insert into question values ('96','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="96" type="fill_in_the_blank"><question>
「one」,「two」,「three」,「four」,「five」,「six」,「seven」,「eight」,「nine」,「ten」の10個の<br/>
文字列をポインタの配列を用いて宣言し、ASCIIコードで昇順に並び替えるプログラムを作ります。<br/>
[ A ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include &lt;stdio.h&gt;
#include &lt;string.h&gt;
#define N 10
        
int main( void )
{
        char *p[N] = {
                "one", "two", "three", 
                "four", "five", "six", 
                "seven", "eight", "nine", 
                "ten"
        };
        char *temp;
        int i, j, min;

        for ( i = 0; i &lt; N - 1; i++ ) {
                min = i;
                for ( j = i + 1; j &lt; N; j++ ) {
                        /* 文字列の比較 */
                        if ( strcmp(p[min], p[j]) &gt; 0 ) {
                                min = j;
                        }
                }
                /* ポインタの配列の入れ換え */
                temp = p[i];
                p[i] = p[min];
                [ A ] = temp;
        }
        for ( i = 0; i &lt; N; i++ )
                puts( p[i] );

        return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">p[min]</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>値同士の入れ替えの手順を思い出してください。</explanation>
</item>
</program_set>');