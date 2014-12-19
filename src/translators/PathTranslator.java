package translators;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lookups.TypeTable;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import utility.Utility;
import antlr.PathLexer;
import antlr.PathParser;
import antlr.PathParser.AddressExprContext;
import antlr.PathParser.AssignStatContext;
import antlr.PathParser.AssumeStatContext;
import antlr.PathParser.CallStatContext;
import antlr.PathParser.DeclarationStatContext;
import antlr.PathParser.DefExprContext;
import antlr.PathParser.ExprContext;
import antlr.PathParser.ProgContext;
import antlr.PathParser.ReturnStatContext;
import antlr.PathParser.StatementContext;



public class PathTranslator {
	private String path;
	private String constraints;
	public PathTranslator(String path){
		this.path = path;
		constraints = "";
		translate();
		//System.out.println(constraints);
	}
	
	
	
	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public String getConstraints() {
		return constraints;
	}



	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}



	private void translate(){
		System.out.println(path);
		InputStream stream = new ByteArrayInputStream(path.getBytes());
		try {
			ANTLRInputStream input = new ANTLRInputStream(stream);
			PathLexer lexer = new PathLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			PathParser parser = new PathParser(tokens);
			ProgContext prog = parser.prog();
			List<StatementContext> statements = prog.statement();
			convertStatementToConstraints(statements);
			
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
	}
	
	private void convertStatementToConstraints(List<StatementContext> statements) {
		for(StatementContext statement : statements){
				for (int i = 0; i < statement.getChildCount(); i++) {
					ParseTree child = statement.getChild(i);
					if (child instanceof DeclarationStatContext) {
						DeclarationStatContext c = (DeclarationStatContext) child;
						convert(c);
					} else if (child instanceof AssignStatContext) {
						AssignStatContext c = (AssignStatContext) child;
						convert(c);
					} else if (child instanceof ReturnStatContext) {
						ReturnStatContext c = (ReturnStatContext) child;
						convert(c);
					} else if (child instanceof AssumeStatContext) {
						AssumeStatContext c = (AssumeStatContext) child;
						convert(c);
					} else if(child instanceof CallStatContext){
						CallStatContext c = (CallStatContext) child;
						convert(c);
					}

				}

			}
		
	}


	private void convert(CallStatContext c) {
		
		
	}



	private void convert(AssumeStatContext assum) {
		//System.out.println(assum);
		String operator = assum.comparator().getText();
		String leftExpr = this.getExpr(assum.expr(0));
		String rightExpr = this.getExpr(assum.expr(1));
		String constraint = "";
		if(operator.equals("!=")){
			constraint = "(assert(not (= " + leftExpr +" "+ rightExpr + ")))";
		}
		if(operator.equals("==")){
			constraint = "(assert(= " + leftExpr +" "+ rightExpr + "))";
		}
		else{
			constraint = "(assert( " + operator + " " + leftExpr + " " + rightExpr +  " ))";
		}
		constraints = constraints + constraint + '\n';
	}


	private void convert(ReturnStatContext ret) {
		String output = ret.getChild(2).getText();
		String constraint = "(declare-const _output_ Int)";
		constraints = constraints + constraint + '\n';
		constraint = "(assert (= _output_ " + output +  "))";
		constraints = constraints + constraint + '\n';
	}


	private void convert(AssignStatContext assign) {
		if (assign.getChildCount() == 5) {						
			covertPointerAssgin(assign);
		}
		else if(assign.getChildCount() == 4){
			convertNonPointerAssign(assign);
		}
		
	}

	private void convertNonPointerAssign(AssignStatContext assign) {
		String ID = assign.ID().getText();
		
		String exprConstraint = this.getExpr(assign.expr());
		String constraint = "(assert (= " + exprConstraint + " " + ID +  "))";
		constraints = constraints + constraint + '\n';
	}

	
	private void covertPointerAssgin(AssignStatContext assign) {
		String ID = assign.ID().getText();
		ExprContext temp = assign.expr();
		
		String constraint = "(assert (= valueOf " + ID + " " + getExpr(temp) + "))";
		constraints = constraints + constraint + '\n';
		
	}

	private void convert(DeclarationStatContext decl) {
		String type = decl.type().getText();
		if (decl.getChildCount() == 4) {
			// char*, treat as a string
			type = type + '*';

		}
		String constraint = "(declare-fun " + decl.ID().getText()
				+ " () " + TypeTable.getInstance().getType(type) + ")";
		constraints = constraints + constraint + '\n';
		
	}


	public static void main(String[] args){
		String path = Utility.getStringFromFile("ctest/pathtest/temp");
		PathTranslator p = new PathTranslator(path);
		//System.out.println(path);
	}
	
	private String getExpr(ExprContext expr) {
		String output = "";
		// float, int, char, char*
		if (expr.getChildCount() == 1) {
			if (expr.ID() != null || expr.FLOAT() != null || expr.INT() != null) {
				return expr.getText().toString();
			} else if(expr.defExpr() != null){
				DefExprContext def = expr.defExpr();
				output = "(valueOf " + def.ID() +")";
			}
			else if(expr.addressExpr() != null){
				AddressExprContext add = expr.addressExpr();
				output = "(addressOf " + add.ID() + ")";
			}

		} else {
			return "(" + expr.getChild(1).getText() + " "
					+ getExpr(expr.expr(0)) + " " + getExpr(expr.expr(1)) + ")";
		}
		return output;
	}
	

}
