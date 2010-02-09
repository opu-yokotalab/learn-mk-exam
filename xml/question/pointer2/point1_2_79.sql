insert into question values ('79','point1','2',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="79" type="fill_in_the_blank"><question>
int b[3][2][4];の全要素のアドレスを表示するプログラムを作成、実行する。<br/>
以下にソースコードと、実行結果の一例を表示する。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include&lt;stdio.h&gt;

int main(void){
	int i,j,k;
	int b[3][2][4];
	
	for(i=0; i &lt; 3; i++){
		for(j=0; j &lt; 2; j++){
			for(k=0; k &lt; 4; k++){
				printf("&amp;b[%d][%d][%d] = %p",i,j,k, &amp;b[i][j][k])
			}
		}
	}
}

実行結果一例
&amp;[0][0][0] = 1000
&amp;[0][0][1] = [ A ]
&amp;[0][0][2] = 1004
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>240</function>
<correct id="1">1002</correct>
<score>2</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>int型は2バイトなので1つ次の要素のアドレスは模範解答のようになる。</explanation>
</item>
</program_set>');