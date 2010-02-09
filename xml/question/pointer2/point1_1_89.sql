insert into question values ('89','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="89" type="fill_in_the_blank"><question>
下記のプログラムの実行結果を答える問題です。<br/>
[ A ]に当てはまるものは何でしょう。<br/><br/>

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

実行結果
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
<explanation>間接参照によりnum1に15が代入され、num1の15とnum2の15が加算され30が代入される。</explanation>
</item>
</program_set>');