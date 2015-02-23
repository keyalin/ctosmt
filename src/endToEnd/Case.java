package endToEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import search.PrototypeSearch;
import yalin.CTest;
import yalin.CaseInfo;
import yalin.PrototypeTest;

public class Case {
	private Map<String, String> positive;
	private Map<String, String> negative;
	private CaseInfo info;
	private String fileTag;
	private int start;
	private int end;
	
	public Case(String fileTag){
		this.fileTag = fileTag;
		this.positive = new HashMap<String, String>();
		this.negative = new HashMap<String, String>();
		this.info = new CaseInfo();
		IO();
		fillCaseInfo();
		search();
		ecaminResult();
		printResult();
	}

	private void printResult() {
		for(String s : info.getMapping().keySet()){			
			String newCode = s.substring(s.indexOf("\n"));
			Set<String> set = new HashSet<String>();
			for(String left : info.getMapping().get(s).keySet()){
				String l = left.substring(0, left.indexOf("_"));
				if(set.contains(l)) continue;
				else{
					String r = info.getMapping().get(s).get(left);
					newCode.replace(r, l);
				}
			}
			System.out.println(newCode);
			String filePath = replace(newCode);
			if(run(filePath ,info)){
				System.out.println(true);
			}
		}
		
	}

	private boolean run(String filePath, CaseInfo info2) {
		//String sourceFile = this.fileTag + ".c";
		boolean pass = true;
		for(String input : this.positive.keySet()){
			String output = this.positive.get(input);
			String command1 = "gcc " + filePath + " -o " + this.fileTag;
			String command2 = "./" + this.fileTag + " " +  input;
			String s1 = CTest.runCProgram(command1);
			String s2 = CTest.runCProgram(command2);
			//System.out.println(s2);
			int index1 = s2.indexOf("input");
			int index2 = s2.indexOf("output");
			int index3 = s2.indexOf("return");
			
			if(!output.equals(s2.substring(index3 + 7).trim())){
				pass =false;
				break;
			}
						//info.getPositives().put(inputList, outputList);
		}
		if(!pass) return false;
		
		for(String input : this.negative.keySet()){
			String output = this.negative.get(input);
			String command1 = "gcc " + filePath + " -o " + this.fileTag;
			String command2 = "./" + this.fileTag + " " +  input;
			String s1 = CTest.runCProgram(command1);
			String s2 = CTest.runCProgram(command2);
			//System.out.println(s2);
			int index1 = s2.indexOf("input");
			int index2 = s2.indexOf("output");
			int index3 = s2.indexOf("return");
			
			if(output.equals(s2.substring(index3 + 7).trim())){
				pass =false;
				break;
			}
						//info.getPositives().put(inputList, outputList);
		}
		if(!pass) return false;
		return true;
		
//		for(String input : this.negative.keySet()){
//			String command1 = "gcc " + sourceFile + " -o " + this.fileTag;
//			String command2 = "./" + this.fileTag + " " +  input;
//			String s1 = CTest.runCProgram(command1);
//			String s2 = CTest.runCProgram(command2);
//			//System.out.println(s2);
//			int index1 = s2.indexOf("input");
//			int index2 = s2.indexOf("output");
//			int index3 = s2.indexOf("return");
//			
//			List<String> inputList = new ArrayList<String>();
//			List<String> outputList = new ArrayList<String>();
//			
//			String[] elems = s2.substring(index1 + 12, index2).split(",");
//			for(String e : elems){
//				if(e.equals("")) continue;
//				inputList.add(e);				
//			}
//			for(String o : s2.substring(index2 + 13, index3).split(",")){
//				if(o.equals("")) continue;
//				outputList.add(o);
//			}
//			info.getNegatives().put(inputList, outputList);
//		}
//		info.print();
		
	}

	private String replace(String newCode) {
		String sourceFile = this.fileTag + ".c";
		String newFile = this.fileTag + "new.c";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
			String line = null;
			for(int i = 1; i < start; i++){
				line = br.readLine();
				bw.write(line);
				bw.write("\n");
				bw.flush();
			}
			//System.out.println(start);
			for(int i = start; i<=start; i++){
				line = br.readLine();
			}
			
			bw.write(newCode);
			bw.write("\n");
			bw.flush();
			
			while((line = br.readLine()) != null){
				bw.write(line);
				bw.write("\n");
				bw.flush();
			}
			bw.close();
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newFile;
	}

