package pathExtractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Method {
	String source;
	String name;
	List<String> path;
	Map<String, String> pathToInput;
	Map<String, String> pathToConstraint;
	
	public Method(){
		source = null;
		name = null;
		path = new ArrayList<String>();
		pathToInput = new HashMap<String, String>();
		pathToConstraint = new HashMap<String, String>();
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
