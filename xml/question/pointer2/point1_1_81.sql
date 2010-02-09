insert into question values ('81','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="81" type="fill_in_the_blank"><question>
2つの文字列の中身を入れかえる関数<br/>
void swqp_str(char s1[], char s2[])を作成せよ。<br/>
[ A ]に当てはまるものは何でしょう。<br/><br/>

<pre>
#include&lt;stdio.h&gt;

/*-- 配列の各要素を交換する関数 --*/
void swap_str(char s1[], char s2[]){

	/*-- 各要素ごとを1つずつ交換 --*/
	while(*s1 || *s2){
		char t = *s1;
		*s1++ = *s2;
		[ A ] = t;
	}
	*s1 = *s2 = '\n0';

}

int main(void){

	char str1[] = "hello";
	char str2[] = "nihao";
	
	swap_str(str1,str2);
	
	printf("str1=%s",str1);
	printf("str2=%s",str2);
	return 0;
}

</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>240</function>
<correct id="1">*s2++</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>*s2に代入した後要素を進めないといけません。</explanation>
</item>
</program_set>');