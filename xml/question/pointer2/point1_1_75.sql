DELETE from question where program_id = '75';

insert into question values ('75','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point">
<item id="75" type="fill_in_the_blank"><question>
二つの整数xとyの輪をwaが指す変数に、差をsaが指す変数に代入する関数<br/>
void sum_diff(int x, int y, int *wa, int *sa)を作成する。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>
<hr/>
<pre>
#include&lt;stdio.h&gt;

void sum_diff(int x, int y, int *wa, int *sa){
	[ A ] = x +y; /*和の計算*/
	if (x>y){/*差の計算*/
		*sa = x-y;
	}
	else {
		*sa = y-x;
	}
}

int main(void){
	int n1 = 100, n2 = 200;
	int sum, diff;
	
	sum_diff(n1,n2,&amp;sum,&amp;diff);
	
	printf("xとyの和＝%d", sum); /*sumの値を表示*/
	printf("xとyの差＝%d", [ B ]); /*diffの値を表示*/
	
	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>120</function>
<correct id="1">*wa</correct>
<correct id="2">diff</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>sum_diff関数に変数sumとdiffを参照渡しで渡します。そのうえでの変数のアクセスに気をつけて。</explanation>
</item>
</program_set>');