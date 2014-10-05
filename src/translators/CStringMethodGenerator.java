package translators;

import java.util.List;

public class CStringMethodGenerator {
	
	//This is used to generate constraints for 
	//char* str3 = strcat(Str1, str2);
	
	public List<String> getStrcat(String str1, String str2, String str3){
		str3 = str1 + str2;
		return new StringRepresetationGenerator(str3).getConstraints();
	}

	public static void main(String[] args) {
		

	}

}
