package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import search.PrototypeSearch;

public class PrototypeTest {
	
	@org.junit.Test
	public void test1() throws SQLException, IOException{
		CaseInfo info = PrototypeTest.parse("Prototype/test cases/case1");
		PrototypeSearch.search(info);
		for(String s : info.getResult()){
			System.out.println(s);
		}
	}
	
	@org.junit.Test
	public void test2() throws SQLException, IOException{
		CaseInfo info = PrototypeTest.parse("Prototype/test cases/case2");
		info.print();
		PrototypeSearch.search(info);
		for(String s : info.getResult()){
			System.out.println(s);
		}		
	}
	
	public static CaseInfo parse(String fileName){
		CaseInfo info = new CaseInfo();
		try{
			boolean positive = false;
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String s = null;
			while((s = reader.readLine()) != null){
				String sb = s.trim();
				if(sb.isEmpty()) continue;
				else if(sb.startsWith("Positive")){
					positive = true;
					continue;
				}
				else if(sb.startsWith("Negative")){
					positive = false;
				}
				else if(sb.startsWith("input state:")){
					List<String> input = new ArrayList<String>();
					List<String> output = new ArrayList<String>();
					for(String i : sb.substring(12).split(" ")){
						input.add(i);
					}
					sb = reader.readLine().trim();
					for(String o : sb.substring(13).split(" ")){
						output.add(o);
					}
					if(positive){
						info.getPositives().put(input, output);
					}
					else{
						info.getNegatives().put(input, output);
					}
				}
				else if(sb.startsWith("buggy line")){
					break;
				}
			}
			reader.close();
		}catch(Exception e){
			
		}
		return info;
		
	}
	
	
	public static void main(String[] args){
		CaseInfo info = PrototypeTest.parse("Prototype/test cases/case1");
		info.print();
	}
}
