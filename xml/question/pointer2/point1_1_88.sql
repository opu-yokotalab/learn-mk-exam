insert into question values ('88','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="88" type="fill_in_the_blank"><question>
���L�̃v���O�����̎��s���ʂ𓚂�����ł��B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main(void)
{
	int num = 10;  /* �ʏ�̕ϐ� */
	int *p;        /* �|�C���^�ϐ� */
	
	p = &amp;num; 
	*p = 20;    
	
	printf( "num = %d", num );  

	return 0;
}

���s����
num = [ A ]
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">20</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�ԐڎQ�Ƃɂ��A�ϐ�num��20�������Ă���̂�num�̒l���ς��܂��B</explanation>
</item>
</program_set>');