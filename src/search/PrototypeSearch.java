package search;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.CaseInfo;
import test.Test;
import db.DataBaseManager;

public class PrototypeSearch {
	private static String SEARCHPROTOTYPE = "select * from prototype";	
	public static void search(CaseInfo info) throws SQLException, IOException{
		DataBaseManager.connect();
		ResultSet result = DataBaseManager.query(SEARCHPROTOTYPE);
		while(result.next()){
			String constraint = result.getString(2);
			String[] inputs = result.getString(3).split("\n");
//			Map<String, String> variables = translator.getVariableTypeTrack();
			String source = result.getString(1);
			search(constraint, inputs, source, info);			
		}
	}

	private static void search(String constraint, String[] inputs,
			String source, CaseInfo info) throws FileNotFoundException {
		List<String> variableConstraint = getVariableConstraint(inputs);
		
//		if(!source.equals("c=a+b")){
//			return;
//			//System.out.print("");
//		}
		List<List<String>> variablesNames = getPermutation(inputs);
		boolean positiveFlag = false;
		boolean passAllPositive = true;
		for(List<String> positiveInput : info.getPositives().keySet()){
			List<String> positiveOuput = info.getPositives().get(positiveInput);
			List<List<String>> inputArray = getPermutation(positiveInput);
			List<List<String>> outputArray = getPermutation(positiveOuput);
			
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ctest/test/temp")));
			for(List<String> in : inputArray){
				for(List<String> out : outputArray){
					for(List<String> variables : variablesNames){
						List<String> mapping = new ArrayList<String>();
						for(int i = 0; i < Math.min(in.size(), variables.size()); i++){
							String c = "(assert (= " + in.get(i) + " " + variables.get(i).split(":")[1] + " ))"; 
							mapping.add(c);
						}
						for(int i = 0; i < Math.min(out.size(), variables.size()); i++){
							String c = "(assert (= " + out.get(i) + " " + variables.get(i).split(":")[1] + " ))";
							mapping.add(c);
						}
						
						if(validate(variableConstraint, mapping, constraint)){
							positiveFlag = true;
						}
					}
				}
			}
			if(positiveFlag == true){
				positiveFlag = false;
				continue;
			}
			else{
				passAllPositive = false;
				break;
			}
		}
		
		boolean negativeFlag = false;
		boolean passAllNegativeFlag = true;
		for(List<String> negativeInput : info.getNegatives().keySet()){
			List<String> negativeOuput = info.getNegatives().get(negativeInput);
			List<List<String>> inputArray = getPermutation(negativeInput);
			List<List<String>> outputArray = getPermutation(negativeOuput);
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ctest/test/temp")));
			for(List<String> in : inputArray){
				for(List<String> out : outputArray){
					for(List<String> variables : variablesNames){
						List<String> mapping = new ArrayList<String>();
						for(int i = 0; i < Math.min(in.size(), variables.size()); i++){
							String c = "(assert (= " + in.get(i) + " " + variables.get(i).split(":")[1] + " ))"; 
							mapping.add(c);
						}
						for(int i = 0; i < Math.min(out.size(), variables.size()); i++){
							String c = "(assert (= " + out.get(i) + " " + variables.get(i).split(":")[1] + " ))";
							mapping.add(c);
						}
						
						if(!validate(variableConstraint, mapping, constraint)){
							negativeFlag = true;
						}
					}
				}
			}
			if(negativeFlag == true){
				negativeFlag = false;
				continue;
			}
			else{
				passAllNegativeFlag = false;
				break;
			}
		}
		if(passAllPositive && passAllNegativeFlag)info.getResult().add(source);
	}



	private static List<List<String>> getPermutation(String[] inputs) {
		List<String> list = new ArrayList<String>();
		for(String s : inputs){
			list.add(s);
		}
		return getPermutation(list);
	}

	private static boolean validate(List<String> variableConstraint,
			List<String> mapping, String constraint) {
		try{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ctest/test/temp")));
			for(String s : variableConstraint){
				bw.write(s);
				bw.write("\n");
			}
			for(String s : mapping){
				bw.write(s);
				bw.write("\n");				
			}
			bw.write(constraint);
			bw.write("\n");	
			bw.write("(check-sat)\n");
			bw.close();
			String res = Test.invokeZ3onFile("ctest/test/temp");
			if(res.equals("sat")){
				return true;
			}
			else return false;
		}catch(Exception e){
			
		}
		return false;
	}

	private static List<String> getVariableConstraint(String[] inputs) {
		List<String> list = new ArrayList<String>();
		for(String s : inputs){
			String[] variable = s.split(":");
			String declare = "(declare-fun " + variable[1] + " () Int)";
			list.add(declare);
		}
		return list;
	}

	public static List<List<String>> getPermutation(List<String> list){
		List<List<String>> lists = new ArrayList<List<String>>();
		if(list.size() == 0){
			lists.add(new ArrayList<String>());
		}
		
		for(int i = 0; i < list.size(); i++){
			List<String> temp = new ArrayList<String>(list);
			temp.remove(i);
			List<List<String>> permutations = getPermutation(temp);
			for(List<String> t : permutations){
				t.add(list.get(i));
				lists.add(t);
			}
		}
		return lists;
	}
	
	public static void validatePermuation(){
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("1");
		list.add("2");
		list.add("3");
		List<List<String>> permutation = getPermutation(list);
		System.out.println("size:" + permutation.size());
		for(List<String> t : permutation){
			for(String s : t){
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		validatePermuation();
	}
}
