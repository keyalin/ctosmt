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

import utility.Utility;
import antlr.SSALexer;
import antlr.SSAParser;
import antlr.SSAParser.ArgumentsContext;
import antlr.SSAParser.BlockContext;
import antlr.SSAParser.CallExprContext;
import antlr.SSAParser.DeclarationStatContext;
import antlr.SSAParser.ExprContext;
import antlr.SSAParser.FormalArgumentContext;
import antlr.SSAParser.FormalParameterContext;
import antlr.SSAParser.FunctionContext;
import antlr.SSAParser.ParametersContext;
import antlr.SSAParser.StatementContext;
import antlr.SSAParser.AssignStatContext;
import antlr.SSAParser.CallStatContext;
import antlr.SSAParser.ReturnStatContext;

/**
 * This class takes a file, two buggy line numbers, and output a file that is applied by SSA
 * @author yke
 *
 */
public class SSATranslator {
	private static String CONVENTION1 = "_output_";
	private static String CONVENTION2 = "_ssa_";
	private String methodName;
	private String methodString;
	private int begin;
	private int end;
	private int start;
	private int stop;
	
	private List<String> methodPostSSA;
	//value type
	private Map<String, String> input;
	private Map<String, String> output;
	
	private Map<String, String> variableTrack;
	private Map<String, String> variableTypeTrack;
	private List<String> contentConstraints;
	
	private int newVariableCount;

	public SSATranslator(String methodString, int begin, int end){
		this.start = -1;
		this.stop = -1;
		this.methodString = methodString;
		this.begin = begin;
		this.end = end;
		this.input = new HashMap<String, String>();
		this.output = new HashMap<String, String>();
		this.newVariableCount = 0;
		this.methodPostSSA = new ArrayList<String>();
		this.variableTrack = new HashMap<String, String>();
		this.variableTypeTrack = new HashMap<String, String>();
		
		contentConstraints = new ArrayList<String>();
		applySSA();
		translate();
	}

	public static String getCONVENTION1() {
		return CONVENTION1;
	}

	public static void setCONVENTION1(String cONVENTION1) {
		CONVENTION1 = cONVENTION1;
	}

	public static String getCONVENTION2() {
		return CONVENTION2;
	}

