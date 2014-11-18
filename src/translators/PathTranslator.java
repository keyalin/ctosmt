package translators;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import utility.Utility;
import antlr.PathLexer;
import antlr.PathParser;
import antlr.PathParser.AssignStatContext;
import antlr.PathParser.AssumeStatContext;
import antlr.PathParser.DeclarationStatContext;
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
	}
	
	
	private void translate(){
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
		for(StatementContext s : statements){
			for (StatementContext statement : statements) {
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
					}

				}

			}
		}
		
	}


	private void convert(DeclarationStatContext c) {
		
		
	}


	public static void main(String[] args){
		String path = Utility.getStringFromFile("ctest/pathtest/temp");
		PathTranslator p = new PathTranslator(path);
		//System.out.println(path);
	}
	
	
	

}
