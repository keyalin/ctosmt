grammar Data;

prog : statement*;

statement
	: assignStat';'
	| declareStat';'
	| callStat';'
	;

declareStat
	: type ID;

assignStat
<<<<<<< HEAD
	: ID assignOperater expr
	| pointerTag ID assignOperater StringLiteral
=======
	: type? ID assignOperater expr
>>>>>>> b3454f3c0325c1d4410f169bc6344a55a5f70c79
	;
	
callStat
	: callExpr
	;
	
type
	: Int
	| Char
	| Float
	| String
	;


	
expr 
	: ID 
	| INT
	| expr multiOperater expr
	| expr  addictiveOperator expr
	| FLOAT
	| '(' expr ')'
	| StringLiteral
	;
	
callExpr : ID arguments;

arguments:
	'(' (formalArgument(',' formalArgument)*)? ')';

formalArgument
	: ID
	| FLOAT
	| INT
	| StringLiteral
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
String: 'char*';


ID : ('a'..'z' |'A'..'Z'|'_'|'|')('a'..'z' |'A'..'Z'|'_' | '0'..'9')*;

INT: ('0'..'9')+;
FLOAT: ('0'..'9')+('.')('0'..'9')+;

WS : [ \t\r\n]+ -> skip;


	
	
	
StringLiteral
	: '"' (Character)* '"' 
	;


Character : [0-9|a-z|A-Z];
