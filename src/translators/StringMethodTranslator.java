package translators;

import java.util.List;

import lookups.StringReturnTable;

public class StringMethodTranslator {
	
	private static int count = 0;
	
	//int strcmp(const char *str1, const char *str2)
	
	public static String getStrcmpConstraints(String left, String leftContent, String right, String rightContent){
		return compare(left, leftContent, right, rightContent, 0);
	}
	
	//int strncmp(const char *str1, const char *str2, size_t n)

	public static String getStrcmpConstraints(String left, String leftContent, String right, String rightContent, int n){
		if(n < leftContent.length()) {
			leftContent = leftContent.substring(0, n);
		}
		if(n < rightContent.length()){
			rightContent = rightContent.substring(0, n);
		}
		return compare(left, leftContent, right, rightContent, 0);
	}
	
	private static String compare(String left, String leftContent, String right, String rightContent, int i){
		if(i == leftContent.length() || i == rightContent.length()){
			if(leftContent.length() > rightContent.length()) return "1";
			else if(leftContent.length() == rightContent.length()) return "0";
			else return "-1";
		}
		
		
		String constraint = "(\nite\n"
								+ "\t(= compare (" + left + " " + right + "  " + i + " ) 0) "
								+ compare(left, leftContent, right, rightContent, i + 1)
								+ " \nthen " 
									+ "\n(ite\n"
										+ "\t(= compare (" + left + " " + right + " " + i + " ) 1) "
										+ "1)"
										+ " then -1)) ";
		return constraint;
										
									
	}
	
	//char* char *strcat(char *dest, const char *src)
	/**
	 * 
	 * @param dest ID of dest
	 * @param destContent the sequence
	 * @param src ID of src
	 * @param srcContent the character sequence
	 * @return
	 */
	public static List<String> getStrcatConstraints(String dest, String destContent, String src, String srcContent){
		String output = "_" + dest + "_" + count++;
		StringReturnTable.getInstance().set(dest, output);
		String primitive = destContent + srcContent;
		return new StringRepresetationGenerator(output, primitive).getConstraints();
	}
	
	//char *strncat(char *dest, const char *src, size_t n)
	public static List<String> getStrncatConstraints(String dest, String destContent, String src, String srcContent, int n){
		if(n < srcContent.length()){
			srcContent = srcContent.substring(0, n);
		}
		String output = "_" + dest + "_" + count++;
		StringReturnTable.getInstance().set(dest, output);
		String primitive = destContent + srcContent;
		return new StringRepresetationGenerator(output, primitive).getConstraints();
	}
	
	//char *strchr(const char *str, int c)
	
	public static List<String> getStrchrConstratints(String src, String srcContent, int c){
		String output = "_" + src + "_" + count++;
		StringReturnTable.getInstance().set(src, output);
		int index = src.indexOf(c);
		if(index == -1){
			
			String primitive = "";
			return new StringRepresetationGenerator(output, primitive).getConstraints();			
		}
		else{
			String primitive = src.substring(index);
			return new StringRepresetationGenerator(output, primitive).getConstraints();
		}
	}
	

	
	public static void main(String[] args){
		System.out.print(getStrcmpConstraints("left", "a", "right", "f"));
	}
	
	//char *strcpy(char *dest, const char *src)
	public static List<String> getstrcpyConstraints(String dest, String srcContent){
		String output = "_" + dest + "_" + count++;
		StringReturnTable.getInstance().set(dest, output);
		String primitive =  srcContent;
		return new StringRepresetationGenerator(output, primitive).getConstraints();
	}
	
	//char *strncpy(char *dest, const char *src)
	public static List<String> getstrncpyConstraints(String dest, String srcContent, int n){
		if(n < srcContent.length()){
			srcContent = srcContent.substring(0, n);
		}
		String output = "_" + dest + "_" + count++;
		StringReturnTable.getInstance().set(dest, output);
		String primitive =  srcContent;
		return new StringRepresetationGenerator(output, primitive).getConstraints();
	}
	

}
