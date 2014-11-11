grammar path;

prog : statement*;


statement
	: declarationStat
	| assignStat
	| returnStat
	| exprStat
	| assumeStat
	; 

assumeStat
	: expr comparator expr ';'
	;
	
declarationStat
	: type ('[' INT ']') ID  ';'
	| type '*' ID ';'
	| type ID ';'
	;

assignStat
	: ID '=' expr  ';'
	| '*' ID '=' (expr | StringLiteral) ';'
	;
	
returnStat
	: 'return' '('ID | INT | FLOAT ')' ';'
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
	
Address: '&' ID;

	



	
CallExpr : ID '(' (ID | FLOAT | INT )* ')';

type
	: Int
	| Char
	| Float
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

comparator
	: '>'
	| '>='
	| '<'
	| '<='
	| '='
	;

Int : 'int';

Char : 'char';

Float : 'float';


ID : ('a'..'z' |'A'..'Z'|'_'|'|')('a'..'z' |'A'..'Z'|'_' | '0'..'9')*;

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
