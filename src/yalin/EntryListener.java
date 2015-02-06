// Generated from Entry.g4 by ANTLR 4.4
package yalin;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EntryParser}.
 */
public interface EntryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EntryParser#assignStat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(@NotNull EntryParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#assignStat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(@NotNull EntryParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull EntryParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull EntryParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(@NotNull EntryParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(@NotNull EntryParser.ComparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull EntryParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull EntryParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#typeStat}.
	 * @param ctx the parse tree
	 */
	void enterTypeStat(@NotNull EntryParser.TypeStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#typeStat}.
	 * @param ctx the parse tree
	 */
	void exitTypeStat(@NotNull EntryParser.TypeStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#pointerTag}.
	 * @param ctx the parse tree
	 */
	void enterPointerTag(@NotNull EntryParser.PointerTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#pointerTag}.
	 * @param ctx the parse tree
	 */
	void exitPointerTag(@NotNull EntryParser.PointerTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#callStat}.
	 * @param ctx the parse tree
	 */
	void enterCallStat(@NotNull EntryParser.CallStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#callStat}.
	 * @param ctx the parse tree
	 */
	void exitCallStat(@NotNull EntryParser.CallStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#addictiveOperator}.
	 * @param ctx the parse tree
	 */
	void enterAddictiveOperator(@NotNull EntryParser.AddictiveOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#addictiveOperator}.
	 * @param ctx the parse tree
	 */
	void exitAddictiveOperator(@NotNull EntryParser.AddictiveOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#formalArgument}.
	 * @param ctx the parse tree
	 */
	void enterFormalArgument(@NotNull EntryParser.FormalArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#formalArgument}.
	 * @param ctx the parse tree
	 */
	void exitFormalArgument(@NotNull EntryParser.FormalArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull EntryParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull EntryParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#multiOperater}.
	 * @param ctx the parse tree
	 */
	void enterMultiOperater(@NotNull EntryParser.MultiOperaterContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#multiOperater}.
	 * @param ctx the parse tree
	 */
	void exitMultiOperater(@NotNull EntryParser.MultiOperaterContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull EntryParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull EntryParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull EntryParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull EntryParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#assignOperater}.
	 * @param ctx the parse tree
	 */
	void enterAssignOperater(@NotNull EntryParser.AssignOperaterContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#assignOperater}.
	 * @param ctx the parse tree
	 */
	void exitAssignOperater(@NotNull EntryParser.AssignOperaterContext ctx);
	/**
	 * Enter a parse tree produced by {@link EntryParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull EntryParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link EntryParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull EntryParser.VariableContext ctx);
}