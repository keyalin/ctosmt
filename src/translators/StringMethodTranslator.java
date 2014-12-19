package translators;

import java.util.ArrayList;
import java.util.HashSet;
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
		
		
		String constraint = "(\nif\n"
								+ "\t(= (compare " + left + " " + right + "  " + i + " ) 0) "
								+ compare(left, leftContent, right, rightContent, i + 1)
								+ " \n " 
									+ "\n(if\n"
										+ "\t(= (compare " + left + " " + right + " " + i + " ) 1) "
										+ "1"
										+ "  -1)) ";
		return constraint;
										
									
	}
	
	//char * strcat ( char * destination, const char * source );
	public static List<String> getStrcatConstraints(String dest, String src, String result){
		List<String> constraints = new ArrayList<String>();
		String assertion = "(assert " + "(forall ((index Int))" + "(ite "
				+ "(and" + "(>= index 0)" + "(< index (length "
				+ dest
				+ ")))"
				+ "(= (charOf "
				+ result
				+ " index) (charOf "
				+ dest
				+ " index))"
				+ "(ite "
				+ "(and"
				+ "(>= index (length "
				+ dest
				+ "))"
				+ "(< index (+ (length "
				+ dest
				+ ") (length "
				+ dest
				+ "))))"
				+ "(= (charOf "
				+ result
				+ " index) (charOf "
				+ dest
				+ " (- index (length " + dest + "))))" + "true))))";
		String length = "(assert (= (length " + result + ") (+ (length " + dest
				+ ") (length " + src + "))))";
		String length2 = "(assert (>= (length " + dest + ") 0))";
		String length3 = "(assert (>= (length " + result + ") 0))";
		String length4 = "(assert (>= (length " + src + ") 0))";
		constraints.add(assertion);
		constraints.add(length);
		constraints.add(length2);
		constraints.add(length3);
		constraints.add(length4);
		return constraints;
	}
	
	//char *strncat(char *dest, const char *src, size_t n)
	public static List<String> getStrncatConstraints(String dest, String src, int n, String result){
		List<String> constraints = new ArrayList<String>();
		String copyLengthAssertion = "(assert (= copyLength (+ (length dest) (ite (> (length src) n) (length src) n))))";
		String assertion = "(assert " + "(forall ((index Int))" + "(ite "
				+ "(and" + "(>= index 0)" + "(< index (length "
				+ dest
				+ ")))"
				+ "(= (charOf "
				+ result
				+ " index) (charOf "
				+ dest
				+ " index))"
				+ "(ite "
				+ "(and"
				+ "(>= index (length "
				+ dest
				+ "))"
				+ "(< index (+ (length "
				+ dest
				+ ") copyLength)))"
				+ "(= (charOf "
				+ result
				+ " index) (charOf "
				+ src
				+ " (- index (length " + dest + "))))" + "true))))";
		String length = "(assert (= (length " + result + ") (+ (length " + dest
				+ ") (length " + src + "))))";
		String length2 = "(assert (>= (length " + dest + ") 0))";
		String length3 = "(assert (>= (length " + result + ") 0))";
		String length4 = "(assert (>= (length " + src + ") 0))";
		constraints.add(assertion);
		constraints.add(length);
		constraints.add(length2);
		constraints.add(length3);
		constraints.add(length4);
		return constraints;
	}
	
	//char *strchr(const char *str, int c)
	//
	public static List<String> getStrchrConstratints(String src, String srcContent, int c){
		String output = "_" + src + "_" + count++;
		StringReturnTable.getInstance().set(src, output);
		int index = src.indexOf(c);
		if(index == -1){
			
			String primitive = "";
			return new StringRepresetation(output, primitive).getConstraints();			
		}
		else{
			String primitive = src.substring(index);
			return new StringRepresetation(output, primitive).getConstraints();
		}
	}
	

	
	public static void main(String[] args){
		//System.out.print(getStrcmpConstraints("inputString", "abcd", "staticString", "abc"));
		System.out.println(getStrcmpConstraints("inputString", "abc", "staticString", "abcd"));
		//System.out.println(getstrncpyConstraints("inputString", "staticString", 4).get(1));
	}
	
	//char *strcpy(char *dest, const char *src)
	public static List<String> getstrcpyConstraints(String dest, String srcContent){
		List<String> constraints = new ArrayList<String>();
		String assertion = "(assert (= " + dest +  " " + srcContent + "))";
		constraints.add(assertion);
		return constraints;
	}
	
	//char *strncpy(char *dest, const char *src)
	public static List<String> getstrncpyConstraints(String dest, String srcContent, int n){
		List<String> constraints = new ArrayList<String>();
		String length = "(assert (= (length "+ dest + ") (+ " + n + " (length " + srcContent + ")))";
		String assertion = "(assert (forall ((index Int)) (ite (and (>= index 0) (< index " + n + ")) (= (charOf " + dest + " index) (charOf " + srcContent  + " index)) true)))";
		constraints.add(length);
		constraints.add(assertion);
		return constraints;
	}
	

}
