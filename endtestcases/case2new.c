#include<stdio.h>
#include<string.h>
#include<stdlib.h>

char* test1(int a, char* c, char* b);

int main(int argc, char** argv){
	int a = atoi(argv[1]);
	char* b = argv[2];
	char c[20];
	test1(a, b, c);
	return 1;
}

char* test1(int a, char* b, char* c){
	//a = 3;
		
	strcpy(c, "a");
	printf("input state:a:%d:int,b:%s:char*,c:%s:char*\n", a, b, c);


strncat(b, c, a);

	printf("output state:a:%d:int,b:%s:char*,c:%s:char*\n", a, b, c);
	printf("return:%s\n", c);
	return c;
}
