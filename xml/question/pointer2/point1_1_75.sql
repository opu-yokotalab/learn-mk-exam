DELETE from question where program_id = '75';

insert into question values ('75','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point">
<item id="75" type="fill_in_the_blank"><question>
��̐���x��y�̗ւ�wa���w���ϐ��ɁA����sa���w���ϐ��ɑ������֐�<br/>
void sum_diff(int x, int y, int *wa, int *sa)���쐬����B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>
<hr/>
<pre>
#include&lt;stdio.h&gt;

void sum_diff(int x, int y, int *wa, int *sa){
	[ A ] = x +y; /*�a�̌v�Z*/
	if (x>y){/*���̌v�Z*/
		*sa = x-y;
	}
	else {
		*sa = y-x;
	}
}

int main(void){
	int n1 = 100, n2 = 200;
	int sum, diff;
	
	sum_diff(n1,n2,&amp;sum,&amp;diff);
	
	printf("x��y�̘a��%d", sum); /*sum�̒l��\��*/
	printf("x��y�̍���%d", [ B ]); /*diff�̒l��\��*/
	
	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>120</function>
<correct id="1">*wa</correct>
<correct id="2">diff</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>sum_diff�֐��ɕϐ�sum��diff���Q�Ɠn���œn���܂��B���̂����ł̕ϐ��̃A�N�Z�X�ɋC�����āB</explanation>
</item>
</program_set>');