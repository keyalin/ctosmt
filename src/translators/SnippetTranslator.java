package translators;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr.SnippetLexer;
import antlr.SnippetParser;
import antlr.SnippetParser.AssignStatContext;
import antlr.SnippetParser.DeclarationStatContext;
import antlr.SnippetParser.ExprContext;
import antlr.SnippetParser.ExprStatContext;
import antlr.SnippetParser.ProgContext;
import antlr.SnippetParser.ReturnStatContext;
import antlr.SnippetParser.StatementContext;

public class SnippetTranslator {
	private String snippet;
	private List<String> constraints;
	private final String output = "_output_";
	private List<String> potentialInput;
	private String potentialOutput;
	
	public SnippetTranslator(String snippet){
		this.snippet = snippet;
		constraints = new ArrayList<String>();
		potentialInput = new ArrayList<String>();
		potentialOutput = "";
		translate();
	}
	
	private void translate(){
		InputStream stream = new ByteArrayInputStream(snippet.getBytes());
		try {
			ANTLRInputStream input = new ANTLRInputStream(stream);
			SnippetLexer lexer = new SnippetLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SnippetParser parser = new SnippetParser(tokens);
			ProgContext prog = parser.prog();
			List<StatementContext> statements = prog.statement();
			convertStatementToConstraints(statements);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void convertStatementToConstraints(List<StatementContext> statements) {
		for(StatementContext statement : statements){
			List<DeclarationStatContext> declarations = (List<DeclarationStatContext>) statement.declarationStat();
			List<AssignStatContext> assigns = (List<AssignStatContext>) statement.assignStat();
			List<ReturnStatContext> returns = (List<ReturnStatContext>) statement.returnStat();
			List<ExprStatContext> expressions = (List<ExprStatContext>) statement.exprStat();
			convertDeclarations(declarations, assigns);
			covnertAssigns(assigns);
			convertExpressions(expressions);
			convertReturns(returns);
		}
		
	}


	private void convertExpressions(List<ExprStatContext> expressions) {
		for(ExprStatContext expr : expressions){
			
		}
		
	}

	private void convertReturns(List<ReturnStatContext> returns) {
		
		for(ReturnStatContext as : returns)
		{
			String constraint = "(assert (= " + as.ID().getText() + " " +  this.output + " ))";
			constraints.add(constraint);
		}
		
	}

	private void covnertAssigns(List<AssignStatContext> assigns) {
		for(AssignStatContext assign : assigns){
			String constraint = this.getConstraintsOfAssign(assign);
				constraints.add(constraint);
		}
		
	}
	
	private String getConstraintsOfAssign(AssignStatContext as){
		String constraint = "(assert (= " + as.ID().getText() + " " + getExpr(as.expr()) + " ))";
		return constraint;
	}
	
	private String getExpr(ExprContext expr){
		if(expr.getChildCount() == 1){
			return expr.getChild(0).getText();
		}
		else{
			return "(" + expr.getChild(1).getText() + " " + getExpr(expr.expr(0)) + " " + getExpr(expr.expr(1)) + ")";
		}
	}

	private void convertDeclarations(List<DeclarationStatContext> declarations, List<AssignStatContext> assigns) {
		for(DeclarationStatContext decl : declarations){
			
			String type = decl.type().getText();
			type = type.substring(0, 1).toUpperCase() + type.substring(1);			
			//check if it is a char array
			if(decl.getChildCount() == 3)
			{	
				String constraint = "(declare-const " + decl.ID().getText() + " string)";
				constraints.add(constraint);
				constraints.add("(assert (<= (length "+ decl.ID().getText() + ") " + decl.INT() + "))");
			}
			else{
				String constraint = "(declare-const " + decl.ID().getText() + " string)";
				constraints.add(constraint);
				this.potentialInput.add(decl.ID().getText());
			}
		}
		
		for(AssignStatContext assgin : assigns){
			if(!assgin.type().isEmpty()){
				String type = assgin.type().getText();
				type = type.substring(0, 1).toUpperCase() + type.substring(1);
				String constraint = "(declare-const " + assgin.ID().getText() + " " + type + ")";
				this.constraints.add(constraint);
			}
		}					
		
	}			
	

}
