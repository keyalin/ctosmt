grammar Data;

prog : statement*;

statement
	: assignStat';'
	| callStat';'
	;

assignStat
	: ID assignOperater expr
	;
	
callStat
	: callExpr
	;
	
type
	: Int
	| Char
	| Float
	;
	
expr 
	: ID 
	| INT
	| expr multiOperater expr
	| expr  addictiveOperator expr
	| FLOAT
	;
	
callExpr : ID arguments;

arguments:
	'(' (formalArgument(',' formalArgument)*)? ')';

formalArgument
	: ID
	| FLOAT
	| INT
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

assignOperater : '=';
pointerTag : '*';

comparator
	: '>'
	| '>='
	| '<'
	| '<='
	| '=='
	| '!='
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
	: StringTag (Character)* StringTag 
	;

StringTag : '"';

Character : [0-9|a-z|A-Z|_|'|'|'^'];