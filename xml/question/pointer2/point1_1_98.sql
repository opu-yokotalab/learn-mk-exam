insert into question values ('98','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="98" type="fill_in_the_blank"><question>
���L�̃v���O�����̎��s���ʂ𓚂�����ł��B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;
void main(void){   
	char *ps, str[10] = "abcd";   
	ps = str; 
	printf("�o��1�F%s",str);   
	*ps = 'x';   
	ps++;  
	*ps = 'y';   
	printf("�o��2�F%s",str);   
	return 0;  
}  


���s���ʗ�
�o��1�Fabcd 
�o��2�F[ A ]
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">xycd</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�|�C���^�ϐ���p����1���������������Ă��܂��B�m�F���Ă݂܂��傤�B</explanation>
</item>
</program_set>');