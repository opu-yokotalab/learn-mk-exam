insert into question values ('84','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="84" type="fill_in_the_blank"><question>
�\����xyz�^��2�̃I�u�W�F�N�g�ւ̃|�C���^���󂯎��Aa-&gt;��b-&gt;x1�ƂȂ�悤�ɕ��ׂ�����֐�<br/>
void sortXYZ(struct xyz *a, struct xyz *b)���쐬����B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include&lt;stdio.h&gt;

typedef struct xyz{
	int x;
	long y;
	double z;
}xyz;

/*-- a�̎w���\���̂̃����o�ɒl��ǂݍ��� --*/
void getXYZ(xyz *a){
	printf("x : ");	scanf("%d",&amp;a-&gt;x);
	printf("y : ");	scanf("%ld",&amp;a-&gt;y);
	printf("z : ");	scanf("%lf",&amp;a-&gt;z);
}

/*-- �����ox�̏����ƂȂ�悤��a,b����בւ��� --*/
void sortXYZ(struct xyz *a, struct xyz *b){
	if(a-&gt;x &gt; b-&gt;x){
		struct xyz temp = *a;
		*a = *b;
		*b = temp;
	}
}


int main(void){
	xyz	a,b;
	
	/*a�ɒl����*/
	getXYZ([ A ]);
	/*b�ɒl����*/
	getXYZ(&amp;b);
	
	/*x�̏����Ƀ\�[�g*/
	sortXYZ([ B ],&amp;b);

	printf("a.x = %d", a.x);	/*�����o�l��\��*/
	printf("a.y = %ld", a.y);	/*�����o�l��\��*/
	printf("a.z = %lf", a.z);	/*�����o�l��\��*/
	
	printf("b.x = %d", b.x);	/*�����o�l��\��*/
	printf("b.y = %ld", b.y);	/*�����o�l��\��*/
	printf("b.z = %lf", b.z);	/*�����o�l��\��*/

	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">&amp;a</correct>
<correct id="2">&amp;a</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�\�[�g����ɂ͕��בւ���Ώۂ������ɓn���K�v������܂��B</explanation>
</item>
</program_set>');