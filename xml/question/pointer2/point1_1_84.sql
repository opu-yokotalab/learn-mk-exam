insert into question values ('84','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="84" type="fill_in_the_blank"><question>
構造体xyz型の2つのオブジェクトへのポインタを受け取り、a-&gt;≦b-&gt;x1となるように並べかえる関数<br/>
void sortXYZ(struct xyz *a, struct xyz *b)を作成せよ。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include&lt;stdio.h&gt;

typedef struct xyz{
	int x;
	long y;
	double z;
}xyz;

/*-- aの指す構造体のメンバに値を読み込む --*/
void getXYZ(xyz *a){
	printf("x : ");	scanf("%d",&amp;a-&gt;x);
	printf("y : ");	scanf("%ld",&amp;a-&gt;y);
	printf("z : ");	scanf("%lf",&amp;a-&gt;z);
}

/*-- メンバxの昇順となるようにa,bを並べ替える --*/
void sortXYZ(struct xyz *a, struct xyz *b){
	if(a-&gt;x &gt; b-&gt;x){
		struct xyz temp = *a;
		*a = *b;
		*b = temp;
	}
}


int main(void){
	xyz	a,b;
	
	/*aに値を代入*/
	getXYZ([ A ]);
	/*bに値を代入*/
	getXYZ(&amp;b);
	
	/*xの昇順にソート*/
	sortXYZ([ B ],&amp;b);

	printf("a.x = %d", a.x);	/*メンバ値を表示*/
	printf("a.y = %ld", a.y);	/*メンバ値を表示*/
	printf("a.z = %lf", a.z);	/*メンバ値を表示*/
	
	printf("b.x = %d", b.x);	/*メンバ値を表示*/
	printf("b.y = %ld", b.y);	/*メンバ値を表示*/
	printf("b.z = %lf", b.z);	/*メンバ値を表示*/

	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">&amp;a</correct>
<correct id="2">&amp;a</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>ソートするには並べ替える対象を引数に渡す必要があります。</explanation>
</item>
</program_set>');