package lookups;

import java.util.ArrayList;
import java.util.List;

import pathExtractor.Method;

public class MethodSearch {
	private  List<Method> methods = new ArrayList<Method>();
	private static MethodSearch instance ;
	private MethodSearch(){
		instance = new MethodSearch();
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public static MethodSearch getInstance() {
		return instance;
	}

	
	public Method getMethod(String name){
		for(Method method : methods){
			if(method.getName().equals(name)){
				return method;
			}
		}
		return null;
	}
}
