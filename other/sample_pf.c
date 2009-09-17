#include<stdio.h>

int main(){
	
	int a,b,c;
	int sum =0;
	double ave = 0;
	
	printf("a = ");
	scanf("%d",&a);
	
	printf("b = ");
	scanf("%d",&b);
	
	printf("c = ");
	scanf("%d",&c);
	
	sum = a + b + c;
	printf("a+b+c = %d\n",sum);
	
	ave = sum/3;
	printf("average = %f\n",ave);
	
	return 0;
	
}