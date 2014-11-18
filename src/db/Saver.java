package db;

import java.sql.PreparedStatement;

import pathExtractor.Method;
import translators.MethodTranslator;
import utility.Utility;

public class Saver {
	
	
	public static void save(Method method){
		DataBaseManager.connect(DataBaseManager.USER, DataBaseManager.PASSWORD, DataBaseManager.DATABASE);
		for(String path : method.getPath()){
			String methodName = method.getName();
			String source = method.getSource();
			String constraints = method.getPathToConstraint().get(path);
			String input = method.getPathToInput().get(path);
			System.out.println("-----input-----");
			System.out.println(input);
			String sql = "insert into srcConstraints (path, source, methodName, constraints, input) " +
					"values(?, ?, ?, ?, ?)";
			PreparedStatement  statement = null;
			try{
				statement = DataBaseManager.conn.prepareStatement(sql);
				statement.setString(1, path);
				statement.setString(2, source);
				statement.setString(3, methodName);
				statement.setString(4, constraints);
				statement.setString(5, input);
				statement.execute();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void save(String source){
		MethodTranslator method = new MethodTranslator(source);
		String[] input = method.getInput();
		String inputstring = getInputString(input);
		String constraint = method.getConstraint();
		System.out.println(inputstring);
		DataBaseManager.connect(DataBaseManager.USER, DataBaseManager.PASSWORD, DataBaseManager.DATABASE);
		String sql = "insert into srcConstraints (source, path, constraints, input) " +
				"values(?, ?, ?, ?)";
		PreparedStatement  statement = null;
		try{
			statement = DataBaseManager.conn.prepareStatement(sql);
			statement.setString(1, source);
			statement.setString(2, source);
			statement.setString(3, constraint);
			statement.setString(4, inputstring);
			statement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	
	
	private static String getInputString(String[] input){
		String s = "";
		for(int i = 0; i < input.length - 1; i++){
			s += input[i];
			s += "\n";
		}
		s += input[input.length - 1];
		return s;
	}
	
	public static void main(String[] args){
		String content = Utility.getStringFromFile("./ctest/methods/test.c");
		save(content);
	}
}
