package yalin;

import java.util.Map;

public class EntryObject {
	private String source;
	private String Constraint;
	private Map<String, String> variablesTypes;
	private Map<String, String> variableTrack;
	private Map<String, String> variableMap;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getConstraint() {
		return Constraint;
	}
	public void setConstraint(String constraint) {
		Constraint = constraint;
	}


	public Map<String, String> getVariableTrack() {
		return variableTrack;
	}
	public void setVariableTrack(Map<String, String> variableTrack) {
		this.variableTrack = variableTrack;
	}
	public Map<String, String> getVariablesTypes() {
		return variablesTypes;
	}
	public void setVariablesTypes(Map<String, String> variablesTypes) {
		this.variablesTypes = variablesTypes;
	}
	public Map<String, String> getVariableMap() {
		return variableMap;
	}
	public void setVariableMap(Map<String, String> variableMap) {
		this.variableMap = variableMap;
	}
	
	
	public void print(){
		System.out.println("source:");
		System.out.println(source);
		System.out.println("constraint");
		System.out.println(Constraint);
		System.out.println("types");
		for(String s : this.variablesTypes.keySet()){
			System.out.println(s + ":" + this.variablesTypes.get(s));
		}
		System.out.println("map");
		for(String s : this.variableMap.keySet()){
			System.out.println(s + ":" + this.variableMap.get(s));
		}
		System.out.println("track");
		for(String s : this.variableTrack.keySet()){
			System.out.println(s + ":" + this.variableTrack.get(s));
		}
	}

}
