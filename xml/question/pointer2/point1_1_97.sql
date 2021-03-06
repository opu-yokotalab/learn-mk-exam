insert into question values ('97','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="97" type="fill_in_the_blank"><question>
生徒情報を構造体として作成し、それぞれの科目の平均点を求めるプログラムを作ります。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include &lt;stdio.h&gt;

#define	 NINZU 5	/* 学生の人数 */
#define	 KAMOKU 4	/* 科目数 */

struct seiseki {
	int no;				/*学籍番号*/
	int kamoku[KAMOKU];	/*科目ごとの点数*/
};

int main( void )
{
	struct seiseki mycls[NINZU+1] = {
		{ 1001, 85, 74, 63, 90 }, 
		{ 1002, 78, 65, 70, 62 }, 
		{ 1003, 89, 92, 88, 76 }, 
		{ 1004, 32, 48, 66, 25 }, 
		{ 1005, 92, 76, 81, 98 }, 
		{   -1,  0,  0,  0,  0 } };
	struct seiseki *my_p;
	char *kname[] = { "国語", "数学", "理科", "社会" };
	int i, j;
	double heikin;

	my_p = [ A ];	/*構造体ポインタの初期化*/
	for ( j = 0; j &lt; KAMOKU; j++ ) {
		i = 0;
		heikin = 0.0;	/*平均値の初期化*/
		while( ( my_p+i )-&gt;no != -1 ) {
			heikin = heikin + ( [ B ] )-&gt;kamoku[j];/*ポインタを用いて点数を合計していく*/
			i++;
		}
		heikin = heikin / i;	/*合計点を人数で割る*/
		printf( "%s 平均 = %6.2f\n", kname[j], heikin );
	}
	
	return 0;
}

実行結果例

国語 平均 = 75.20
数学 平均 = 71.00
理科 平均 = 73.60
社会 平均 = 70.20
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">mycls</correct>
<correct id="2">my_p+i</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>ポインタ変数は初期化する必要があり、アクセスするときは配列の先頭アドレスを表します。</explanation>
</item>
</program_set>');