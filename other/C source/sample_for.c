#include<stdio.h>

int main(void){
	int sum = 0;
	int n;
	int i=1;

	printf("�P���炎�܂ł̍��v���v�Z����B:��=");
	scanf("%d",&n);

	for(i=1;i<=n;i++){
		sum = sum + i;
	}

	printf("���v=%d\n",sum);

	return 0;
}