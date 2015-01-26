grammar Entry;

prog : statement*;

statement
	: assignStat';'
	| callStat';'
	| typeStat';'
	;

typeStat
	: variable+;
variable: 
	ID':'type',';




assignStat
	: ID assignOperater expr
	| pointerTag ID assignOperater StringLiteral
	;
	
callStat
	: ID arguments
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
	;
	

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

Float : 'float' | 'double';
String : 'char*';


ID : ('a'..'z' |'A'..'Z'|'_'|'|')('a'..'z' |'A'..'Z'|'_' | '0'..'9')*;

INT: ('0'..'9')+;
FLOAT: ('0'..'9')+('.')('0'..'9')+;

WS : [ \t\r\n]+ -> skip;


	
	
	
StringLiteral
	: '"' (Character)* '"' 
	;


Character : [0-9|a-z|A-Z];
