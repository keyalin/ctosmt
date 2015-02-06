// Generated from Entry.g4 by ANTLR 4.4
package yalin;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class EntryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, Int=18, Char=19, Float=20, String=21, ID=22, INT=23, FLOAT=24, 
		WS=25, StringLiteral=26, Character=27;
	public static final String[] tokenNames = {
		"<INVALID>", "'%'", "')'", "','", "'+'", "'*'", "'-'", "'('", "':'", "'<'", 
		"'='", "'!='", "';'", "'<='", "'>'", "'/'", "'=='", "'>='", "'int'", "'char'", 
		"Float", "'char*'", "ID", "INT", "FLOAT", "WS", "StringLiteral", "Character"
	};
	public static final int
		RULE_prog = 0, RULE_statement = 1, RULE_typeStat = 2, RULE_variable = 3, 
		RULE_assignStat = 4, RULE_callStat = 5, RULE_type = 6, RULE_expr = 7, 
		RULE_arguments = 8, RULE_formalArgument = 9, RULE_addictiveOperator = 10, 
		RULE_multiOperater = 11, RULE_assignOperater = 12, RULE_pointerTag = 13, 
		RULE_comparator = 14;
	public static final String[] ruleNames = {
		"prog", "statement", "typeStat", "variable", "assignStat", "callStat", 
		"type", "expr", "arguments", "formalArgument", "addictiveOperator", "multiOperater", 
		"assignOperater", "pointerTag", "comparator"
	};

	@Override
	public String getGrammarFileName() { return "Entry.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EntryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12 || _la==ID) {
				{
				{
				setState(30); statement();
				}
				}
				setState(35);
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
		public CallStatContext callStat() {
			return getRuleContext(CallStatContext.class,0);
		}
		public TypeStatContext typeStat() {
			return getRuleContext(TypeStatContext.class,0);
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
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(45);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36); assignStat();
				setState(37); match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39); callStat();
				setState(40); match(T__5);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(42); typeStat();
				setState(43); match(T__5);
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

	public static class TypeStatContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public TypeStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterTypeStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitTypeStat(this);
		}
	}

	public final TypeStatContext typeStat() throws RecognitionException {
		TypeStatContext _localctx = new TypeStatContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_typeStat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47); variable();
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EntryParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); match(ID);
			setState(53); match(T__9);
			setState(54); type();
			setState(55); match(T__14);
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
		public AssignOperaterContext assignOperater() {
			return getRuleContext(AssignOperaterContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(EntryParser.ID, 0); }
		public PointerTagContext pointerTag() {
			return getRuleContext(PointerTagContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(EntryParser.StringLiteral, 0); }
		public AssignStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterAssignStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitAssignStat(this);
		}
	}

	public final AssignStatContext assignStat() throws RecognitionException {
		AssignStatContext _localctx = new AssignStatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignStat);
		try {
			setState(66);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(57); match(ID);
				setState(58); assignOperater();
				setState(59); expr(0);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(61); pointerTag();
				setState(62); match(ID);
				setState(63); assignOperater();
				setState(64); match(StringLiteral);
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

	public static class CallStatContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EntryParser.ID, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public CallStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callStat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterCallStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitCallStat(this);
		}
	}

	public final CallStatContext callStat() throws RecognitionException {
		CallStatContext _localctx = new CallStatContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_callStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); match(ID);
			setState(69); arguments();
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode String() { return getToken(EntryParser.String, 0); }
		public TerminalNode Char() { return getToken(EntryParser.Char, 0); }
		public TerminalNode Float() { return getToken(EntryParser.Float, 0); }
		public TerminalNode Int() { return getToken(EntryParser.Int, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Char) | (1L << Float) | (1L << String))) != 0)) ) {
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

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(EntryParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(EntryParser.INT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(EntryParser.ID, 0); }
		public AddictiveOperatorContext addictiveOperator() {
			return getRuleContext(AddictiveOperatorContext.class,0);
		}
		public MultiOperaterContext multiOperater() {
			return getRuleContext(MultiOperaterContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitExpr(this);
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(74); match(ID);
				}
				break;
			case INT:
				{
				setState(75); match(INT);
				}
				break;
			case FLOAT:
				{
				setState(76); match(FLOAT);
				}
				break;
			case T__10:
				{
				setState(77); match(T__10);
				setState(78); expr(0);
				setState(79); match(T__15);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(93);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(91);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(83);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(84); multiOperater();
						setState(85); expr(5);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(87);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(88); addictiveOperator();
						setState(89); expr(4);
						}
						break;
					}
					} 
				}
				setState(95);
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

	public static class ArgumentsContext extends ParserRuleContext {
		public FormalArgumentContext formalArgument(int i) {
			return getRuleContext(FormalArgumentContext.class,i);
		}
		public List<FormalArgumentContext> formalArgument() {
			return getRuleContexts(FormalArgumentContext.class);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(T__10);
			setState(105);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INT) | (1L << FLOAT))) != 0)) {
				{
				setState(97); formalArgument();
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__14) {
					{
					{
					setState(98); match(T__14);
					setState(99); formalArgument();
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(107); match(T__15);
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

	public static class FormalArgumentContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(EntryParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(EntryParser.FLOAT, 0); }
		public TerminalNode ID() { return getToken(EntryParser.ID, 0); }
		public FormalArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterFormalArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitFormalArgument(this);
		}
	}

	public final FormalArgumentContext formalArgument() throws RecognitionException {
		FormalArgumentContext _localctx = new FormalArgumentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_formalArgument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INT) | (1L << FLOAT))) != 0)) ) {
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

	public static class AddictiveOperatorContext extends ParserRuleContext {
		public AddictiveOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addictiveOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterAddictiveOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitAddictiveOperator(this);
		}
	}

	public final AddictiveOperatorContext addictiveOperator() throws RecognitionException {
		AddictiveOperatorContext _localctx = new AddictiveOperatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_addictiveOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			_la = _input.LA(1);
			if ( !(_la==T__13 || _la==T__11) ) {
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
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterMultiOperater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitMultiOperater(this);
		}
	}

	public final MultiOperaterContext multiOperater() throws RecognitionException {
		MultiOperaterContext _localctx = new MultiOperaterContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_multiOperater);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__12) | (1L << T__2))) != 0)) ) {
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

	public static class AssignOperaterContext extends ParserRuleContext {
		public AssignOperaterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignOperater; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterAssignOperater(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitAssignOperater(this);
		}
	}

	public final AssignOperaterContext assignOperater() throws RecognitionException {
		AssignOperaterContext _localctx = new AssignOperaterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_assignOperater);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(T__7);
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

	public static class PointerTagContext extends ParserRuleContext {
		public PointerTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterPointerTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitPointerTag(this);
		}
	}

	public final PointerTagContext pointerTag() throws RecognitionException {
		PointerTagContext _localctx = new PointerTagContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pointerTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); match(T__12);
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

	public static class ComparatorContext extends ParserRuleContext {
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EntryListener ) ((EntryListener)listener).exitComparator(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__3) | (1L << T__1) | (1L << T__0))) != 0)) ) {
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
		case 7: return expr_sempred((ExprContext)_localctx, predIndex);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35|\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\7\2\"\n\2\f\2\16\2%\13"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\60\n\3\3\4\6\4\63\n\4\r\4\16"+
		"\4\64\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6E\n\6"+
		"\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tT\n\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\7\t^\n\t\f\t\16\ta\13\t\3\n\3\n\3\n\3\n\7\ng"+
		"\n\n\f\n\16\nj\13\n\5\nl\n\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3"+
		"\16\3\17\3\17\3\20\3\20\3\20\2\3\20\21\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36\2\7\3\2\24\27\3\2\30\32\4\2\6\6\b\b\5\2\3\3\7\7\21\21\6\2\13\13"+
		"\r\r\17\20\22\23x\2#\3\2\2\2\4/\3\2\2\2\6\62\3\2\2\2\b\66\3\2\2\2\nD\3"+
		"\2\2\2\fF\3\2\2\2\16I\3\2\2\2\20S\3\2\2\2\22b\3\2\2\2\24o\3\2\2\2\26q"+
		"\3\2\2\2\30s\3\2\2\2\32u\3\2\2\2\34w\3\2\2\2\36y\3\2\2\2 \"\5\4\3\2! "+
		"\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\3\3\2\2\2%#\3\2\2\2&\'\5\n\6"+
		"\2\'(\7\16\2\2(\60\3\2\2\2)*\5\f\7\2*+\7\16\2\2+\60\3\2\2\2,-\5\6\4\2"+
		"-.\7\16\2\2.\60\3\2\2\2/&\3\2\2\2/)\3\2\2\2/,\3\2\2\2\60\5\3\2\2\2\61"+
		"\63\5\b\5\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65"+
		"\7\3\2\2\2\66\67\7\30\2\2\678\7\n\2\289\5\16\b\29:\7\5\2\2:\t\3\2\2\2"+
		";<\7\30\2\2<=\5\32\16\2=>\5\20\t\2>E\3\2\2\2?@\5\34\17\2@A\7\30\2\2AB"+
		"\5\32\16\2BC\7\34\2\2CE\3\2\2\2D;\3\2\2\2D?\3\2\2\2E\13\3\2\2\2FG\7\30"+
		"\2\2GH\5\22\n\2H\r\3\2\2\2IJ\t\2\2\2J\17\3\2\2\2KL\b\t\1\2LT\7\30\2\2"+
		"MT\7\31\2\2NT\7\32\2\2OP\7\t\2\2PQ\5\20\t\2QR\7\4\2\2RT\3\2\2\2SK\3\2"+
		"\2\2SM\3\2\2\2SN\3\2\2\2SO\3\2\2\2T_\3\2\2\2UV\f\6\2\2VW\5\30\r\2WX\5"+
		"\20\t\7X^\3\2\2\2YZ\f\5\2\2Z[\5\26\f\2[\\\5\20\t\6\\^\3\2\2\2]U\3\2\2"+
		"\2]Y\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\21\3\2\2\2a_\3\2\2\2bk\7\t"+
		"\2\2ch\5\24\13\2de\7\5\2\2eg\5\24\13\2fd\3\2\2\2gj\3\2\2\2hf\3\2\2\2h"+
		"i\3\2\2\2il\3\2\2\2jh\3\2\2\2kc\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\7\4\2\2"+
		"n\23\3\2\2\2op\t\3\2\2p\25\3\2\2\2qr\t\4\2\2r\27\3\2\2\2st\t\5\2\2t\31"+
		"\3\2\2\2uv\7\f\2\2v\33\3\2\2\2wx\7\7\2\2x\35\3\2\2\2yz\t\6\2\2z\37\3\2"+
		"\2\2\13#/\64DS]_hk";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}