insert into question values ('78','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="78" type="fill_in_the_blank"><question>
�z���x�Ɏ󂯎���āA*x[0]&lt;=*x[1]&lt;=*x[2]�ƂȂ�悤��<br/>
�\�[�g����֐�void sort_ptr3ary(int *x[])���쐬����B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include&lt;stdio.h&gt;

/*-- *x��*y�̒l������ --*/
	void swap(int *x, int *y){
		int temp = *x;
		*x = *y;
		*y = temp;
	}
	
/*-- *x[0]&lt;= *x[1]&lt;= *x[2]�ƂȂ�悤�Ƀ\�[�g --*/
	void sort_ptr3ary(int *x[]){
		if(*x[0] &gt; *x[1])swap(x[0],x[1]);
		if(*x[1] &gt; *x[2])swap(x[1],x[2]);
		if(*x[0] &gt; *[ A ])swap(x[0],[ A ]);
	}
	
	int main(void){
		int a=5, b=3, c=7;
		int *p[3];
		int i;
		
		p[0] = &amp;a;
		p[1] = &amp;b;
		p[2] = &amp;c;
		
		sort_ptr3ary([ B ]);
		
		for(i=0; i&lt;3;i++){
			printf("*p[%d]�̒l��%d", i, *p[i]);
		}
		return 0;
	}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>240</function>
<correct id="1">x[1]</correct>
<correct id="2">p</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�ǂ̂悤�ȏ��ԂŃ\�[�g����Ă��邩�������Ă݂悤�B</explanation>
</item>
</program_set>');