insert into question values ('76','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="76" type="fill_in_the_blank"><question>
pa,pb,pc���w��3��double�^���������_�l��*pa&lt;=*pb&lt;=*pc�ƂȂ�悤�ɁA<br/>
�����Ƀ\�[�g����֐�void sort3d(double *pa,double *pb, double *pc){}���쐬����B<br/>
[ A ]��[ B ]��[ C ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>
<hr/>
<pre>
#include&lt;stdio.h&gt;

/*-- *x��*y�̒l������ --*/
	void swapd(double [ A ], double *y){
		double temp = *x;
		*x = [ B ];
		*y = [ C ];
	}

/*-- *pa &lt;= *pb &lt;= *pc �ƂȂ�悤�Ƀ\�[�g --*/
	void sort3d ( double *pa, double *pb, double *pc){
		if(*pa &gt; *pb) swapd(pa, pb);
		if(*pb &gt; *pc) swapd(pb, pc);
		if(*pa &gt; *pb) swapd(pa, pb);
	}

	int main (void){
		double d1 = 3.14, d2 = 2.97, d3 = 0.11;
		
		sort3d(&amp;d1, &amp;d2, &amp;d3);
		
		printf("d1�̒l��%.3f", d1);/*d1�̒l��\��*/
		printf("d2�̒l��%.3f", d2);/*d2�̒l��\��*/
		printf("d3�̒l��%.3f", d3);/*d3�̒l��\��*/
		
		return 0;
		
	}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>[
<response id="3">[C]=</response>
<evaluate>
<function>240</function>
<correct id="1">*x</correct>
<correct id="2">*y</correct>
<correct id="3">temp</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�l�����ւ���Ƃ��̏��ԂɋC�����܂��傤�B</explanation>
</item>
</program_set>');