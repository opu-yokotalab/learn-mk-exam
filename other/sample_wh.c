#include<stdio.h>

int main(){

	int sum=0;
	int n;
	int i=1;

	printf("１からｎまでの合計を計算する。:ｎ=");
	scanf("%d",&n);

	while(i<=n){

		sum=sum+i;
		i++;
	}

	printf("合計=%d\n",sum);
	return 0;
}
