#include<stdio.h>
#include<math.h>

int main (void){

	int a;
	int f = 10;

	double c;
	double d=0.01;

	int e,b,h,g;
	int i,j;

	c=0;

	for(a=0;a<f;a++){
		c = c+f;
	}

	e=c+d;
	j=d+c;

	b= a*c;
	i= c*a;

	g=(e+b)*d;

	h=e*d+b*d;

	printf("%d",g);

	return 0;

}