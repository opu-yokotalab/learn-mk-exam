#include<stdio.h>

int main(void){
	int sum = 0;
	int n;
	int i=1;

	printf("１からｎまでの合計を計算する。:ｎ=");
	scanf("%d",&n);

	for(i=1;i<=n;i++){
		sum = sum + i;
	}

	printf("合計=%d\n",sum);

	return 0;
}