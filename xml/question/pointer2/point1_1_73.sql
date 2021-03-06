DELETE from question where program_id = '73';

insert into question values ('73','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="73" type="fill_in_the_blank"><question>
以下のように宣言が行われており、p1はｘを指し、p2はyを指している。<br/>
<br/>
int x,y;<br/>
int *p1=&amp;x;<br/>
int *p2=&amp;y;<br/>
<br/>
p1がyを指し、p2がxを指すように変えるプログラムを示す。<br/>
[ A ]と[ B ]に当てはまるものは何でしょう。<br/><br/>

<hr/>
<pre>
#include&lt;stdio.h&gt;

int main(void){
	int x,y;
	int *p1=&amp;x;
	int *p2=&amp;y;
	int *temp;
	
	temp = [ A ];
	p1 = [ B ];
	p2 = temp;
	
	printf("*p1の値＝%d", *p1); /*p1が指すyの値を表示*/
	printf("*p2の値＝%d", *p2); /*p2が指すxの値を表示*/
	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>120</function>
<correct id="1">p1</correct>
<correct id="2">p2</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>代入の順番についてよく考えてください。<br/>
p1の値をtempに退避させたうえで、p1にp2の値を上書きします。<br/>
その後、p2にp1の元の値をtempから書き込みます。</explanation>
</item>
</program_set>');