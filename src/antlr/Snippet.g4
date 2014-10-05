grammar Snippet;

prog : statement*;

statement
	: declarationStat
	| assignStat
	| returnStat
	| exprStat
	; 

declarationStat
	: type ID ('[' INT ']')?';'
	;

assignStat
	: type? ID '=' expr ';'
	;
	
returnStat
	: 'return' ID ';'
	;
	
exprStat
	: CallExpr ';'
	;

expr 
	: ID 
	| INT
	| expr addictiveOperator expr
	| expr multiOperater expr
	| FLOAT
	;
	
CallExpr : ID '(' (ID | FLOAT | INT )*')';

type
	: builtin
	| pointers
	| string
	;

ID : ('a'..'z' |'A'..'Z'|'_')('a'..'z' |'A'..'Z'|'_' | '0'..'9')*;


	
	
builtin 
	: Int
	| Char
	| Float
	;
	
pointers
	: IntPointer
	| FloatPointer
	;
	
string
	: CharPointer
	;
	
	

addictiveOperator
	: '+'
	| '-'
	;
	
multiOperater
	: '*'
	| '/'
	| '%'
	;

Int : 'int';

Char : 'char';

Float : 'float';

IntPointer 
	: 'int*'
	;

CharPointer : 'char*';
FloatPointer : 'float*';

INT: ('0'..'9')+;
FLOAT: ('0'..'9')+('.')('0'..'9');

WS : [ \t\r\n]+ -> skip;
	

	
