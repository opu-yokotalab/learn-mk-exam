insert into question values ('90','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="90" type="fill_in_the_blank"><question>
入力された文字列の長さを表示させるプログラムを作成する。<br/>
[ A ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main(void)
{
    char name[100];
    char *p;
    int n=0;

	printf("文字列を入力してください：");/*文字列を取得する*/
	scanf("%s",name);

    for([ A ]; *p != '\0'; p++){/*文字数を数える*/
        n++;
    }

    printf("%s は%d文字です。",name, n);

    return 0;
}

</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">p=name</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>ポインタ変数の初期化を行わないときちんと動作しません。</explanation>
</item>
</program_set>');