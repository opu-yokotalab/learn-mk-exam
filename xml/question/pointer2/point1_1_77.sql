insert into question values ('77','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="77" type="fill_in_the_blank"><question>
�ȉ��̂悤�ɔz��̗v�f�̃A�h���X��\������v���O���������Ȃ����B
---- ���s���ʗ� ----
&amp;a[0] = 100 , p+0 = 100<br/>
&amp;a[1] = 102 , p+0 = 102<br/>
&amp;a[2] = 104 , p+0 = 104<br/>
&amp;a[3] = 106 , p+0 = 106<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>
<hr/>
<pre>
#include&lt;stdio.h&gt;

int main(void){
	int i;
	int a[5];
	int *p = a;
	
	for(i=0; i &lt; 5; i++){
	/*�l�ɃA�h���X���Z�q��K�p�����\���ƁA�|�C���^�ϐ���p�����\�����s��*/
		printf("%a[%d] = %p , p+%d = %p",i, [ A ],i, [ B ]);
	}
	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>240</function>
<correct id="1">&amp;a[i]</correct>
<correct id="2">p+i</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�|�C���^�ϐ���p�����A�N�Z�X�@���o���Ă������B<br/>
�|�C���^�ϐ���p���Ĕz��̗v�f�ɃA�N�Z�X����ƌ����I�ŕ֗�</explanation>
</item>
</program_set>');