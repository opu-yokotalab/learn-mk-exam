insert into question values ('87','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="87" type="fill_in_the_blank"><question>
0�`100�͈̔͂œ��͂��ꂽ�����̐��l�̒�����A�ő�l�ƍŏ��l�����߂ĕ\������v���O�������쐬����B<br/>
-1�����͂��ꂽ�ꍇ�͓��͂̏I���Ɣ��肷��B�������A�ő�l�ƍŏ��l��main�֐��ȊO�̈�̊֐��̒��ŋ��߂�B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;

void maxmin(int array[],int *max,int *min);

int main(void)
{
	int i = 0,array[10],max,min;
	
	do {
		printf("%d �Ԗڂ̐�:",i + 1);	/*�l�̎擾*/
		scanf("%d",&amp;array[i]);
		i++;
	} while (array[i - 1] != -1);		/*-1�����͂����܂œ��͂��s��*/
	
	maxmin(array,&amp;max,&amp;min);
	
	printf("�ő�l %d : �ŏ��l %d",max,min);
	
	return 0;
}

/*-- ��r�֐� --*/
void maxmin(int array[],int [ A ],int [ B ])
{
	int i = 0;
	
	*max = 0;	/*�ő�l*/
	*min = 100;	/*�ŏ��l*/
	
	while (array[i] != -1) {
		if (array[i] &gt; *max) *max = array[i];/*�ő�l���傫����Α��*/
		if (array[i] &lt; *min) *min = array[i];/*�ŏ��l��菬������Α��*/
		i++;
	}
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">*max</correct>
<correct id="2">*min</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�֐��ɎQ�Ɠn���Œl��n���̂ŁA�����̓|�C���^�ɂȂ�܂��B</explanation>
</item>
</program_set>');