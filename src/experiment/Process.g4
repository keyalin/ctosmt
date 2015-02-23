grammar Process;

function
	: type ID parameters block
	;
	
block
	: '{' statement* '}'
	;