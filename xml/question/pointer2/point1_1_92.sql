insert into question values ('92','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="92" type="fill_in_the_blank"><question>
���L�̃v���O�����̎��s���ʂ𓚂�����ł��B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;
int main(void)
{
	int data, *ptr1, *ptr2;

	data = 10;
	ptr2 = (int *)malloc(sizeof(int));
	*ptr2 = data;
	printf ("==%d==\n", *ptr2);
	ptr1 = &amp;data;
	*ptr1 = 20;
	printf ("==%d==\n", data);
}


���s����
==[ A ]==
==[ B ]==
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">10</correct>
<correct id="2">20</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�|�C���^��p����������ǂ̂悤�ɍs���邩���K���Ă݂Ă��������B</explanation>
</item>
</program_set>');