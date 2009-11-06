#include<stdio.h>

int main(){
	int a=1;
	int b=2,c=3;
	int d1,d2;
	double e=0.01,f=1.0;
	double g=1.01;
	double h1,h2;

	d1= a*c+b*c;
	d2= (a+b)*c;

	h1= e+f+g;
	h2= e+g+f;
	

	printf("%d,%d\n",d1,d2);
	printf("%f,%f\n",h1,h2);

	return 0;
}