
int test1(int a){
	int b = 0;
	int c = 0;
	int* p = &b;
	//char* s = "abc";
	if(a == 3){
		b = a + 1;
		c = b * 2;
	}
	else{
		c = 2 * c;
		*p = c;
	}
	int d = b * c;
	return d;
}

int test2(char a){
	int b = 0;
	int c = 0;
	int* p = &b;
	//char* s = "abc";
	if(a < 3){
		b = a + 1;
		c = b * 2;
	}
	else{
		c = 2 * c;
		*p = c;
	}
	int d = b * c;
	return d;
}


int max(int a, int b, int c){
	int d;
	if(a > b) d = a;
	else d = b;
	if(d > c) return d;
	else return c;
}


int Doubled(int a){
	int c = 2 * a;
	return c;
}

int minus(int a, int b){
	int c = a - b;
	return c;
}

int sum(int a, int b)
{

	int c = a + b;
	return c;
}

int muliptly(int a, int b){
	int c = a * b;
	return c;
}

int divide(int a, int b){
	int c = a / b;
	return c;
}
