DELETE from question where program_id = '74';

insert into question values ('74','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point">
<item id="74" type="fill_in_the_blank"><question>
�ȉ��Ɏ����v���O�����̏o�͌��ʂ������B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>
<hr/>
<pre>
#include&lt;stdio.h&gt;

int main(void){
	int x =50;
	int *p = &amp;x;
	
	printf("5**p�̒l��%d", 5**p); 
	
	return 0;
}

���s����
5**p�̒l=[ A ]
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>120</function>
<correct id="1">250</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>**�Ƃ������Z�q�͑��݂��܂���B5��*p�̒l���|���邾���ł��B</explanation>
</item>
</program_set>');