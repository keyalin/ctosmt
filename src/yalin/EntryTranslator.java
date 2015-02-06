package yalin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import db.Saver;
import translators.StringRepresentation;
import utility.Utility;
import yalin.EntryParser.AssignStatContext;
import yalin.EntryParser.CallStatContext;
import yalin.EntryParser.ExprContext;
import yalin.EntryParser.FormalArgumentContext;
import yalin.EntryParser.ProgContext;
import yalin.EntryParser.StatementContext;
import yalin.EntryParser.TypeStatContext;
import yalin.EntryParser.VariableContext;

public class EntryTranslator {
	private String filePath;
	private EntryObject object;
	private Map<String, String> variableTrack;
	private Map<String, String> variableType;
	private Map<String, String> variableMap;
	private String source;
	private List<String> constraints;
	
	//for generating new name only
	private String fileName;
	private int count;
	
	public EntryTranslator(String filePath){
		this.filePath = filePath;
		int index = this.filePath.lastIndexOf("/");
		fileName = filePath.substring(index+1);
		count = 0;
		
		variableTrack = new HashMap<String, String>();
		variableType = new HashMap<String, String>();
		variableMap = new HashMap<String, String>();
		source = Utility.getStringFromFile(filePath);
		constraints = applySSA();
		
		//initialize sql object
		object = new EntryObject();
		initEntryObject();
		//object.print();
		//Saver.save(object);
		
	}


	public EntryObject getObject() {
		return object;
	}


	public void setObject(EntryObject object) {
		this.object = object;
	}


	private void initEntryObject() {
		object.setSource(source);
		String constraint = "";
		for(String s : this.constraints){
			constraint = constraint + s + "\n";
		}
		object.setConstraint(constraint);
		object.setVariablesTypes(this.variableType);
		object.setVariableMap(variableMap);
		object.setVariableTrack(variableTrack);
		
	}


