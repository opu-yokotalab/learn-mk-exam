insert into question values ('77','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="77" type="fill_in_the_blank"><question>
以下のように配列の要素のアドレスを表示するプログラムを作りなさい。
---- 実行結果例 ----
&amp;a[0] = 100 , p+0 = 100<br/>
&amp;a[1] = 102 , p+0 = 102<br/>
&amp;a[2] = 104 , p+0 = 104<br/>
&amp;a[3] = 106 , p+0 = 106<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>
<hr/>
<pre>
#include&lt;stdio.h&gt;

int main(void){
	int i;
	int a[5];
	int *p = a;
	
	for(i=0; i &lt; 5; i++){
	/*値にアドレス演算子を適用した表示と、ポインタ変数を用いた表示を行う*/
		printf("%a[%d] = %p , p+%d = %p",i, [ A ],i, [ B ]);
	}
	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>240</function>
<correct id="1">&amp;a[i]</correct>
<correct id="2">p+i</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>ポインタ変数を用いたアクセス法を覚えておこう。<br/>
ポインタ変数を用いて配列の要素にアクセスすると効率的で便利</explanation>
</item>
</program_set>');