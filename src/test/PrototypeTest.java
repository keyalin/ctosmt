package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import search.PrototypeSearch;
import yalin.CaseInfo;

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
		PrototypeSearch.search(info);
		for(String s : info.getResult()){
			System.out.println(s);
		}
	}
	
	@org.junit.Test
	public void test3() throws SQLException, IOException{
		CaseInfo info = PrototypeTest.parse("Prototype/test cases/case3");
		PrototypeSearch.search(info);
		for(String s : info.getResult()){
			System.out.println(s);
		}
	}
	
	@org.junit.Test
	public void test4() throws SQLException, IOException{
		CaseInfo info = PrototypeTest.parse("Prototype/test cases/case4");
		PrototypeSearch.search(info);
		for(String s : info.getResult()){
			System.out.println(s);
		}
	}
	
	@org.junit.Test
	public void test5() throws SQLException, IOException{
		CaseInfo info = PrototypeTest.parse("Prototype/test cases/case5");
		PrototypeSearch.search(info);
		for(String s : info.getResult()){
			System.out.println(s);
		}
	}
	
	@org.junit.Test
	public void test6() throws SQLException, IOException{
		CaseInfo info = PrototypeTest.parse("Prototype/test cases/case6");
		PrototypeSearch.search(info);
		for(String s : info.getResult()){
			System.out.println(s);
		}
	}
	
	public static CaseInfo parse(String fileName){
		CaseInfo info = new CaseInfo();
		String type = null;
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
					String[] elems = sb.substring(12).split(",");
					for(String i : elems){
						if(i.equals("")) continue;
						input.add(i);
						
					}
					//input
					sb = reader.readLine().trim();
					for(String o : sb.substring(13).split(",")){
						if(o.equals("")) continue;
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
