DELETE from question where program_id = '73';

insert into question values ('73','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="73" type="fill_in_the_blank"><question>
�ȉ��̂悤�ɐ錾���s���Ă���Ap1�͂����w���Ap2��y���w���Ă���B<br/>
<br/>
int x,y;<br/>
int *p1=&amp;x;<br/>
int *p2=&amp;y;<br/>
<br/>
p1��y���w���Ap2��x���w���悤�ɕς���v���O�����������B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

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
	
	printf("*p1�̒l��%d", *p1); /*p1���w��y�̒l��\��*/
	printf("*p2�̒l��%d", *p2); /*p2���w��x�̒l��\��*/
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
<explanation>����̏��Ԃɂ��Ă悭�l���Ă��������B<br/>
p1�̒l��temp�ɑޔ������������ŁAp1��p2�̒l���㏑�����܂��B<br/>
���̌�Ap2��p1�̌��̒l��temp���珑�����݂܂��B</explanation>
</item>
</program_set>');