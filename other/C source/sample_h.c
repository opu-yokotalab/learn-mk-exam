#include<stdio.h>
#include<math.h>

int main (void){

	int a;
	int b = 10;

	double c;
	double d=0.01;

	int e,f,g;

	c=0;

	for(a=0;a<b;a++){
		c = c+d;
	}

	e=c;

	f=e+c;

	g=e+10*d;

	printf("%d",g);

	return 0;

}