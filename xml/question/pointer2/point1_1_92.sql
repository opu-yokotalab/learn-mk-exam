insert into question values ('92','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="92" type="fill_in_the_blank"><question>
下記のプログラムの実行結果を答える問題です。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

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


実行結果
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
<explanation>ポインタを用いた代入がどのように行われるか復習してみてください。</explanation>
</item>
</program_set>');