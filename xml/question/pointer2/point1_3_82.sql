insert into question values ('82','point1','3',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="82" type="fill_in_the_blank"><question>
英単語を学習するプログラムを作成する。<br/>
このプログラムでは日本語あるいは英語の単語を表示して<br/>
それに対応する英語あるいは日本語を選ばせる。<br/>
[ A ]に当てはまるものは何でしょう。（穴はメイン関数にあります）<br/><br/>

<pre>
○実行例
平和はどれですか
(0)magazine (1)book (2)peace (3)love  :2
正解です
もう一度挑戦しますか？(0-NO!/1-YES):1
bookはどれですか
(0)愛 (1)本 (2)机 (3)平和  :1
違います
(0)愛 (1)本 (2)机 (3)平和  :3
正解です
もう一度挑戦しますか？(0-NO!/1-YES):0


○ソースコード
#include&lt;stdio.h&gt;
#include&lt;time.h&gt;
#include&lt;stdlib.h&gt;
#include&lt;limits.h&gt;

#define QNO 12	/*単語の数*/
#define CNO 4	/*選択肢の数*/

#define swap(type, x, y) do { type t=x; x=y; y=t;}while(0)

/*-- 日本語 --*/
char *jptr[] = {
	"動物","車", "花","家","机","本",
	"椅子","父","母","愛","平和","雑誌",
};

/*-- 英語 --*/
char *eptr[] = {
	"animal","car","flower","house","desk","book",
	"chair","father","mother","love","peace","magazine",
};

/*-- 選択肢を作成し正解の添え字を返す --*/
int make_cand(int c[],int n){
	int i,j;
	c[0] = n;
	for(i=1;i&lt; CNO; i++){
		int x;
		do {
			x = rand() % QNO;/*誤りの選択肢の決定*/
			for(j=0; j&lt;i; j++)
				if(c[j] == x)
					break;
		}while(i != j);
		c[i] = x;
	}
	j = rand()%CNO;			/*正解位置の決定*/
	swap(int, c[0], c[j]);	/*正解位置の入れ替え*/

	return (j);
}

/*-- 選択肢を表示 --*/
void print_cand(int c[],int sw){
	int i;
	for(i=0; i&lt;CNO; i++){
		printf("(%d) %s  ",i,sw ? jptr[c[i]] : eptr[c[i]]);
	}
	printf(" : ");
}

/*-- メイン関数 --*/
int main(void){
	int nq;			/*問題番号*/
	int na;			/*正解の番号*/
	int sw;			/*問題形式を決めるフラグ-0：英語→日本語／1：日本語→英語*/
	int retry;		/*再挑戦するか*/
	int cand[CNO];	/*選択肢番号*/
	time_t t;		/*現在の時刻*/

	srand(time(&amp;t) % RAND_MAX);/*rand関数で発生させる擬似乱数の発生系列の変更*/

	do{
		int no;

		nq = rand() % QNO;			/*出す問題を決定*/
		na = make_cand(cand, nq);	/* 選択肢を作成し正解の添え字を返す */
		sw = rand() % 2;			/*英語→日本語／日本語→英語のどちらで出すか決定*/

		printf("%sはどれですか\n",sw ? eptr[nq] : jptr[nq]);
		/*swの値によって日本語→英語の問題が出るか、英語→日本語の問題が出るか決定*/
		
		do{
			print_cand([ A ], sw);	/*選択肢を提示する関数の引数は○○とどの問題形式で出すかのフラグ*/
			scanf("%d", &amp;no);
			if (no != na)puts("違います。");
		}while(no != na);
		
		puts("正解です。");
		printf("もう一度挑戦しますか？(0-NO!/1-YES):");
		scanf("%d",&amp;retry);
	}while (retry == 1);

	return 0;
}

</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>600</function>
<correct id="1">cand</correct>
<score>3</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>選択肢を表示するためにはどんな情報が必要か考えてください。</explanation>
</item>
</program_set>');