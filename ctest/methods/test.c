//#include <string.h>
#include <stdio.h>
int test(int a){
	int b = 0;
	int c = 0;
	int* p = &b;
	char array[256];
	char* s = "abc";
	//strcpy(array, s);
	if(a < 3){
		b = a + 1;
		c = b * 2;
	}
	else{
		c *= 2;
		*p = c;
	}
	a * b;
	int d = b * c;
	return d;
}

int main(){
	printf("%d\n", test(2));
	return 1;
}