	/**
	 * apply SSA to the source code and collect the type info and mapping info of every existing and introduced variable
	 * @param filePath2
	 */
	private List<String> applySSA() {
		//String content = Utility.getStringFromFile(filePath);
		List<String> ssa = new ArrayList<String>();
		InputStream stream = new ByteArrayInputStream(source.getBytes());
		try {
			ANTLRInputStream input = new ANTLRInputStream(stream);
			EntryLexer lexer = new EntryLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			EntryParser parser = new EntryParser(tokens);
			ProgContext prog = parser.prog();
			convertToSSAString(prog, ssa);
			return ssa;
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * c
	 * @param prog
	 * @param ssaString
	 */
	private void convertToSSAString(ProgContext prog, List<String> ssaString) {
		List<StatementContext> list = prog.statement();
		for(StatementContext con : list){
			convertStatement(con, ssaString);			
		}		
	}
	
	private void convertStatement(StatementContext con, List<String> ssa) {
		ParseTree child = con.getChild(0);
		//System.out.println(child.getText());
		if (child instanceof CallStatContext) {
			CallStatContext c = (CallStatContext) child;
			convert(c, ssa);
		} else if (child instanceof AssignStatContext) {
			if(((AssignStatContext) child).StringLiteral() != null){
				AssignStatContext c = (AssignStatContext) child;
				convertString(c, ssa);
			}
			else{
				AssignStatContext c = (AssignStatContext) child;
				convert(c, ssa);
			}
			
		}
		else if(child instanceof TypeStatContext){
			TypeStatContext c = (TypeStatContext) child;
			convert(c);
		}

		
	}
	
	//convert string assign statement
	private void convertString(AssignStatContext c, List<String> ssa) {
		String content = c.StringLiteral().getText();
		content = content.substring(1, content.length() - 1);
		String id = c.ID().getText();
		String newId = this.generateNewName(id);
		StringRepresentation rep = new StringRepresentation(newId, content);
		ssa.addAll(rep.getConstraints());
		
	}


	//for non string assignment
	private void convert(AssignStatContext c, List<String> ssa) {
		String expr = getExpr(c.expr());
		String id = this.variableTrack.get(c.ID().getText());
		String newId = generateNewName(id);
		String constraint = "(assert(= " + newId + " " + expr + "))";
		ssa.add(constraint);
		
	}


	private String getExpr(ExprContext expr) {
		if(expr.getChildCount() == 1){
			if(expr.ID() != null){
				return this.variableTrack.get(expr.getText());
			}
			else return expr.getText();
		}
		else if(expr.expr().size() == 2){
			String left = getExpr(expr.expr(0));
			String right = getExpr(expr.expr(1));
			String operator = expr.getChild(1).getText();
			return "(" + operator + " "+ left + " " + right + ")";
		}
		else if(expr.expr().size() == 1){
			return "(" + getExpr(expr.expr(0)) + ")"; 
		}
		return "";
	}


	private void convert(TypeStatContext c) {
		for(VariableContext variable : c.variable()){
			String type = variable.type().getText();
			String id = variable.ID().getText();
			this.variableMap.put(id, id);
			this.variableTrack.put(id, id);
			this.variableType.put(id, type);
		}		
	}


	private void convert(CallStatContext c, List<String> ssa) {
		String function = c.ID().getText();
		if(function.equals("strcpy")){
			FormalArgumentContext argument1 = c.arguments().formalArgument(0);
			FormalArgumentContext argument2 = c.arguments().formalArgument(1);
			String dest = this.variableTrack.get(argument1.ID().getText());
			String source = this.variableTrack.get(argument2.ID().getText());
			String newDest = generateNewName(dest);
			List<String> constraints = StringMethodConstraintsGenerator.getstrcpyConstraints(newDest, source);
			ssa.addAll(constraints);
//			for(String s : constraints){
//				System.out.println(s);
//			}
		}
		else if(function.equals("strncpy")){
			FormalArgumentContext argument1 = c.arguments().formalArgument(0);
			FormalArgumentContext argument2 = c.arguments().formalArgument(1);
			FormalArgumentContext argument3 = c.arguments().formalArgument(2);
			String num = argument3.ID().getText();
			String dest = this.variableTrack.get(argument1.ID().getText());
			String source = this.variableTrack.get(argument2.ID().getText());
			String newDest = generateNewName(dest);
			List<String> constraints = StringMethodConstraintsGenerator.getstrncpyConstraints(newDest, source, num);
			ssa.addAll(constraints);
//			for(String s : constraints){
//				System.out.println(s);
//			}			
		}
		else if(function.equals("strcat")){
			FormalArgumentContext argument1 = c.arguments().formalArgument(0);
			FormalArgumentContext argument2 = c.arguments().formalArgument(1);
			String dest = this.variableTrack.get(argument1.ID().getText());
			String source = this.variableTrack.get(argument2.ID().getText());
			String newDest = generateNewName(dest);
			List<String> constraints = StringMethodConstraintsGenerator.getStrcatConstraints(dest, source, newDest);
			ssa.addAll(constraints);
//			for(String s : constraints){
//				System.out.println(s);
//			}			
		}
		else if(function.equals("strncat")){
			FormalArgumentContext argument1 = c.arguments().formalArgument(0);
			FormalArgumentContext argument2 = c.arguments().formalArgument(1);
			FormalArgumentContext argument3 = c.arguments().formalArgument(2);
			String dest = this.variableTrack.get(argument1.ID().getText());
			String source = this.variableTrack.get(argument2.ID().getText());
			String num = argument3.ID().getText();
			String newDest = generateNewName(dest);
			List<String> constraints = StringMethodConstraintsGenerator.getStrncatConstraints(dest, source, num, newDest);
			ssa.addAll(constraints);
//			for(String s : constraints){
//				System.out.println(s);
//			}
		}
		
	}


	private String generateNewName(String dest) {
		String newName = "_" + this.fileName + "_" + count++;
//		this.variableTrack.put(dest, newName);
		this.variableTrack.put(this.variableMap.get(dest), newName);
		this.variableType.put(newName, this.variableType.get(dest));
		this.variableMap.put(newName, this.variableMap.get(dest));
		return newName;
	}


	public static void main(String[] args){
		EntryTranslator trans = new EntryTranslator("entries/entry1");
	}
	
}
