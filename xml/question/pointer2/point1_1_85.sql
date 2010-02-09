insert into question values ('85','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="85" type="fill_in_the_blank"><question>
ポインタpが会員データを表す構造体のオブジェクトを指している。<br/>
会員データを取得する関数と、表示する関数を作成する。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include&lt;stdio.h&gt;

typedef struct Member{/*会員データ*/
	int no;			/*会員番号*/
	char name[10];	/*氏名*/
}Member;

/*会員データ取得関数*/
void getMember(Member *p){
	printf("会員番号："); 
	scanf("%d",&amp;p-&gt;no);

	printf("氏名：");
	scanf("%s",[ A ]);
}

/*会員データの表示関数*/
void printMember(Member *p){
	printf("会員番号＝%d",p-&gt;no);
	printf("氏名＝%s",p-&gt;name);
}

int main(void){
	
	Member m1;
	Member *p = [ B ];

	getMember(p);	/*会員データの取得*/
	printMember(p);	/*会員データの表示*/

	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">p-&gt;name</correct>
<correct id="2">&amp;m1</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>メンバnameは文字列ですから、アドレス演算子&amp;は不要です。</explanation>
</item>
</program_set>');