	public static void setCONVENTION2(String cONVENTION2) {
		CONVENTION2 = cONVENTION2;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodString() {
		return methodString;
	}

	public void setMethodString(String methodString) {
		this.methodString = methodString;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	public List<String> getMethodPostSSA() {
		return methodPostSSA;
	}

	public void setMethodPostSSA(List<String> methodPostSSA) {
		this.methodPostSSA = methodPostSSA;
	}

	public Map<String, String> getInput() {
		return input;
	}

	public void setInput(Map<String, String> input) {
		this.input = input;
	}

	public Map<String, String> getOutput() {
		return output;
	}

	public void setOutput(Map<String, String> output) {
		this.output = output;
	}

	public Map<String, String> getVariableTrack() {
		return variableTrack;
	}

	public void setVariableTrack(Map<String, String> variableTrack) {
		this.variableTrack = variableTrack;
	}

	public Map<String, String> getVariableTypeTrack() {
		return variableTypeTrack;
	}

	public void setVariableTypeTrack(Map<String, String> variableTypeTrack) {
		this.variableTypeTrack = variableTypeTrack;
	}

	public List<String> getContentConstraints() {
		return contentConstraints;
	}

	public void setContentConstraints(List<String> contentConstraints) {
		this.contentConstraints = contentConstraints;
	}

	public int getNewVariableCount() {
		return newVariableCount;
	}

	public void setNewVariableCount(int newVariableCount) {
		this.newVariableCount = newVariableCount;
	}

	private void translate() {
		try{
			String content = getContent();
			System.out.print(content);
			ContentTranslator translator = new ContentTranslator(content);
			List<String> constraints = translator.getConstraints();
			for(String s : constraints){
				System.out.println(s);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private String getContent() {
		String content = "";
		for(int i = 0; i < this.methodPostSSA.size(); i++){
			if(i >= start && i <= stop) continue;
			String s = this.methodPostSSA.get(i);
			content = content + s + "\n";
		}
		return content;
	}

	private void applySSA() {
		InputStream stream = new ByteArrayInputStream(methodString.getBytes());
		try {
			ANTLRInputStream input = new ANTLRInputStream(stream);
			SSALexer lexer = new SSALexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SSAParser parser = new SSAParser(tokens);
			FunctionContext function = parser.function();
			GetMethodName(function);
			GetInputAndOutputPart(function);
			GetStatements(function);
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
	}

	private void GetStatements(FunctionContext function) {
		BlockContext block = function.block();
		for(int i = 0; i < block.statement().size(); i++){
			StatementContext statement = block.statement(i);
			ConvertStatement(statement, i);
		}
		
	}

	private void ConvertStatement(StatementContext statement, int index) {
//		System.out.println(statement.getChildCount());
//		System.out.println(statement.getText());
		ParseTree child = statement.getChild(0);
		if(index >= begin && index <= end){
			this.start = start == -1 ? this.methodPostSSA.size() : start;
		}
		//System.out.println(child.getText());
		if (child instanceof DeclarationStatContext) {
			DeclarationStatContext c = (DeclarationStatContext) child;
			convert(c, index);
		} else if (child instanceof AssignStatContext) {
			AssignStatContext c = (AssignStatContext) child;
			convert(c, index);
		} else if (child instanceof ReturnStatContext) {
			ReturnStatContext c = (ReturnStatContext) child;
			convert(c, index);
		} else if (child instanceof CallStatContext) {
			CallStatContext c = (CallStatContext) child;
			convert(c, index);
		}
		if(index >= begin && index <= end){
			this.stop = this.methodPostSSA.size() - 1;
		}
		
	}

	private void convert(CallStatContext c, int index) {
		String ssa = this.applySSAToCallExpr(c.callExpr()) + ";";
		this.methodPostSSA.add(ssa);
		
	}

	private void convert(ReturnStatContext c, int index) {
		String text = "";
		if(c.ID() != null){
			text = "return " + this.variableTrack.get(c.ID().getText()) + ";";
		}
		else{
			text = c.getText() + ";";
		}
		this.methodPostSSA.add(text);
		
	}

	private void convert(AssignStatContext c, int index) {
		String type = c.type() == null ? "" : c.type().getText();
		String variable = c.ID().getText();
		String newVariable = "";
		if(type.equals("")){
			newVariable = this.processExistingVariable(variable);
		}
		else{
			newVariable = this.processNonExistingVariable(variable, type);
		}
		
		String pointer = (c.pointerTag() == null ? "" : "*");
		String expr = applySSAToExpr(c.expr());
		String ssa = type +  pointer + " " + newVariable + c.assignOperater().getText() + expr + ";";
		this.methodPostSSA.add(ssa);
	}
	
	private String processExistingVariable(String variable){
		String newVariable;
		newVariable = this.getNewName(variable);
		this.methodPostSSA.add(this.variableTypeTrack.get(variable) + " " + newVariable + ";");
		//this.methodPostSSA.add(newVariable + " = " + this.variableTrack.get(variable) + ";");
		this.variableTrack.put(variable, newVariable);
		this.variableTrack.put(newVariable, newVariable);
		this.variableTypeTrack.put(newVariable, this.variableTypeTrack.get(variable));
		return newVariable;
	}
	
	private String processNonExistingVariable(String variable, String type){
		this.variableTypeTrack.put(variable, type);
		this.variableTrack.put(variable, variable);
		return variable;
	}
	
	private String getNewName(String name){
		newVariableCount++;
		return name + CONVENTION2 + this.newVariableCount;
	}

	private String applySSAToExpr(ExprContext expr) {
		if (expr.getChildCount() == 1) {
			if(expr.callExpr() != null){
				return applySSAToCallExpr(expr.callExpr());
			}
			else if(expr.ID() != null){
				String id = expr.ID().getText();
				return this.variableTrack.get(id);
			}              
			else{
				return expr.getText();
			}

		} else {
			String left = applySSAToExpr(expr.expr(0));
			String right = applySSAToExpr(expr.expr(1));
			String operator = expr.getChild(1).getText();
			return left + " " + operator + " " + right;
		}
	}

	private String applySSAToCallExpr(CallExprContext callExpr) {
		//TODO dosomeghitn for callID
		String text = callExpr.ID().getText() + "(";
		ArgumentsContext arguments = callExpr.arguments();
		for(int i = 0;i < arguments.formalArgument().size(); i++){
			FormalArgumentContext context = arguments.formalArgument(i);
			if(context.ID() != null){
				String name = context.ID().getText();
				text = text + this.variableTrack.get(name) + ", ";
			}
		}
		
		text = text.substring(0, text.length() - 2) + ")";
		return text;
		
	}

	private void convert(DeclarationStatContext c, int index) {
		String type = c.type().getText();
		String id = c.ID().getText();
		String pointer = (c.pointerTag() == null ? "" : "*");
		String ssa = type + pointer + " " + id + ";";
		this.variableTrack.put(id, id);
		this.methodPostSSA.add(ssa);
		this.variableTypeTrack.put(id, type);
		
	}

	private void GetInputAndOutputPart(FunctionContext function) {
		//input
		ParametersContext parameters = function.parameters();
		for(int i = 0; i < parameters.formalParameter().size(); i++){
			FormalParameterContext context = parameters.formalParameter(i);
			String type = context.type().getText();
			String id = context.ID().getText();
			this.input.put(id, type);
			this.variableTrack.put(id, id);
			this.variableTypeTrack.put(id, type);
		}
		//output
		String outputType = function.type().getText();
		this.output.put(CONVENTION1, outputType);
		
		
	}

	private void GetMethodName(FunctionContext function) {
		this.methodName = function.ID().getText();		
	}
	
	public void print(){
		System.out.print(this.start);
		System.out.println(this.stop);

		
		for(String s : this.variableTrack.keySet()){
			System.out.println(s + ':' + this.variableTrack.get(s));
		}
		
		for(String ssa : this.methodPostSSA){
			System.out.println(ssa);
		}
	}
	
	public static void main(String[] args){
		String methodString = Utility.getStringFromFile("Prototype/test1.c");
		SSATranslator translator = new SSATranslator(methodString, 2, 3);
		translator.print();
	}
}
