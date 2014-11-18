package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import translators.SnippetTranslator;
import translators.StringMethodTranslator;
import utility.Utility;


public class Test {
	
	
//	int a;
//	char c[100];
//	float b;
//	char* h;
//	int* n;
//	float* k;
//	int d = 4;
//	float e = 5;
//	s;
//	char* g = "abcdefghijklmn";
//	char f = 'c';
//
//	int m = a + b;
	
// this is a test to convert the above code to constraints, the constraints will be printed out on console
	@org.junit.Test
	public void test1() throws IOException{
		String s = Utility.getStringFromFile("ctest/snippets/snippet2.txt");
		SnippetTranslator translator = new SnippetTranslator(s);
		for(String c : translator.getConstraints()){
			System.out.println(c);
		}
	}
	
	
	// this is a test case for printing out strcmp function in c
	// In order not to print out too many constraints, only the constraints of comparsion part is printed out
	@org.junit.Test
	public void CTest2(){
		String a = "abcdefg";
		String b = "98910";
		String result = StringMethodTranslator.getStrcmpConstraints("a", a, "b", b);
		System.out.println(result);
	}
	
	/**
	 * search
	 * @throws IOException 
	 * There are five c functions for being searched
	 * you can change the inputs and output in 'searchFunction(int[], int)'
	 * the first paramter is an array of integer inputs, the second paramter is output
	 * The suitable functions will be printed on console.
	 * It may take a while for the searching process.
	 * 
	 * int Doubled(int a){
	 * 		int c = 2 * a;
	*		return c;
	*	}
	*	
	*	int minus(int a, int b){
	*		int c = a - b;
	*		return c;
	*	}
	*	
	*	int sum(int a, int b)
	*	{
	*		
	*		int c = a + b;
	*		return c;
	*	}
	*	
	*	int muliptly(int a, int b){
	*		int c = a * b;
	*		return c;
	*	}
	*	
	*	int divide(int a, int b){
	*		int c = a / b;
	*		int d = abs(a);
	*		return c;
	*	}
	 */
	@org.junit.Test
	public void CTest3() throws IOException{
		List<String> result = searchFunction(new int[]{2, 2}, 4);// inputs are 2, 2 and output is 4
		
		for(String s : result){
			System.out.println(s);
		}
	}
	
	
	public List<String> searchFunction(int[] inputs, int output) throws IOException{
		File list = new File("CSMT");
		List<String> functions = new ArrayList<String>();
		for(String fileName : list.list()){
			if(fileName.equals("Z3")) continue;
			File tempFile = new File("temp");
			if(tempFile.exists()) tempFile.delete();
			tempFile.createNewFile();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("CSMT/" + fileName)));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)));
			String s;
			for(int i = 0; i < inputs.length; i++){
				s = reader.readLine();
				writer.write(s + "\n");
				s = reader.readLine();
				writer.write(s + " " + inputs[i] + "))\n");
			}
			s = reader.readLine();
			writer.write(s + "\n");
			s = reader.readLine();
			writer.write(s + " " + output + "))\n");
			while((s = reader.readLine()) != null){
				writer.write(s + "\n");
			}
			reader.close();
			writer.close();
			String result = invokeZ3onFile(tempFile.getName());
			if(result.equals("sat")) functions.add(fileName.substring(0, fileName.lastIndexOf('.')));
			
		}
		
		return functions;
	}
	
	@org.junit.Test
	public void test4()
	{
		System.out.println(invokeZ3onFile("CSMT/test.smt"));
	}
	
	public static String invokeZ3onFile(String file) {
		String out = "";
		String execString = "CSMT/z3"
				+ " -smt2 -nw -file:" + file;
		String ls_str;
		StringBuffer sb = new StringBuffer();
		try {
			Process ls_proc = Runtime.getRuntime().exec(execString);

			BufferedReader ls_in = new BufferedReader(new InputStreamReader(
					ls_proc.getInputStream()));
//			BufferedReader ls_err = new BufferedReader(new InputStreamReader(
//					ls_proc.get));

			long now = System.currentTimeMillis();
			long timeoutInMillis = 100L * 10; // timeout in seconds
			long finish = now + timeoutInMillis;

			try {
				while (isAlive(ls_proc)
						&& (System.currentTimeMillis() < finish)) {
					Thread.sleep(10);
				}
				if (isAlive(ls_proc)) {
					ls_proc.destroy();
					sb.append("unknown - killed");
				}
				while ((ls_str = ls_in.readLine()) != null) {
					sb.append(ls_str);
					// System.out.println(ls_str);
				}
//				while((ls_str = ls_err.readLine()) != null){
//					System.out.println(ls_str+ "j");
//					sb.append(ls_str);
//				}
			} catch (IOException e) {
				out = sb.toString();
				// System.exit(0);
			} catch (InterruptedException e) {
				out = sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		out = sb.toString();
		return out;
	}
	
	public static boolean isAlive(Process p) {
		try {
			p.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

}
