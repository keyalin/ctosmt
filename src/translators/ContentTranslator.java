package translators;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.ContentLexer;
import antlr.ContentParser;
import antlr.ContentParser.AssignStatContext;
import antlr.ContentParser.ExprContext;
import antlr.ContentParser.ProgContext;
import antlr.ContentParser.ReturnStatContext;
import antlr.ContentParser.StatementContext;


public class ContentTranslator {
	
	private String snippet;
	private List<String> constraints;
	
	public ContentTranslator(String snippet) throws IOException{
		this.snippet = snippet;
		this.constraints = new ArrayList<String>();
		translate();
	}

	public String getSnippet() {
		return snippet;
	}

	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}

	private void translate() throws IOException {
		InputStream stream = new ByteArrayInputStream(snippet.getBytes());
		ANTLRInputStream input = new ANTLRInputStream(stream);
		ContentLexer lexer = new ContentLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ContentParser parser = new ContentParser(tokens);
		ProgContext prog = parser.prog();
		 List<StatementContext> statements = prog.statement();
		 for(StatementContext statement : statements){
			 ParseTree child = statement.getChild(0);
			 if(child instanceof AssignStatContext){
				 convert((AssignStatContext)child);
			 }
			 else if(child instanceof ReturnStatContext){
				 convert((ReturnStatContext)child);
			 }
			 else{
				 continue;
			 }
		 }
	}


	
	private void convert(ReturnStatContext ret) {
		String output = ret.getChild(1).getText();
		String constraint = "(assert (= _output_ " + output +  "))";
		this.constraints.add(constraint);
	}

	private void convert(AssignStatContext child) {
		String id = child.ID().getText();
		String exprConstraint = this.getExpr(child.expr());
		String constraint = "(assert (= " + exprConstraint + " " + id +  "))";
		this.constraints.add(constraint);
		
	}
	
	private String getExpr(ExprContext exprContext) {
		String output = "";
		// float, int, char, char*
		if (exprContext.getChildCount() == 1) {
				return exprContext.getText().toString();
		} else {
			return "(" + exprContext.getChild(1).getText() + " "
					+ getExpr(exprContext.expr(0)) + " " + getExpr(exprContext.expr(1)) + ")";
		}
	}

}
