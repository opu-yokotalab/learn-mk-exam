insert into question values ('91','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="91" type="fill_in_the_blank"><question>
入力した文字列の英小文字だけを大文字に変換して表示するプログラムを作る。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
●関数説明
#include &lt;ctype.h&gt;
int toupper(int ch); 
文字chが英小文字であれば、大文字に変換した値を返し、それ以外はそのままの値を返す。

--ここからソースコード--
#include &lt;stdio.h&gt;
#include &lt;ctype.h&gt;

int main(void)
{
    char buff[256], *pb;    /* 入力文字列 */

    pb = [ A ];    /* ポインタ変数初期化 */

    printf("文字列 : ");
    gets(buff);            /* 文字列入力 */

    while(*pb){    /* ヌル文字まで繰り返す */
        *pb = toupper(*pb);	/* 大文字変換 */
        [ B ];				/*次の文字へ*/
    }
    printf("文字列は%sです\n", buff);    /* 表示 */
    return(0);
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">buff</correct>
<correct id="2">++pb</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>配列の名前は配列の先頭要素のアドレスを指すので初期化するとき&amp;はいりません。</explanation>
</item>
</program_set>');