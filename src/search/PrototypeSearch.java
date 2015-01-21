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

import lookups.TypeTable;
import test.CaseInfo;
import test.Test;
import translators.StringRepresentation;
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
			String introduced= result.getString(4);
			String type = result.getString(5);
			search(constraint, inputs, source, info, introduced, type);			
		}
	}

	private static void search(String constraint, String[] inputs,
			String source, CaseInfo info, String introduced, String type) throws FileNotFoundException {
		
		
		
		if(!info.getType().equals(type)) return;
		List<String> variableConstraint = getVariableConstraint(inputs, type);
		boolean passAllPositive = true;
		boolean positiveFlag = false;
		for(List<String> positiveInput : info.getPositives().keySet()){
			List<String> positiveOuput = info.getPositives().get(positiveInput);
			if(inputs.length > positiveOuput.size()) return;
			List<String> stateConstraint = getStateConstraint(positiveOuput);
			List<List<String>> outputArray = getPermutation(positiveOuput);
			
			for(List<String> out : outputArray){
					List<String> mapping = new ArrayList<String>();
					for(int i = 0; i < inputs.length; i++){
						String c = "(assert (= " + out.get(i).split(" ")[0] + " " + inputs[i].split(":")[1] + " ))"; 
						
						mapping.add(c);
					}

					
					if(validate(variableConstraint, mapping, constraint, stateConstraint)){
						positiveFlag = true;
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
			List<String> negativeOutput = info.getNegatives().get(negativeInput);
			if(inputs.length > negativeOutput.size()) return;
			List<List<String>> outputArray = getPermutation(negativeOutput);
			List<String> stateConstraint = getStateConstraint(negativeOutput);
				for(List<String> out : outputArray){
					List<String> mapping = new ArrayList<String>();
					
					for(int i = 0; i < inputs.length; i++){
						String c = "(assert (= " + out.get(i).split(" ")[0] + " " + inputs[i].split(":")[1] + " ))"; 
						mapping.add(c);
					}
					
					if(!validate(variableConstraint, mapping, constraint, stateConstraint)){
						negativeFlag = true;
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



	private static List<String> getStateConstraint(List<String> states) {
		List<String> list = new ArrayList<String>();
		for(String s : states){
			String[] con = s.split(" ");
			String type = TypeTable.getInstance().getType(con[2]);
			String id = con[0];
			String value = con[1];
			if(!type.equals("String"))
			{	
				String delcare = "(declare-fun " + id + " () " + type+ ")";
				String assign = "(assert (= " + id + " " + value +"))";
				list.add(delcare);
				list.add(assign);
			}
			else{
				StringRepresentation rep = new StringRepresentation(id, value);
				list.addAll(rep.getConstraints());
			}
		}
		return list;
	}

	private static List<List<String>> getPermutation(String[] inputs) {
		List<String> list = new ArrayList<String>();
		for(String s : inputs){
			list.add(s);
		}
		return getPermutation(list);
	}

	private static boolean validate(List<String> variableConstraint,
			List<String> mapping, String constraint, List<String> stateConstraint) {
		try{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ctest/test/temp")));
			SearchManager.loadPrototype(bw);
			bw.flush();
			for(String s : variableConstraint){
				bw.write(s);
				bw.write("\n");
			}
			bw.flush();
			for(String s : stateConstraint){
				bw.write(s);
				bw.write("\n");	
			}
			bw.flush();
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

	private static List<String> getVariableConstraint(String[] inputs, String type) {
		List<String> list = new ArrayList<String>();
		for(String s : inputs){
			String[] variable = s.split(":");
			String declare = "(declare-fun " + variable[1] + " () " + TypeTable.getInstance().getType(type)+ ")";
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
