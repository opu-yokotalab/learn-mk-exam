DELETE from question where program_id = '74';

insert into question values ('74','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point">
<item id="74" type="fill_in_the_blank"><question>
以下に示すプログラムの出力結果を示せ。<br/>
[ A ]に当てはまるものは何でしょう。<br/><br/>
<hr/>
<pre>
#include&lt;stdio.h&gt;

int main(void){
	int x =50;
	int *p = &amp;x;
	
	printf("5**pの値＝%d", 5**p); 
	
	return 0;
}

実行結果
5**pの値=[ A ]
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
<explanation>**という演算子は存在しません。5と*pの値を掛けるだけです。</explanation>
</item>
</program_set>');