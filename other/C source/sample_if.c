#include<stdio.h>

int main(){

	int a;

	printf("a=");
	scanf("%d",&a);

	if(a<10){
		printf("a>10:false\n");
	}
	else{
		printf("a>10:true\n");
	}

	return 0;
}