grammar SSA;

function
	: type ID parameters block
	;
	
type
	: Int
	| Char
	| Float
	;
	
parameters
	: '('(formalParameter(',' formalParameter)*)?')'
	;
	
formalParameter
	: type ID
	;


block
	: '{' statement* '}'
	;
	
statement
	: declarationStat ';'
	| assignStat ';'
	| returnStat ';'
	| callStat ';'
	;
	
declarationStat
	: type pointerTag ID 
	| type ID 
	;

assignStat
	: ID assignOperater expr
	| type ID assignOperater expr
	| pointerTag ID assignOperater expr
	| type pointerTag ID assignOperater expr
	;
	
	
	
returnStat
	: 'return' (ID | INT | FLOAT )
	;

callStat
	: callExpr
	;
	
	
expr 
	: ID 
	| INT
	| expr addictiveOperator expr
	| expr multiOperater expr
	| FLOAT
	| CharacterLiteral
	| StringLiteral
	| addressExpr
	| defExpr
	| callExpr
	;
	
addressExpr: '&' ID;
defExpr : '*' ID;

	



	
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