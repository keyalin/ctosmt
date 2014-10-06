package translators;

import java.util.ArrayList;
import java.util.List;

public class StringRepresetationGenerator {
	private String ID;
	private String primitive;
	private List<String> constraints;
	public StringRepresetationGenerator(String ID, String primitive){
		this.primitive = primitive;
		this.ID = ID;
		constraints = new ArrayList<String>();
		generateConstraints();
	}
	
	
	
	
	
	public String getID() {
		return ID;
	}





	public void setID(String iD) {
		ID = iD;
	}





	public String getPrimitive() {
		return primitive;
	}





	public void setPrimitive(String primitive) {
		this.primitive = primitive;
	}





	public List<String> getConstraints() {
		return constraints;
	}





	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}





	private void generateConstraints(){
		constraints.add(getDeclareConstraint(ID));
		constraints.add(getLengthConstraints(ID, primitive));
		for(int i = 0; i < primitive.length(); i++){
			constraints.add(getCharOfConstraint(ID, primitive,i));
		}
	}
	
	public static String getDeclareConstraint(String ID){
		String constraint = "(declare-fun " + ID + " () String)";
		return constraint;
	}
	
	public static String getCharOfConstraint(String ID, String primitive, int index){
		String constraint = "(assert (= (charOf " + ID +  " " + index + ") " + encodeLetter(primitive.charAt(index)) + " ))";
		return constraint;
	}
	
	public String toString(){
		String s = "";
		for(String str : constraints){
			s = s + str + "\n";
		}
		return s;
	}
	
	
	
	public static String getLengthConstraints(String ID, String primitive){
		String constraint = "(assert (= (length " + ID + ") " + primitive.length() + "))";
		return constraint;
	}
	
	public static void main(String[] args){
		//StringRepresetationGenerator g = new StringRepresetationGenerator("p", "abcd34");
		System.out.println(Integer.toString(100));
	}
	
	
	public static String encodeLetter(char c) {
		if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e'
				|| c == 'f' || c == 'g' || c == 'h' || c == 'i' || c == 'j'
				|| c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o'
				|| c == 'p' || c == 'q' || c == 'r' || c == 's' || c == 't'
				|| c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y'
				|| c == 'z' || c == 'A' || c == 'B' || c == 'C' || c == 'D'
				|| c == 'E' || c == 'F' || c == 'G' || c == 'H' || c == 'I'
				|| c == 'J' || c == 'K' || c == 'L' || c == 'M' || c == 'N'
				|| c == 'O' || c == 'P' || c == 'Q' || c == 'R' || c == 'S'
				|| c == 'T' || c == 'U' || c == 'V' || c == 'W' || c == 'X'
				|| c == 'Y' || c == 'Z' || c == '1' || c == '2' || c == '3'
				|| c == '4' || c == '5' || c == '6' || c == '7' || c == '8'
				|| c == '9' || c == '0') {
			return "_" + c + "_";
		} else if (c == ':' || c == '/' || c == '_' || c == ',' || c == '.'
				|| c == '\\' || c == '\'' || c == '"' || c == '-' || c == ' '
				|| c == '?' || c == '(' || c == ')' || c == ';' || c == '{'
				|| c == '@' || c == '}' || c == '=' || c == '[' || c == '%'
				|| c == ']' || c == '&' || c == '!' || c == '#' || c == '+'
				|| c == '*' || c == '^' || c == '~' || c == '`' || c == '<'
				|| c == '>' || c == '|' || c == '$' || c == '\n' || c == '\b'
				|| c == '\t' || c == '\f' || c == '\r') {
			switch (c) {
			case '|':
				return "_mid_";
			case '+':
				return "_plus_";
			case '*':
				return "_star_";
			case '^':
				return "_carrot_";
			case '~':
				return "_tilde_";
			case '`':
				return "_backtick_";
			case '<':
				return "_langle_";
			case '>':
				return "_rangle_";
			case ':':
				return "_colon_";
			case '/':
				return "_fwdslash_";
			case '_':
				return "_underscore_";
			case ',':
				return "_comma_";
			case '.':
				return "_period_";
			case '"':
				return "_dblquote_";
			case '\'':
				return "_siglequote_";
			case '\\':
				return "_backslash_";
			case '-':
				return "_dash_";
			case ' ':
				return "_space_";
			case '?':
				return "_question_";
			case '(':
				return "_openparen_";
			case ')':
				return "_closeparen_";
			case ';':
				return "_semicolon_";
			case '{':
				return "_lcurly_";
			case '}':
				return "_rcurly_";
			case '=':
				return "_equals_";
			case '@':
				return "_at_";
			case '[':
				return "_lbracket_";
			case ']':
				return "_rbracket_";
			case '&':
				return "_amp_";
			case '!':
				return "_excl_";
			case '#':
				return "_pound_";
			case '%':
				return "_percent_";
			case '$':
				return "_dollar_";
			case '\n':
				return "_newline_";
			case '\t':
				return "_tab_";
			case '\r':
				return "_return_";
			case '\f':
				return "_slashf_";
			case '\b':
				return "_slashb_";
			}
		}
		System.err.println("Unsupported: " + c);
		return "";
	}

}
