package search;

import java.util.HashMap;
import java.util.Map;

public class SQLObject {
	private String source;
	private Map<String, String> variableTrack;
	private String constraints;
	public SQLObject() {
		source = "";
		this.variableTrack = new HashMap<String, String>();
		this.constraints = "";
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
		return "";
	}
	
}
