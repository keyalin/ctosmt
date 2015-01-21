package search;

import java.util.HashMap;
import java.util.Map;

public class SQLObject {
	private String mainType;
	private String source;
	private Map<String, String> variableTrack;
	private Map<String, String> variableTypeTrack;
	private Map<String, String> introduced;
	private String constraints;
	public SQLObject() {
		source = "";
		this.variableTrack = new HashMap<String, String>();
		this.variableTypeTrack = new HashMap<String, String>();
		this.constraints = "";
		introduced = new HashMap<String, String>();
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Map<String, String> getVariableTrack() {
		return variableTrack;
	}
	public void setVariableTrack(Map<String, String> variableTrack) {
		this.variableTrack = variableTrack;
	}
	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	
	public String toString(){
		System.out.println("source: " + source);
		System.out.println(constraints);
		for(String s : this.variableTrack.keySet()){
			System.out.println(s + " : " + this.variableTrack.get(s));
		}
		System.out.println("introduced");
		for(String s : this.introduced.keySet()){
			System.out.println(s + " : " + this.introduced.get(s));
		}
		System.out.println("types" + this.mainType);
		
		return "";
	}
	public Map<String, String> getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Map<String, String> introduced) {
		this.introduced = introduced;
	}
	public Map<String, String> getVariableTypeTrack() {
		return variableTypeTrack;
	}
	public void setVariableTypeTrack(Map<String, String> variableTypeTrack) {
		this.variableTypeTrack = variableTypeTrack;
	}
	public String getMainType() {
		return mainType;
	}
	public void setMainType(String mainType) {
		this.mainType = mainType;
	}
	
	
}
