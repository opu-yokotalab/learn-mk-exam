insert into question values ('95','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="95" type="fill_in_the_blank"><question>
文字型配列 str1 に、「ABCDEFGHIJKLMNOPQRSTUVWXYZ」 という文字列が格納されている。<br/>
ポインタを用いて、文字型配列 str2 に、この文字列を逆順に格納するプログラムを作りなさい。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main( void )
{
	char str1[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	char str2[30];
	char *p1, *p2;
	
	p1 = str1;
	p2 = str2;
	
	while ( *p1 != '\0' ) {	/* (1)p1をstr1の最後まで進める */
		[ A ];
	}
	while (p1 &gt; str1) {	/* p1がstr1を指す間ループ */
		p1--;		/* p1は後ろから前に進める */
		*p2 = *p1;	/* p2の指すところにp1の指す値を代入 */
		p2++;		/* p2は前から後ろに進める */
	}
	[ B ] = '\0';		/* '\0'の付加 */
	printf( "str1 = %s\n", str1 );
	printf( "str2 = %s\n", str2 );

	return 0;
}


実行例
str1 = ABCDEFGHIJKLMNOPQRSTUVWXYZ
str2 = ZYXWVUTSRQPONMLKJIHGFEDCBA
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">p1++</correct>
<correct id="2">*p2</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>str2の最後にはナル文字を入れないといけません。</explanation>
</item>
</program_set>');