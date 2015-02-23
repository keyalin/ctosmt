#include<stdio.h>
#include<string.h>
#include<stdlib.h>

char* test1(int a, char* c, char* b);

int main(int argc, char** argv){
	int a = atoi(argv[1]);
	char* c = argv[2];
	char b[20];
	test1(a, c, b);
	return 1;
}

char* test1(int a, char* c, char* b){
	//a = 3;
		
	strcpy(b, "a");
	printf("input state:a:%d:int,b:%s:char*,c:%s:char*\n", a, b, c);
	strcat(b, c);
	printf("output state:a:%d:int,b:%s:char*,c:%s:char*\n", a, b, c);
	printf("return:%s\n", b);
	return b;
}