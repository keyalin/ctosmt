package db;

import java.sql.PreparedStatement;
import java.util.Map;

import pathExtractor.Method;
import search.SQLObject;
import translators.MethodTranslator;
import utility.Utility;
import yalin.EntryObject;

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
	
	public static void save(EntryObject object){
		DataBaseManager.connect(DataBaseManager.USER, DataBaseManager.PASSWORD, DataBaseManager.DATABASE);
		String source = object.getSource();
		String constraint = object.getConstraint();
		String variablesMap = formatInput(object.getVariableMap());
		//String introduced = formatInput(object.getIntroduced());
		String type = formatInput(object.getVariablesTypes());
		String variableTrack = formatInput(object.getVariableTrack());
		String sql = "insert into prototype (source, constraints, variableType, variableTrack, variableMap) " +
				"values(?, ?, ?, ?, ?)";
		PreparedStatement  statement = null;
		try{
			statement = DataBaseManager.conn.prepareStatement(sql);
			statement.setString(1, source);
			statement.setString(2, constraint);
			statement.setString(3, type);
			statement.setString(4, variableTrack);
			statement.setString(5, variablesMap);
			statement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void save(SQLObject object){
		DataBaseManager.connect(DataBaseManager.USER, DataBaseManager.PASSWORD, DataBaseManager.DATABASE);
			String source = object.getSource();
			String constraint = object.getConstraints();
			String variables = formatInput(object.getVariableTrack());
			String introduced = formatInput(object.getIntroduced());
			String type = object.getMainType();
			String sql = "insert into prototype (source, constraints, variable, introduced, type) " +
					"values(?, ?, ?, ?, ?)";
			PreparedStatement  statement = null;
			try{
				statement = DataBaseManager.conn.prepareStatement(sql);
				statement.setString(1, source);
				statement.setString(2, constraint);
				statement.setString(3, variables);
				statement.setString(4, introduced);
				statement.setString(5, type);
				statement.execute();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	private static String formatInput(Map<String, String> variableTrack) {
		String input = "";
		for(String s : variableTrack.keySet()){
			input = input + s + ":" + variableTrack.get(s) + "\n";
		}
		return input;
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
