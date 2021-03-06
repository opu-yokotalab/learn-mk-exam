insert into question values ('76','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="76" type="fill_in_the_blank"><question>
pa,pb,pcが指す3つのdouble型浮動小数点値が*pa&lt;=*pb&lt;=*pcとなるように、<br/>
昇順にソートする関数void sort3d(double *pa,double *pb, double *pc){}を作成せよ。<br/>
[ A ]と[ B ]と[ C ]に当てはまるものは何でしょう。<br/><br/>
<hr/>
<pre>
#include&lt;stdio.h&gt;

/*-- *xと*yの値を交換 --*/
	void swapd(double [ A ], double *y){
		double temp = *x;
		*x = [ B ];
		*y = [ C ];
	}

/*-- *pa &lt;= *pb &lt;= *pc となるようにソート --*/
	void sort3d ( double *pa, double *pb, double *pc){
		if(*pa &gt; *pb) swapd(pa, pb);
		if(*pb &gt; *pc) swapd(pb, pc);
		if(*pa &gt; *pb) swapd(pa, pb);
	}

	int main (void){
		double d1 = 3.14, d2 = 2.97, d3 = 0.11;
		
		sort3d(&amp;d1, &amp;d2, &amp;d3);
		
		printf("d1の値＝%.3f", d1);/*d1の値を表示*/
		printf("d2の値＝%.3f", d2);/*d2の値を表示*/
		printf("d3の値＝%.3f", d3);/*d3の値を表示*/
		
		return 0;
		
	}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>[
<response id="3">[C]=</response>
<evaluate>
<function>240</function>
<correct id="1">*x</correct>
<correct id="2">*y</correct>
<correct id="3">temp</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>値を入れ替えるときの順番に気をつけましょう。</explanation>
</item>
</program_set>');