insert into question values ('89','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="89" type="fill_in_the_blank"><question>
���L�̃v���O�����̎��s���ʂ𓚂�����ł��B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main(void)
{
	int num1 = 0, num2 = 15;
	int *p;
	
	p = &amp;num1;
	*p = *p + num2;
	
	p = &amp;num2;
	*p = *p + num1;
	
	printf( "num1:%d  num2:%d", num1, num2 );

	return 0;
}

���s����
num1:[ A ] num2:[ B ]
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">15</correct>
<correct id="2">30</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�ԐڎQ�Ƃɂ��num1��15���������Anum1��15��num2��15�����Z����30����������B</explanation>
</item>
</program_set>');