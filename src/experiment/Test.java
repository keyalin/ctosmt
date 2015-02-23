package experiment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import experiment.processParser.FunctionDefinitionContext;
import utility.Utility;

public class Test {
	
	
	public static void main(String[] args) throws IOException{
		String file = Utility.getStringFromFile("ccode/test.c");
		InputStream stream = new ByteArrayInputStream(file.getBytes());
		ANTLRInputStream input = new ANTLRInputStream(stream);
		processLexer lexer = new processLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		processParser parser = new processParser(tokens);
		FunctionDefinitionContext con = parser.functionDefinition();
		System.out.println(con.getText());
		//con
		System.out.println(parser.selectionStatement().getText());
	}
	
}
