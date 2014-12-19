package translators;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import lookups.PointerTable;
import lookups.TypeTable;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

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

	public List<String> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}

	public SnippetTranslator(String snippet) {
		this.snippet = snippet;
		constraints = new ArrayList<String>();
		potentialInput = new ArrayList<String>();
		potentialOutput = "";
		translate();
	}

	private void translate() {
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
			//
			e.printStackTrace();
		}
	}

	private void convertStatementToConstraints(List<StatementContext> statements) {
		for (StatementContext statement : statements) {
			for (int i = 0; i < statement.getChildCount(); i++) {
				
				ParseTree child = statement.getChild(i);
				
				if (child instanceof DeclarationStatContext) {
					
					continue;
				} else if (child instanceof AssignStatContext) {
					System.out.println("d" + child.getText());
					AssignStatContext c = (AssignStatContext) child;
					convert(c);
				} else if (child instanceof ReturnStatContext) {
					System.out.println("c" + child.getText());
					ReturnStatContext c = (ReturnStatContext) child;
					convert(c);
				} else if (child instanceof ExprStatContext) {
					continue;
				}

			}

		}

	}

	private void convert(ExprStatContext c) {
		//this.constraints.add(getExpr(c.expr()));
		//this deals with function call, still in progress

	}

	private void convert(ReturnStatContext returns) {
		String constraint = "(assert (= " + returns.ID().getText() + " "
				+ this.output + " ))";
		constraints.add(constraint);

	}

	/**
	 * convert assign statement into constraints
	 * 
	 * @param assgin
	 */
	private void convert(AssignStatContext assign) {
		if (!assign.type().isEmpty()) {

			String type = assign.type().getText();
//			System.out.println(assign.getText());
//			System.out.println(assign.getChildCount());

			// if pointers
			if (assign.getChildCount() == 6) {
				type = type + '*';
				// not char*
				if (!type.equals("char*")) {
//					String constraint = "(declare-const "
//							+ assign.ID().getText() + " "
//							+ TypeTable.getInstance().getType(type) + ")";
//					constraints.add(constraint);
					covertPointerAssgin(assign);
				}
				else{
					//todo String conversion
					convertStringAssign(assign);
				}

				
			}
			else if(assign.getChildCount() == 5){
				convertNonPointerAssign(assign);
			}
		}
		else{
			String type = PointerTable.getInstance().getType(assign.ID().getText());

			// if pointers
			if (assign.getChildCount() == 5) {
				
				
				if (!type.equals("String")) {
//					String constraint = "(declare-const "
//							+ assign.ID().getText() + " "
//							+ TypeTable.getInstance().getType(type) + ")";
//					constraints.add(constraint);
					covertPointerAssgin(assign);
				}
				else{
					//todo String conversion
					convertStringAssign(assign);
				}

				
			}
			else if(assign.getChildCount() == 4){
				convertNonPointerAssign(assign);
			}
		}

	}

	private void convertNonPointerAssign(AssignStatContext assign) {
		String ID = assign.ID().getText();
		if(!assign.type().isEmpty()){
			String type = assign.type().getText();
			String constraint = "(declare-const " + ID + " "
					+ TypeTable.getInstance().getType(type) + ")";
			constraints.add(constraint);
		}
		String exprConstraint = this.getExpr(assign.expr());
		String constraint = "(assert (= " + exprConstraint + " " + ID +  "))";
		this.constraints.add(constraint);
	}

	private void convertStringAssign(AssignStatContext assign) {
		String ID = assign.ID().getText();
		String primitive = assign.StringLiteral().getText();
		primitive = primitive.substring(1, primitive.length() - 1);
		StringRepresetation str = new StringRepresetation(ID, primitive);
		List<String> represents = str.getConstraints();
		this.constraints.addAll(represents);
		
	}

	private void covertPointerAssgin(AssignStatContext assign) {
		String ID = assign.ID().getText();
		String address = assign.Address().getText();
		if(!assign.type().isEmpty()){
			String type = assign.type().getText() + '*';
			PointerTable.getInstance().setType(ID, type);
			String constraint = "(declare-const " + ID + " "
					+ TypeTable.getInstance().getType(type) + ")";
			constraints.add(constraint);
		}
		
		PointerTable.getInstance().setValue(ID, address);
		String constraint = "(assert (= AddressOf " + ID + " " + address + "))";
		this.constraints.add(constraint);
		
	}

	/**
	 * translate declaration into constraints
	 * 
	 * @param decl
	 */
	private void convert(DeclarationStatContext decl) {
		String type = decl.type().getText();

		if (decl.INT() == null) {
			// a pointer
			if (decl.getChildCount() == 4) {
				// char*, treat as a string
				type = type + '*';
				String constraint = "(declare-const " + decl.ID().getText()
						+ " " + TypeTable.getInstance().getType(type) + ")";
				constraints.add(constraint);
				if (type.equals("String")) {
					constraints.add("(assert (>= (length "
							+ decl.ID().getText() + " ) " + "0 ))");
				}
			} else if (decl.getChildCount() == 3) {
				String constraint = "(declare-const " + decl.ID().getText()
						+ " " + TypeTable.getInstance().getType(type) + ")";
				constraints.add(constraint);
			}

		}
		// check if it is a char array
		else {
			String constraint = "(declare-const " + decl.ID().getText() + " "
					+ TypeTable.getInstance().getType("char[]") + " )";
			constraints.add(constraint);
			constraints.add("(assert (<= (length " + decl.ID().getText()
					+ " ) " + decl.INT().getText() + "))");
			constraints.add("(assert (>= (length " + decl.ID().getText()
					+ " ) " + "0 ))");
		}
	}

	private String getExpr(ExprContext expr) {
		// float, int, char, char*
		if (expr.getChildCount() == 1) {
			if (expr.ID() != null || expr.FLOAT() != null || expr.INT() != null) {
				return expr.getText().toString();
			} else {
				if (expr.StringLiteral() != null) {
					String content = expr.StringLiteral().getText();
					content = content.substring(1, content.length() - 1);
					return content;
				} else if (expr.CharacterLiteral() != null) {
					int value = 0;
					char c = expr.CharacterLiteral().getText().charAt(1);
					if(Character.isDigit(c)){
						value = c - '0';
					}else if(Character.isLetter(c)){
						value = c;
					}
					return Integer.toString(value);
				}
			}

		} else {
			return "(" + expr.getChild(1).getText() + " "
					+ getExpr(expr.expr(0)) + " " + getExpr(expr.expr(1)) + ")";
		}
		return output;
	}

}
