insert into question values ('98','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="98" type="fill_in_the_blank"><question>
下記のプログラムの実行結果を答える問題です。<br/>
[ A ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include &lt;stdio.h&gt;
void main(void){   
	char *ps, str[10] = "abcd";   
	ps = str; 
	printf("出力1：%s",str);   
	*ps = 'x';   
	ps++;  
	*ps = 'y';   
	printf("出力2：%s",str);   
	return 0;  
}  


実行結果例
出力1：abcd 
出力2：[ A ]
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
<explanation>ポインタ変数を用いて1文字ずつ書き換えています。確認してみましょう。</explanation>
</item>
</program_set>');