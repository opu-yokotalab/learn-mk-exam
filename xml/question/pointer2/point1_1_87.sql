insert into question values ('87','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="87" type="fill_in_the_blank"><question>
0〜100の範囲で入力された複数の数値の中から、最大値と最小値を求めて表示するプログラムを作成せよ。<br/>
-1が入力された場合は入力の終わりと判定する。ただし、最大値と最小値はmain関数以外の一つの関数の中で求める。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include &lt;stdio.h&gt;

void maxmin(int array[],int *max,int *min);

int main(void)
{
	int i = 0,array[10],max,min;
	
	do {
		printf("%d 番目の数:",i + 1);	/*値の取得*/
		scanf("%d",&amp;array[i]);
		i++;
	} while (array[i - 1] != -1);		/*-1が入力されるまで入力を行う*/
	
	maxmin(array,&amp;max,&amp;min);
	
	printf("最大値 %d : 最小値 %d",max,min);
	
	return 0;
}

/*-- 比較関数 --*/
void maxmin(int array[],int [ A ],int [ B ])
{
	int i = 0;
	
	*max = 0;	/*最大値*/
	*min = 100;	/*最小値*/
	
	while (array[i] != -1) {
		if (array[i] &gt; *max) *max = array[i];/*最大値より大きければ代入*/
		if (array[i] &lt; *min) *min = array[i];/*最小値より小さければ代入*/
		i++;
	}
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">*max</correct>
<correct id="2">*min</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>関数に参照渡しで値を渡すので、引数はポインタになります。</explanation>
</item>
</program_set>');