package translators;

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

import search.SQLObject;
import utility.Utility;
import antlr.DataLexer;
import antlr.DataParser;
import antlr.DataParser.AssignStatContext;
import antlr.DataParser.CallStatContext;
import antlr.DataParser.ExprContext;
import antlr.DataParser.ProgContext;
import antlr.DataParser.StatementContext;
import db.Saver;

public class DataTranslator {
	
	private String POST;
	private  int count = 0;
	
	private String snippet;
	private List<SQLObject> list;
	private String mainType;
	public DataTranslator(String fileName, String mainType){
		this.mainType = mainType;
		POST = fileName.substring(fileName.lastIndexOf("/") + 1);
		this.snippet = Utility.getStringFromFile(fileName);
		this.list = new ArrayList<SQLObject>();
		Translate();
		save();
	}
	private void save() {
		for(SQLObject object : list){
			Saver.save(object);
		}
		
	}
	private void Translate() {
		InputStream stream = new ByteArrayInputStream(snippet.getBytes());
		try {
			ANTLRInputStream input = new ANTLRInputStream(stream);
			DataLexer lexer = new DataLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			DataParser parser = new DataParser(tokens);
			ProgContext prog = parser.prog();
			List<StatementContext> statements = prog.statement();
			convertStatementToSQLObject(statements);
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
		
	}
	private void convertStatementToSQLObject(List<StatementContext> statements) {
		for (StatementContext statement : statements) {
			for (int i = 0; i < statement.getChildCount(); i++) {
				ParseTree child = statement.getChild(i);
				if (child instanceof CallStatContext) {
					CallStatContext c = (CallStatContext) child;
					convert(c);
				} else if (child instanceof AssignStatContext) {
					System.out.println(child.getText());
					if(((AssignStatContext) child).StringLiteral() != null){
						AssignStatContext c = (AssignStatContext) child;
						convertString(c);
					}
					else{
						AssignStatContext c = (AssignStatContext) child;
						convert(c);
					}
					
				} 
				else{
					//System.out.println(child);
				}

			}
		
	}
}
	private void convertString(AssignStatContext c) {
		String source = c.getText();
		Map<String, String> variables = new HashMap<String, String>();
		Map<String, String> variablesTypes = new HashMap<String, String>();
		String id = c.ID().getText();
		variablesTypes.put(id, "Char*");
		String newId = this.getProcessedId(variables, id, variablesTypes);
		String content = c.StringLiteral().getText();
		StringRepresentation rep = new StringRepresentation(newId, content.substring(1, content.length() - 1));
		String constraint = "";
		for(String s : rep.getConstraints()){
			constraint = constraint + s + "\n";
		}
		
		SQLObject object = new SQLObject();
		object.setMainType(this.mainType);
		object.setSource(source);
		object.setVariableTrack(variables);
		object.setConstraints(constraint);
		object.setVariableTypeTrack(variablesTypes);
		object.getIntroduced().put(id, newId);
		this.list.add(object);
		
	}
	private void convert(AssignStatContext c) {
		String source = c.getText();
		Map<String, String> variables = new HashMap<String, String>();
		Map<String, String> variablesTypes = new HashMap<String, String>();
		String id = c.ID().getText();
		variablesTypes.put(id, this.mainType);
		String newId = this.getProcessedId(variables, id, variablesTypes);
		String exprConstraint = this.getExpr(variables, c.expr(), variablesTypes);
		String constraint = "(assert (= " + exprConstraint + " " + newId +  "))";
		
		SQLObject object = new SQLObject();
		object.setSource(source);
		object.setVariableTrack(variables);
		object.setConstraints(constraint);
		object.getIntroduced().put(id, newId);
		object.setVariableTypeTrack(variablesTypes);
		object.setMainType(this.mainType);
		this.list.add(object);
		
	}
	
	private String getExpr(Map<String, String> variables, ExprContext expr, Map<String, String> variablesTypes) {

		// float, int, char, char*
		if (expr.getChildCount() == 1) {
			if (expr.FLOAT() != null || expr.INT() != null) {
				return expr.getText().toString();
			}
			else{
				String id = expr.ID().getText();
				String newId = this.getProcessedId(variables, id, variablesTypes);	
				return newId;
			}

		} else {
			if(expr.expr().size() == 1){
				return getExpr(variables, expr.expr(0), variablesTypes);
			}
			return "(" + expr.getChild(1).getText() + " "
					+ getExpr(variables, expr.expr(0), variablesTypes) + " " + getExpr(variables, expr.expr(1), variablesTypes) + ")";
		}
		
	}
	
	private String getProcessedId(Map<String, String> variable, String id, Map<String, String> variablesTypes){
		if(variable.containsKey(id)) return variable.get(id);
		this.count++;
		String newId =  id + POST + count;
		variable.put(id, newId);
		variablesTypes.put(newId, variablesTypes.get(id));
		
		return newId;
		
	}
	private void convert(CallStatContext c) {
		
		
	}
	
	public void print(){
		for(SQLObject object : list){
			System.out.println(object);
		}
	}
	
	public static void main(String[] args){
		//String snippet = Utility.getStringFromFile("Prototype/Database");
		DataTranslator translator = new DataTranslator("Prototype/Database", "float");
		translator.print();
	}
}
