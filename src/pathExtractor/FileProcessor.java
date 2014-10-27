package pathExtractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
	private String fileName;
	private List<Method> methods;
	
	public FileProcessor(String fileName){
		this.fileName = fileName;
		methods = new ArrayList<Method>();
		parse();
	}
	
	public void print(){
		for(Method m : methods){
			for(String path : m.getPath()){
				System.out.println(path);
			}
		}
	}
	
	private void parse(){
		try {
			String com = "./PathExtractor/pathgen " + fileName;

			Process p = Runtime.getRuntime().exec(com);
			BufferedReader ls_in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String s;
			StringBuilder path = new StringBuilder();
			StringBuilder input = new StringBuilder();
			Method method = new Method();
			while((s = ls_in.readLine()) != null)
			{
				s = s.trim();
				if(s.startsWith("Processing:")){
					if(method.getName() != null){
						method.getPath().add(path.toString());
						method.getPathToInput().put(path.toString(), input.toString());
						methods.add(method);
						method = new Method();
						path = new StringBuilder();
						input = new StringBuilder();
					}
					method.setName(s.substring(12));
				}
				else if(s.startsWith("LOCAL")){					
					path.append(s.substring(6, s.length() - 1));
					path.append("\n");
				}
				else if(s.startsWith("GLOBAL")){
					path.append(s.substring(7, s.length() - 1));
					path.append("\n");
				}
				else if(s.startsWith("FORMAL")){
					input.append(s.substring(7, s.length() - 1));
					input.append("\n");
				}
				else if(s.startsWith("STMT(return")){
					path.append(s.substring(5, s.length() - 1));
					path.append("\n");
				}
				else if(s.equals("Paths:") || s.startsWith("path_enumeration")){
					continue;
				}
				else if(s.startsWith("STMT(")){
					if(s.endsWith(";)")){
						path.append(s.substring(5, s.length() - 1));
						path.append("\n");
					}
					else{
						path.append(s.substring(5, s.length()));
						path.append("\n");
					}
				}
				else if(s.endsWith(";)")){
					path.append(s.substring(0, s.length() - 1));
					path.append("\n");
				}
				else {
					path.append(s);
					path.append("\n");
				}
			}
			ls_in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args){
		FileProcessor processor = new FileProcessor("ctest/methods/test.c");
		processor.print();
	}

}