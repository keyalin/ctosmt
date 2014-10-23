package translators;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * need to redesign with method generator
 */
public class PathGenerator {
	
	private String fileName;
	
	private Map<String, String> source;
	private Map<String, List<String>> paths;
	private Map<String, List<String>> inputs;
	
	public PathGenerator(String fileName){
		this.fileName = fileName;
		this.source = new HashMap<String, String>();
		this.paths = new HashMap<String, List<String>>();
	}
	
	
	
	public void parse(){
		
		try {
			String com = "./PathExtractor/pathgen " + fileName;
			//String com = "/usr/bin/gcc -E " + fileName ;
			System.out.println(com);
			Process p = Runtime.getRuntime().exec(com);
			
			
			BufferedReader ls_in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			BufferedReader ls_err = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
		       BufferedWriter bw = new BufferedWriter(new
		                 OutputStreamWriter(new FileOutputStream("temp")));
			String s;
			String method = null;
			String path;
			List<String> input;
			while((s = ls_in.readLine()) != null){
				s = s.trim();
				if(s.startsWith("processing:")){
					if(method != null){
						inputs.put(path, input);
						//paths
					}
					method = s.substring(12);
					//path = new ArrayList<String>();
					input = new ArrayList<String>();
				}
			}
			bw.flush();
			ls_in.close();
			bw.close();
			
			
			//p.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	public static void main(String[] args) throws InterruptedException {
		PathGenerator p = new PathGenerator("ctest/methods/test.c");
		p.parse();
		Thread.sleep(1000);
	}

}
