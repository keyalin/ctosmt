// Generated from Snippet.g4 by ANTLR 4.4
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SnippetParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__9=1, T__8=2, T__7=3, T__6=4, T__5=5, T__4=6, T__3=7, T__2=8, T__1=9, 
		T__0=10, CallExpr=11, ID=12, Int=13, Char=14, Float=15, IntPointer=16, 
		CharPointer=17, FloatPointer=18, INT=19, FLOAT=20, WS=21;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'%'", "'return'", "'*'", "'+'", "';'", "'['", "'-'", 
		"'='", "']'", "CallExpr", "ID", "'int'", "'char'", "'float'", "'int*'", 
		"'char*'", "'float*'", "INT", "FLOAT", "WS"
	};
	public static final int
		RULE_prog = 0, RULE_statement = 1, RULE_declarationStat = 2, RULE_assignStat = 3, 
		RULE_returnStat = 4, RULE_exprStat = 5, RULE_expr = 6, RULE_type = 7, 
		RULE_builtin = 8, RULE_pointers = 9, RULE_string = 10, RULE_addictiveOperator = 11, 
		RULE_multiOperater = 12;
	public static final String[] ruleNames = {
		"prog", "statement", "declarationStat", "assignStat", "returnStat", "exprStat", 
		"expr", "type", "builtin", "pointers", "string", "addictiveOperator", 
		"multiOperater"
	};

	@Override
	public String getGrammarFileName() { return "Snippet.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SnippetParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << CallExpr) | (1L << ID) | (1L << Int) | (1L << Char) | (1L << Float) | (1L << IntPointer) | (1L << CharPointer) | (1L << FloatPointer) | (1L << INT) | (1L << FLOAT))) != 0)) {
				{
				{
				setState(26); statement();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public DeclarationStatContext declarationStat() {
			return getRuleContext(DeclarationStatContext.class,0);
		}
		public ReturnStatContext returnStat() {
			return getRuleContext(ReturnStatContext.class,0);
		}
		public ExprStatContext exprStat() {
			return getRuleContext(ExprStatContext.class,0);
		}
		public AssignStatContext assignStat() {
			return getRuleContext(AssignStatContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(36);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(32); declarationStat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(33); assignStat();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(34); returnStat();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(35); exprStat();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationStatContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SnippetParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode INT() { return getToken(SnippetParser.INT, 0); }
		public DeclarationStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterDeclarationStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitDeclarationStat(this);
		}
	}

	public final DeclarationStatContext declarationStat() throws RecognitionException {
		DeclarationStatContext _localctx = new DeclarationStatContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declarationStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); type();
			setState(39); match(ID);
			setState(43);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(40); match(T__3);
				setState(41); match(INT);
				setState(42); match(T__0);
				}
			}

			setState(45); match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignStatContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SnippetParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AssignStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterAssignStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitAssignStat(this);
		}
	}

	public final AssignStatContext assignStat() throws RecognitionException {
		AssignStatContext _localctx = new AssignStatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Char) | (1L << Float) | (1L << IntPointer) | (1L << CharPointer) | (1L << FloatPointer))) != 0)) {
				{
				setState(47); type();
				}
			}

			setState(50); match(ID);
			setState(51); match(T__1);
			setState(52); expr(0);
			setState(53); match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SnippetParser.ID, 0); }
		public ReturnStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterReturnStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitReturnStat(this);
		}
	}

	public final ReturnStatContext returnStat() throws RecognitionException {
		ReturnStatContext _localctx = new ReturnStatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_returnStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); match(T__7);
			setState(56); match(ID);
			setState(57); match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprStatContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterExprStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitExprStat(this);
		}
	}

	public final ExprStatContext exprStat() throws RecognitionException {
		ExprStatContext _localctx = new ExprStatContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exprStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); expr(0);
			setState(60); match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public AddictiveOperatorContext addictiveOperator() {
			return getRuleContext(AddictiveOperatorContext.class,0);
		}
		public TerminalNode ID() { return getToken(SnippetParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode INT() { return getToken(SnippetParser.INT, 0); }
		public TerminalNode CallExpr() { return getToken(SnippetParser.CallExpr, 0); }
		public TerminalNode FLOAT() { return getToken(SnippetParser.FLOAT, 0); }
		public MultiOperaterContext multiOperater() {
			return getRuleContext(MultiOperaterContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(63); match(ID);
				}
				break;
			case INT:
				{
				setState(64); match(INT);
				}
				break;
			case FLOAT:
				{
				setState(65); match(FLOAT);
				}
				break;
			case CallExpr:
				{
				setState(66); match(CallExpr);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(79);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(77);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(69);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(70); addictiveOperator();
						setState(71); expr(5);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(73);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(74); multiOperater();
						setState(75); expr(4);
						}
						break;
					}
					} 
				}
				setState(81);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BuiltinContext builtin() {
			return getRuleContext(BuiltinContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public PointersContext pointers() {
			return getRuleContext(PointersContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(85);
			switch (_input.LA(1)) {
			case Int:
			case Char:
			case Float:
				enterOuterAlt(_localctx, 1);
				{
				setState(82); builtin();
				}
				break;
			case IntPointer:
			case FloatPointer:
				enterOuterAlt(_localctx, 2);
				{
				setState(83); pointers();
				}
				break;
			case CharPointer:
				enterOuterAlt(_localctx, 3);
				{
				setState(84); string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuiltinContext extends ParserRuleContext {
		public TerminalNode Char() { return getToken(SnippetParser.Char, 0); }
		public TerminalNode Float() { return getToken(SnippetParser.Float, 0); }
		public TerminalNode Int() { return getToken(SnippetParser.Int, 0); }
		public BuiltinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_builtin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterBuiltin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitBuiltin(this);
		}
	}

	public final BuiltinContext builtin() throws RecognitionException {
		BuiltinContext _localctx = new BuiltinContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_builtin);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Char) | (1L << Float))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointersContext extends ParserRuleContext {
		public TerminalNode FloatPointer() { return getToken(SnippetParser.FloatPointer, 0); }
		public TerminalNode IntPointer() { return getToken(SnippetParser.IntPointer, 0); }
		public PointersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterPointers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitPointers(this);
		}
	}

	public final PointersContext pointers() throws RecognitionException {
		PointersContext _localctx = new PointersContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pointers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_la = _input.LA(1);
			if ( !(_la==IntPointer || _la==FloatPointer) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode CharPointer() { return getToken(SnippetParser.CharPointer, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(CharPointer);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddictiveOperatorContext extends ParserRuleContext {
		public AddictiveOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addictiveOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterAddictiveOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitAddictiveOperator(this);
		}
	}

	public final AddictiveOperatorContext addictiveOperator() throws RecognitionException {
		AddictiveOperatorContext _localctx = new AddictiveOperatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_addictiveOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__2) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiOperaterContext extends ParserRuleContext {
		public MultiOperaterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiOperater; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).enterMultiOperater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SnippetListener ) ((SnippetListener)listener).exitMultiOperater(this);
		}
	}

	public final MultiOperaterContext multiOperater() throws RecognitionException {
		MultiOperaterContext _localctx = new MultiOperaterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_multiOperater);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__8) | (1L << T__6))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 4);
		case 1: return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\27d\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\3\3\3\3\3\3\3"+
		"\5\3\'\n\3\3\4\3\4\3\4\3\4\3\4\5\4.\n\4\3\4\3\4\3\5\5\5\63\n\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\bF\n\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\bP\n\b\f\b\16\bS\13\b\3\t\3\t\3\t\5"+
		"\tX\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\2\3\16\17\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\2\6\3\2\17\21\4\2\22\22\24\24\4\2\7\7\n"+
		"\n\4\2\3\4\6\6c\2\37\3\2\2\2\4&\3\2\2\2\6(\3\2\2\2\b\62\3\2\2\2\n9\3\2"+
		"\2\2\f=\3\2\2\2\16E\3\2\2\2\20W\3\2\2\2\22Y\3\2\2\2\24[\3\2\2\2\26]\3"+
		"\2\2\2\30_\3\2\2\2\32a\3\2\2\2\34\36\5\4\3\2\35\34\3\2\2\2\36!\3\2\2\2"+
		"\37\35\3\2\2\2\37 \3\2\2\2 \3\3\2\2\2!\37\3\2\2\2\"\'\5\6\4\2#\'\5\b\5"+
		"\2$\'\5\n\6\2%\'\5\f\7\2&\"\3\2\2\2&#\3\2\2\2&$\3\2\2\2&%\3\2\2\2\'\5"+
		"\3\2\2\2()\5\20\t\2)-\7\16\2\2*+\7\t\2\2+,\7\25\2\2,.\7\f\2\2-*\3\2\2"+
		"\2-.\3\2\2\2./\3\2\2\2/\60\7\b\2\2\60\7\3\2\2\2\61\63\5\20\t\2\62\61\3"+
		"\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\7\16\2\2\65\66\7\13\2\2\66\67"+
		"\5\16\b\2\678\7\b\2\28\t\3\2\2\29:\7\5\2\2:;\7\16\2\2;<\7\b\2\2<\13\3"+
		"\2\2\2=>\5\16\b\2>?\7\b\2\2?\r\3\2\2\2@A\b\b\1\2AF\7\16\2\2BF\7\25\2\2"+
		"CF\7\26\2\2DF\7\r\2\2E@\3\2\2\2EB\3\2\2\2EC\3\2\2\2ED\3\2\2\2FQ\3\2\2"+
		"\2GH\f\6\2\2HI\5\30\r\2IJ\5\16\b\7JP\3\2\2\2KL\f\5\2\2LM\5\32\16\2MN\5"+
		"\16\b\6NP\3\2\2\2OG\3\2\2\2OK\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2R\17"+
		"\3\2\2\2SQ\3\2\2\2TX\5\22\n\2UX\5\24\13\2VX\5\26\f\2WT\3\2\2\2WU\3\2\2"+
		"\2WV\3\2\2\2X\21\3\2\2\2YZ\t\2\2\2Z\23\3\2\2\2[\\\t\3\2\2\\\25\3\2\2\2"+
		"]^\7\23\2\2^\27\3\2\2\2_`\t\4\2\2`\31\3\2\2\2ab\t\5\2\2b\33\3\2\2\2\n"+
		"\37&-\62EOQW";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}