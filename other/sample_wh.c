#include<stdio.h>

int main(){

	int sum=0;
	int n;
	int i=1;

	printf("�P���炎�܂ł̍��v���v�Z����B:��=");
	scanf("%d",&n);

	while(i<=n){

		sum=sum+i;
		i++;
	}

	printf("���v=%d\n",sum);
	return 0;
}
