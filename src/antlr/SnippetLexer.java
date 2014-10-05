// Generated from Snippet.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SnippetLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__9=1, T__8=2, T__7=3, T__6=4, T__5=5, T__4=6, T__3=7, T__2=8, T__1=9, 
		T__0=10, CallExpr=11, ID=12, Int=13, Char=14, Float=15, IntPointer=16, 
		CharPointer=17, FloatPointer=18, INT=19, FLOAT=20, WS=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'"
	};
	public static final String[] ruleNames = {
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "CallExpr", "ID", "Int", "Char", "Float", "IntPointer", "CharPointer", 
		"FloatPointer", "INT", "FLOAT", "WS"
	};


	public SnippetLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Snippet.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u008e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\7\fL\n\f\f\f\16\fO\13\f\3\f\3\f\3\r\3\r\7"+
		"\rU\n\r\f\r\16\rX\13\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\6\24|\n\24\r\24"+
		"\16\24}\3\25\6\25\u0081\n\25\r\25\16\25\u0082\3\25\3\25\3\25\3\26\6\26"+
		"\u0089\n\26\r\26\16\26\u008a\3\26\3\26\2\2\27\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27\3\2\5\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\u0094\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5/\3\2\2\2\7\61\3"+
		"\2\2\2\t8\3\2\2\2\13:\3\2\2\2\r<\3\2\2\2\17>\3\2\2\2\21@\3\2\2\2\23B\3"+
		"\2\2\2\25D\3\2\2\2\27F\3\2\2\2\31R\3\2\2\2\33Y\3\2\2\2\35]\3\2\2\2\37"+
		"b\3\2\2\2!h\3\2\2\2#m\3\2\2\2%s\3\2\2\2\'{\3\2\2\2)\u0080\3\2\2\2+\u0088"+
		"\3\2\2\2-.\7\61\2\2.\4\3\2\2\2/\60\7\'\2\2\60\6\3\2\2\2\61\62\7t\2\2\62"+
		"\63\7g\2\2\63\64\7v\2\2\64\65\7w\2\2\65\66\7t\2\2\66\67\7p\2\2\67\b\3"+
		"\2\2\289\7,\2\29\n\3\2\2\2:;\7-\2\2;\f\3\2\2\2<=\7=\2\2=\16\3\2\2\2>?"+
		"\7]\2\2?\20\3\2\2\2@A\7/\2\2A\22\3\2\2\2BC\7?\2\2C\24\3\2\2\2DE\7_\2\2"+
		"E\26\3\2\2\2FG\5\31\r\2GM\7*\2\2HL\5\31\r\2IL\5)\25\2JL\5\'\24\2KH\3\2"+
		"\2\2KI\3\2\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2"+
		"\2\2PQ\7+\2\2Q\30\3\2\2\2RV\t\2\2\2SU\t\3\2\2TS\3\2\2\2UX\3\2\2\2VT\3"+
		"\2\2\2VW\3\2\2\2W\32\3\2\2\2XV\3\2\2\2YZ\7k\2\2Z[\7p\2\2[\\\7v\2\2\\\34"+
		"\3\2\2\2]^\7e\2\2^_\7j\2\2_`\7c\2\2`a\7t\2\2a\36\3\2\2\2bc\7h\2\2cd\7"+
		"n\2\2de\7q\2\2ef\7c\2\2fg\7v\2\2g \3\2\2\2hi\7k\2\2ij\7p\2\2jk\7v\2\2"+
		"kl\7,\2\2l\"\3\2\2\2mn\7e\2\2no\7j\2\2op\7c\2\2pq\7t\2\2qr\7,\2\2r$\3"+
		"\2\2\2st\7h\2\2tu\7n\2\2uv\7q\2\2vw\7c\2\2wx\7v\2\2xy\7,\2\2y&\3\2\2\2"+
		"z|\4\62;\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~(\3\2\2\2\177\u0081"+
		"\4\62;\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\7\60\2\2\u0085\u0086\4"+
		"\62;\2\u0086*\3\2\2\2\u0087\u0089\t\4\2\2\u0088\u0087\3\2\2\2\u0089\u008a"+
		"\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008d\b\26\2\2\u008d,\3\2\2\2\t\2KMV}\u0082\u008a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}