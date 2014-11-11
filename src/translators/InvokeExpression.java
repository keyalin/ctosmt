package translators;

import java.util.ArrayList;
import java.util.List;

public class InvokeExpression {
	
	public static List<String> getConstraints(String methodName, Arguments args, String result){
		List<String> constraints = new ArrayList<String>();
		if(methodName.equals("strcmp")){
			String left = args.getArgs().get(0);
			String right = args.getArgs().get(1);
			
		}
		else if(methodName.equals("strcpy")){
			String left = args.getArgs().get(0);
			String right = args.getArgs().get(1);			
		}
		return null;
	}
	
}
