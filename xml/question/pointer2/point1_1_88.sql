insert into question values ('88','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="88" type="fill_in_the_blank"><question>
下記のプログラムの実行結果を答える問題です。<br/>
[ A ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main(void)
{
	int num = 10;  /* 通常の変数 */
	int *p;        /* ポインタ変数 */
	
	p = &amp;num; 
	*p = 20;    
	
	printf( "num = %d", num );  

	return 0;
}

実行結果
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
<explanation>間接参照により、変数numに20を代入しているのでnumの値が変わります。</explanation>
</item>
</program_set>');