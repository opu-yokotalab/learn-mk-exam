insert into question values ('86','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="86" type="fill_in_the_blank"><question>
文字列の配列を動的に確保し、そこに文字を記憶させるプログラムです。<br/>
15文字以下であればそのまま格納し、それ以上だと先頭14文字とナル文字を格納する<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include&lt;stdio.h&gt;
#include&lt;stdlib.h&gt;

int main(void){
	int num;		/*文字列の個数*/
	char (*p)[15];	/*文字数は定数15*/
	int i;
	char temp[100];

	printf("文字列は何個：");
	scanf("%d",&amp;num);

	[ A ] = (char (*)[15])[ B ](num * 15);

	if(p == NULL){
		puts("記憶領域の確保に失敗しました。");
	}
	else{

		for(i=0;i&lt;num;i++){
			printf("p[%d]:",i);		/*文字列読み込み*/
			scanf("%s",temp);		/*15文字以上の入力も受け付ける*/
			sprintf(p[i], "%.14s", temp);	/*先頭から14文字までを記憶領域に格納*/
		}

		for(i=0; i&lt;num; i++){
			printf("p[%d] = %s",i,p[i]);	/*文字列を表示*/
		}

		free(p);					/*記憶領域を解放*/
	}

	return 0;

}

○実行例
文字列は何個：3
p[0]:12345678910111213
p[1]:HELLO!!
p[2]:Doyoulikebaseball?
p[0] = 12345678910111
p[1] = HELLO!!
p[2] = Doyoulikebaseb
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">p</correct>
<correct id="2">malloc</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>文字列を動的に確保するにはmallocを用います。</explanation>
</item>
</program_set>');