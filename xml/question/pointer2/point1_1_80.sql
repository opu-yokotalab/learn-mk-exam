insert into question values ('80','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="80" type="fill_in_the_blank"><question>
�ȉ��̂悤�ɐ錾�����3�����z��<br/>
int mx[3][2][4];<br/>
�̑S�v�f�ɂR�̓Y�����̘a��������v���O���������<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include&lt;stdio.h&gt;

void fill_avalue(int a[][2][4], int m,n,o){
	int i,j,k;
	
	for(i=0; i&lt;m; i++){
		for(j=0; j&lt;n; j++){
			for(k=0; k&lt;k; k++){
				a[i][j][k] = i+j+k;
			}
		}
	}
}

int main(void){
	int i,j,k;
	int mx[3][2][4];
	
	fill_avalue([ A ],3,2,4);
	
	for(i=0; i &lt; 3; i++){
		for(j=0; j &lt 2; j++){
			for(k=0; k &lt; 4; k++){
				printf("mx[%d][%d][%d] = %3d",i,j,k, mx[i][j][k])
			}
		}
	}
}

���s���ʈ��
mx[0][0][0] = 0
	�F
mx[1][2][3] = 6
	�F
mx[3][2][2] = 7
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>240</function>
<correct id="1">mx</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�֐�fill_avalue�ɂ͔z��mx�̃A�h���X��n���܂��B</explanation>
</item>
</program_set>');