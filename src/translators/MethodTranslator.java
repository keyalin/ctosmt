package translators;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import lookups.TypeTable;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.CLexer;
import antlr.CParser;
import antlr.CParser.AssignStatContext;
import antlr.CParser.BlockContext;
import antlr.CParser.ExprContext;
import antlr.CParser.FormalParameterContext;
import antlr.CParser.FunctionContext;
import antlr.CParser.ParametersContext;
import antlr.CParser.ReturnStatContext;
import antlr.CParser.StatementContext;
import antlr.CParser.VariableContext;


//The translator receives a parser of functions, and translate each function into constraints.
public class MethodTranslator {
	private String src;
	private String[] input;
	private String constraint;
	
	
	public String getSrc() {
		return src;
	}

	public String[] getInput() {
		return input;
	}
	
	

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public MethodTranslator(String src) {
		this.src = src;
		constraint = "";
		try {
			convertToConstraints();
		} catch (IOException e) {
			constraint = null;
			input = null;
		}		
	}

	
	
	//convert function to constraints and write constraints into file with the same name as the function name
	private void convertToConstraints() throws IOException {
		InputStream stream = new ByteArrayInputStream(src.getBytes());
		ANTLRInputStream input = new ANTLRInputStream(stream);
		CLexer lexer = new CLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CParser parser = new CParser(tokens);
		FunctionContext function = parser.function();
		ParametersContext parameters = function.parameters();
		convertParameters(parameters);
		
		BlockContext block = function.block();
		covertBlock(block);
		this.constraint = constraint.intern();
		
	}
	
	private void covertBlock(BlockContext block) {
		for(VariableContext v : block.variable()){
			String type = TypeTable.getInstance().getType(v.type().getText());
			
			String temp = "(declare-const " + v.ID().getText() + " " + type + ")\n";
			constraint += temp;
		}
		
			
		for(StatementContext s : block.statement()){
			for(int i = 0; i < s.getChildCount(); i++){
				ParseTree statement = s.getChild(i);
				if(statement instanceof AssignStatContext){
					AssignStatContext as= (AssignStatContext) statement;
					if(!as.type().isEmpty()){
						String type = TypeTable.getInstance().getType(as.type().getText());
						String temp = "(declare-const " + as.ID().getText() + " " + type + ")\n";
						constraint += temp;
					}
					String whole = getConstraintsOfAssign(as);
					constraint += whole;
				}
				else if(statement instanceof ReturnStatContext){
					ReturnStatContext rs = (ReturnStatContext)statement;
					String temp = "(assert ( = out " + rs.expr().getText() + "))\n";
					constraint += temp;
				}
			}
		}
	}
		


	private void convertParameters(ParametersContext parameters) {
		input = new String[parameters.formalParameter().size()];
		for(int i = 0; i < parameters.formalParameter().size(); i++){
			FormalParameterContext fp = (FormalParameterContext) parameters.formalParameter(i);
			input[i] = fp.ID().getText();
			String type = TypeTable.getInstance().getType(fp.type().getText());
			String temp = "(declare-const " + input[i] + " " + type +")\n";
			constraint += temp;
			
		}
		
	}

	public String getConstraintsOfAssign(AssignStatContext as){
		String constraint = "(assert (= " + as.ID().getText() + " " + getExpr(as.expr()) + " ))\n";
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
}