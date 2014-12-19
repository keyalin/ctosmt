package pathExtractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import db.DataBaseManager;
import db.Saver;
import translators.PathTranslator;

public class FileProcessor {
	private String fileName;
	private List<Method> methods;
	
	public FileProcessor(String fileName){
		this.fileName = fileName;
		methods = new ArrayList<Method>();
		parse();
		applySSA();
		traslate();
		save();
	}
	
	private void applySSA() {
		
		
	}

	private void save() {
		for(Method m : methods){
			Saver.save(m);
		}
		
	}

	private void traslate() {
		for(Method m : methods){
			for(String path : m.getPath())
			{
				PathTranslator pt = new PathTranslator(path);
				m.getPathToConstraint().put(path, pt.getConstraints());
			}
		}
		
	}

	public void print(){
		for(Method m : methods){
			System.out.println("method " + m.getName());
			for(String path : m.getPath()){
				System.out.println("-------");
				System.out.println(m.getPathToInput().get(path));
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
						methods.add(method);
						method = new Method();						
					}
					method.setName(s.substring(12));
				}
				else if(s.startsWith("LOCAL")){					
					path.append(s.substring(6, s.length() - 1));
					path.append(";");
					path.append("\n");
				}
				else if(s.startsWith("GLOBAL")){
					continue;
//					path.append(s.substring(7, s.length() - 1));
//					path.append("\n");
				}
				else if(s.startsWith("FORMAL")){
					input.append(s.substring(7, s.length() - 1));
					input.append("\n");
					path.append(s.substring(7, s.length() - 1));
					path.append(";");
					path.append("\n");
				}
				else if(s.startsWith("STMT(return")){
					path.append(s.substring(5, s.length() - 1));
					path.append("\n");
					
					method.getPath().add(path.toString());
					method.getPathToInput().put(path.toString(), input.toString());					
					path = new StringBuilder();
					input = new StringBuilder();
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
				else if(s.startsWith("ASSUME(")){
					path.append(s.substring(7, s.length() - 1));
					path.append(";");
					path.append("\n");
				}
				else if(s.startsWith("Number of")){
					continue;
				}
				else {
					path.append(s);
					path.append("\n");
				}
			}
			if(method.getName() != null)
			{
				methods.add(method);
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