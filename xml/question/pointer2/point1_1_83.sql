insert into question values ('83','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="83" type="fill_in_the_blank"><question>
�R�}���h���C������^����ꂽ���ׂĂ̐��l�̘a��\������v���O����sum�����B<br/>
sum 15 3.14 1.35E1 �́A31.6400000�Əo�͂����B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include&lt;stdio.h&gt;
#include&lt;stdlib.h&gt;
#include&lt;errno.h&gt;

int main(int argc, char **argv){
	char str[100];
	char *ptr = str;
	double sum = 0.0;

	while([ A ] &gt; 0){							/*�������ׂĂɂ��ď�������܂�*/
		double x = strtod(*++argv, &amp;ptr);		/*�������瓾���������double�^�ɕϊ����Ċi�[*/
		if(errno != ERANGE &amp;&amp; ptr!= str)	/*������̕ϊ����������s�����ꍇ�������Ȃ�*/
			sum +[ B ] x;
	}

	printf("%f",sum);

	return 0;
}

</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">--argc</correct>
<correct id="2">=</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>argc�ɂ̓R�}���h���C��������͂��ꂽ������̐�������̂ŁA�����p����B</explanation>
</item>
</program_set>');