	private void ecaminResult() {
		
		
	}

	private void search(){
		try {
			PrototypeSearch.search(info);
			//PrototypeTest.printResult(info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void fillCaseInfo() {
		String sourceFile = this.fileTag + ".c";
		for(String input : this.positive.keySet()){
			//String output = this.positive.get(input);
			String command1 = "gcc " + sourceFile + " -o " + this.fileTag;
			String command2 = "./" + this.fileTag + " " +  input;
			String s1 = CTest.runCProgram(command1);
			String s2 = CTest.runCProgram(command2);
			//System.out.println(s2);
			int index1 = s2.indexOf("input");
			int index2 = s2.indexOf("output");
			int index3 = s2.indexOf("return");
			
			List<String> inputList = new ArrayList<String>();
			List<String> outputList = new ArrayList<String>();
			
			String[] elems = s2.substring(index1 + 12, index2).split(",");
			for(String e : elems){
				if(e.equals("")) continue;
				inputList.add(e);				
			}
			for(String o : s2.substring(index2 + 13, index3).split(",")){
				if(o.equals("")) continue;
				outputList.add(o);
			}
			info.getPositives().put(inputList, outputList);
		}
		
		for(String input : this.negative.keySet()){
			String command1 = "gcc " + sourceFile + " -o " + this.fileTag;
			String command2 = "./" + this.fileTag + " " +  input;
			String s1 = CTest.runCProgram(command1);
			String s2 = CTest.runCProgram(command2);
			//System.out.println(s2);
			int index1 = s2.indexOf("input");
			int index2 = s2.indexOf("output");
			int index3 = s2.indexOf("return");
			
			List<String> inputList = new ArrayList<String>();
			List<String> outputList = new ArrayList<String>();
			
			String[] elems = s2.substring(index1 + 12, index2).split(",");
			for(String e : elems){
				if(e.equals("")) continue;
				inputList.add(e);				
			}
			for(String o : s2.substring(index2 + 13, index3).split(",")){
				if(o.equals("")) continue;
				outputList.add(o);
			}
			info.getNegatives().put(inputList, outputList);
		}
		//info.print();
	}

	private String getCommand(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	private void IO() {
		try{
			String caseFile = this.fileTag + "_TS";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caseFile)));
			String line = null;
			boolean neg = false;
			while((line = br.readLine()) != null){
				line = line.trim();
				if(line.startsWith("positive")){
					neg = false;
				}
				else if(line.startsWith("negative")){
					neg = true;
				}
				else if(line.startsWith("buggy line")){
					String[] buggy = line.substring(11).split("-");
					start = Integer.valueOf(buggy[0]);
					end = Integer.valueOf(buggy[1]);
				}
				else if(line.startsWith("input")){
					int index = line.indexOf("output");
					String input = line.substring(6, index);
					String output = line.substring(index + 7);
					if(neg){
						this.negative.put(input.trim(), output.trim());
					}
					else{
						this.positive.put(input.trim(), output.trim());
					}
				}
				else{
					continue;
				}
			}
			br.close();
		}catch(Exception e){
			
		}
		
	}
	
	
	
	
	public Map<String, String> getPositive() {
		return positive;
	}

	public void setPositive(Map<String, String> positive) {
		this.positive = positive;
	}

	public Map<String, String> getNegative() {
		return negative;
	}

	public void setNegative(Map<String, String> negative) {
		this.negative = negative;
	}

	public CaseInfo getInfo() {
		return info;
	}

	public void setInfo(CaseInfo info) {
		this.info = info;
	}

	public String getFileTag() {
		return fileTag;
	}

	public void setFileTag(String fileTag) {
		this.fileTag = fileTag;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public static void main(String[] args){
		Case c = new Case("endtestcases/case1");
//		for(String s : c.getPositive().keySet()){
//			System.out.println(s + c.getPositive().get(s));
//		}
//		for(String s : c.getNegative().keySet()){
//			System.out.println(s + c.getNegative().get(s));
//		}
	}
}
