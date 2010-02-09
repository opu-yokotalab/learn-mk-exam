insert into question values ('83','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="83" type="fill_in_the_blank"><question>
コマンドラインから与えられたすべての数値の和を表示するプログラムsumを作れ。<br/>
sum 15 3.14 1.35E1 は、31.6400000と出力される。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include&lt;stdio.h&gt;
#include&lt;stdlib.h&gt;
#include&lt;errno.h&gt;

int main(int argc, char **argv){
	char str[100];
	char *ptr = str;
	double sum = 0.0;

	while([ A ] &gt; 0){							/*引数すべてについて処理するまで*/
		double x = strtod(*++argv, &amp;ptr);		/*引数から得た文字列をdouble型に変換して格納*/
		if(errno != ERANGE &amp;&amp; ptr!= str)	/*文字列の変換処理が失敗した場合処理しない*/
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
<explanation>argcにはコマンドラインから入力された文字列の数が入るので、それを用いる。</explanation>
</item>
</program_set>');