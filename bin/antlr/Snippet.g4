grammar snippet;

statement
	: declarationStat
	| assignStat
	| returnStat
	| exprStat
	; 

declarationStat
	: type ID ('[' constant ']')?';'
	;

assignStat
	: type? ID '=' expr ';'
	;
	
returnStat
	: 'return' ID ';'
	;
	
exprStat
	: expr ';'
	;


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

constant
	: ('0'..'9')?;

WS : [ \t\r\n]+ -> skip;
	

	
