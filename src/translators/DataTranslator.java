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

import db.Saver;
import search.SQLObject;
import utility.Utility;
import antlr.DataLexer;
import antlr.DataParser;
import antlr.DataParser.AssignStatContext;
import antlr.DataParser.CallStatContext;
import antlr.DataParser.ExprContext;
import antlr.DataParser.ProgContext;
import antlr.DataParser.StatementContext;

public class DataTranslator {
	
	private static String POST = "_data_";
	private  int count = 0;
	
	private String snippet;
	private List<SQLObject> list;
	public DataTranslator(String snippet){
		this.snippet = snippet;
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
					AssignStatContext c = (AssignStatContext) child;
					convert(c);
				} 

			}
		
	}
}
	private void convert(AssignStatContext c) {
		String source = c.getText();
		Map<String, String> variables = new HashMap<String, String>();
		String id = c.ID().getText();
		String newId = this.getProcessedId(variables, id);
		String exprConstraint = this.getExpr(variables, c.expr());
		String constraint = "(assert (= " + exprConstraint + " " + newId +  "))";
		
		SQLObject object = new SQLObject();
		object.setSource(source);
		object.setVariableTrack(variables);
		object.setConstraints(constraint);
		this.list.add(object);
		
	}
	
	private String getExpr(Map<String, String> variables, ExprContext expr) {

		// float, int, char, char*
		if (expr.getChildCount() == 1) {
			if (expr.FLOAT() != null || expr.INT() != null) {
				return expr.getText().toString();
			}
			else{
				String id = expr.ID().getText();
				String newId = this.getProcessedId(variables, id);	
				return newId;
			}

		} else {
			return "(" + expr.getChild(1).getText() + " "
					+ getExpr(variables, expr.expr(0)) + " " + getExpr(variables, expr.expr(1)) + ")";
		}
		
	}
	
	private String getProcessedId(Map<String, String> variable, String id){
		if(variable.containsKey(id)) return variable.get(id);
		this.count++;
		String newId =  id + POST + count;
		variable.put(id, newId);
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
		String snippet = Utility.getStringFromFile("Prototype/Database");
		DataTranslator translator = new DataTranslator(snippet);
		//translator.print();
	}
}
