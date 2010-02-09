insert into question values ('94','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="94" type="fill_in_the_blank"><question>
暗号のプログラムにはいろいろなアルゴリズムがあるが、<br/>
その中で、文字コードに1を加算して暗号文を作成するプログラムを作る。<br/>
[ A ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main(void)
{
    char buff[256], *pb;    /* 入力文字列 */

	pb = buff;    /* ポインタ変数初期化 */

    printf("暗号文作成プログラム");
    printf("文字列 : ");    /* プロンプト表示 */
    gets(buff);            /* 文字列入力 */

	while(*pb){      /* ヌル文字まで繰り返す */
		*pb = [ A ];    /* 暗号に変換 */
        ++pb;
    }
    printf("暗号文 :%s", buff);
    return(0);
}


実行例
暗号文作成プログラム
文字列：abcde
暗号文：bcdef
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">*pb+1</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>1文字ずつ文字に1を加算していきます。</explanation>
</item>
</program_set>');