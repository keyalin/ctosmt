grammar Snippet;

prog : statement*;

statement
	: declarationStat
	| assignStat
	| returnStat
	| exprStat
	; 

declarationStat
	: type ID ('[' INT ']')? ';'
	;

assignStat
	: type? ID '=' expr ';'
	;
	
returnStat
	: 'return' ID ';'
	;
	
exprStat
	: CallExpr ';'
	| expr ';'
	;

expr 
	: ID 
	| INT
	| expr addictiveOperator expr
	| expr multiOperater expr
	| FLOAT
	| CharacterLiteral
	| StringLiteral
	;
	



	
CallExpr : ID '(' (ID | FLOAT | INT )* ')';

type
	: Int
	| Char
	| Float
	| IntPointer
	| FloatPointer
	| String
	;




	
String
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
	: 'int' '*'
	;

CharPointer : 'char*';
FloatPointer : 'float' '*';
ID : ('a'..'z' |'A'..'Z'|'_')('a'..'z' |'A'..'Z'|'_' | '0'..'9')*;

INT: ('0'..'9')+;
FLOAT: ('0'..'9')+('.')('0'..'9')+;

WS : [ \t\r\n]+ -> skip;


CharacterLiteral 
	: '\'' (Character) '\''
	;	
	
	
StringLiteral
	: '"' (Character)* '"' 
	;


Character : [0-9|a-z|A-Z];


	

	
