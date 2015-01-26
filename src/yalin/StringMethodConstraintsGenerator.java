package yalin;

import java.util.ArrayList;
import java.util.List;

public class StringMethodConstraintsGenerator {
	
	//char *strcpy(char *dest, const char *src)
	public static List<String> getstrcpyConstraints(String dest, String srcContent){
		List<String> constraints = new ArrayList<String>();
		String assertion = "(assert (= " + dest +  " " + srcContent + "))";
		constraints.add(assertion);
		return constraints;
	}
	
	//char *strncpy(char *dest, const char *src)
	public static List<String> getstrncpyConstraints(String dest, String srcContent, String n){
		List<String> constraints = new ArrayList<String>();
		String length = "(assert (= (length "+ dest + ") " + n + "))";
		String assertion = "(assert (forall ((index Int)) (ite (and (>= index 0) (< index " + n + ")) (= (charOf " + dest + " index) (charOf " + srcContent  + " index)) true)))";
		constraints.add(length);
		constraints.add(assertion);
		return constraints;
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
				+ src
				+ "))))"
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
	
	//char *strncat(char *dest, const char *src, size_t n)
	public static List<String> getStrncatConstraints(String dest, String src, String n, String result){
		List<String> constraints = new ArrayList<String>();
		//String copyLengthAssertion = "(assert (= copyLength (+ (length dest) (ite (> (length src) n) (length src) n))))";
		String copyLengthAssertion = "(assert(= (length "+ result + ") " + "(+ (length " + dest +") "+ n + ")))";
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
				+ "(< index  "
				+ dest
				+ "(length " + result + " )" + "))"
				+ "(= (charOf "
				+ result
				+ " index) (charOf "
				+ src
				+ " (- index (length " + dest + "))))" + "true))))";
		String length2 = "(assert (>= (length " + dest + ") 0))";
		String length3 = "(assert (>= (length " + result + ") 0))";
		String length4 = "(assert (>= (length " + src + ") 0))";
		constraints.add(assertion);
		constraints.add(length2);
		constraints.add(length3);
		constraints.add(length4);
		constraints.add(copyLengthAssertion);
		return constraints;
	}
	
	public static void main(String[] args){
		for(String s : getStrncatConstraints("a", "b", "2", "c")){
			System.out.println(s);
		}
	}
}
