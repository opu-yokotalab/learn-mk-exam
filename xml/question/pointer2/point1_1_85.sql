insert into question values ('85','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="85" type="fill_in_the_blank"><question>
�|�C���^p������f�[�^��\���\���̂̃I�u�W�F�N�g���w���Ă���B<br/>
����f�[�^���擾����֐��ƁA�\������֐����쐬����B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include&lt;stdio.h&gt;

typedef struct Member{/*����f�[�^*/
	int no;			/*����ԍ�*/
	char name[10];	/*����*/
}Member;

/*����f�[�^�擾�֐�*/
void getMember(Member *p){
	printf("����ԍ��F"); 
	scanf("%d",&amp;p-&gt;no);

	printf("�����F");
	scanf("%s",[ A ]);
}

/*����f�[�^�̕\���֐�*/
void printMember(Member *p){
	printf("����ԍ���%d",p-&gt;no);
	printf("������%s",p-&gt;name);
}

int main(void){
	
	Member m1;
	Member *p = [ B ];

	getMember(p);	/*����f�[�^�̎擾*/
	printMember(p);	/*����f�[�^�̕\��*/

	return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">p-&gt;name</correct>
<correct id="2">&amp;m1</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�����oname�͕�����ł�����A�A�h���X���Z�q&amp;�͕s�v�ł��B</explanation>
</item>
</program_set>');