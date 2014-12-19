package pathExtractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Method {
	private String source;
	private String name;
	private List<String> path;
	private Map<String, String> pathToInput;
	private Map<String, String> pathToConstraint;
	private Map<String, String> inputTransform;
	private static int number = 1;
	private String prefix;
	
	
	
	public Map<String, String> getInputTransform() {
		return inputTransform;
	}
	public void setInputTransform(Map<String, String> inputTransform) {
		this.inputTransform = inputTransform;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public Method(){
		source = null;
		name = null;
		path = new ArrayList<String>();
		pathToInput = new HashMap<String, String>();
		pathToConstraint = new HashMap<String, String>();
		inputTransform = new HashMap<String, String>();
		number++;
		prefix = name + number;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPath() {
		return path;
	}
	public void setPath(List<String> path) {
		this.path = path;
	}
	public Map<String, String> getPathToInput() {
		return pathToInput;
	}
	public void setPathToInput(Map<String, String> pathToInput) {
		this.pathToInput = pathToInput;
	}
	public Map<String, String> getPathToConstraint() {
		return pathToConstraint;
	}
	public void setPathToConstraint(Map<String, String> pathToConstraint) {
		this.pathToConstraint = pathToConstraint;
	}
	
	

}
