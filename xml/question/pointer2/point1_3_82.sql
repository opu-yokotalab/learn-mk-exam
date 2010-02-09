insert into question values ('82','point1','3',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="82" type="fill_in_the_blank"><question>
�p�P����w�K����v���O�������쐬����B<br/>
���̃v���O�����ł͓��{�ꂠ�邢�͉p��̒P���\������<br/>
����ɑΉ�����p�ꂠ�邢�͓��{���I�΂���B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B�i���̓��C���֐��ɂ���܂��j<br/><br/>

<pre>
�����s��
���a�͂ǂ�ł���
(0)magazine (1)book (2)peace (3)love  :2
�����ł�
������x���킵�܂����H(0-NO!/1-YES):1
book�͂ǂ�ł���
(0)�� (1)�{ (2)�� (3)���a  :1
�Ⴂ�܂�
(0)�� (1)�{ (2)�� (3)���a  :3
�����ł�
������x���킵�܂����H(0-NO!/1-YES):0


���\�[�X�R�[�h
#include&lt;stdio.h&gt;
#include&lt;time.h&gt;
#include&lt;stdlib.h&gt;
#include&lt;limits.h&gt;

#define QNO 12	/*�P��̐�*/
#define CNO 4	/*�I�����̐�*/

#define swap(type, x, y) do { type t=x; x=y; y=t;}while(0)

/*-- ���{�� --*/
char *jptr[] = {
	"����","��", "��","��","��","�{",
	"�֎q","��","��","��","���a","�G��",
};

/*-- �p�� --*/
char *eptr[] = {
	"animal","car","flower","house","desk","book",
	"chair","father","mother","love","peace","magazine",
};

/*-- �I�������쐬�������̓Y������Ԃ� --*/
int make_cand(int c[],int n){
	int i,j;
	c[0] = n;
	for(i=1;i&lt; CNO; i++){
		int x;
		do {
			x = rand() % QNO;/*���̑I�����̌���*/
			for(j=0; j&lt;i; j++)
				if(c[j] == x)
					break;
		}while(i != j);
		c[i] = x;
	}
	j = rand()%CNO;			/*�����ʒu�̌���*/
	swap(int, c[0], c[j]);	/*�����ʒu�̓���ւ�*/

	return (j);
}

/*-- �I������\�� --*/
void print_cand(int c[],int sw){
	int i;
	for(i=0; i&lt;CNO; i++){
		printf("(%d) %s  ",i,sw ? jptr[c[i]] : eptr[c[i]]);
	}
	printf(" : ");
}

/*-- ���C���֐� --*/
int main(void){
	int nq;			/*���ԍ�*/
	int na;			/*�����̔ԍ�*/
	int sw;			/*���`�������߂�t���O-0�F�p�ꁨ���{��^1�F���{�ꁨ�p��*/
	int retry;		/*�Ē��킷�邩*/
	int cand[CNO];	/*�I�����ԍ�*/
	time_t t;		/*���݂̎���*/

	srand(time(&amp;t) % RAND_MAX);/*rand�֐��Ŕ���������[�������̔����n��̕ύX*/

	do{
		int no;

		nq = rand() % QNO;			/*�o����������*/
		na = make_cand(cand, nq);	/* �I�������쐬�������̓Y������Ԃ� */
		sw = rand() % 2;			/*�p�ꁨ���{��^���{�ꁨ�p��̂ǂ���ŏo��������*/

		printf("%s�͂ǂ�ł���\n",sw ? eptr[nq] : jptr[nq]);
		/*sw�̒l�ɂ���ē��{�ꁨ�p��̖�肪�o�邩�A�p�ꁨ���{��̖�肪�o�邩����*/
		
		do{
			print_cand([ A ], sw);	/*�I������񎦂���֐��̈����́����Ƃǂ̖��`���ŏo�����̃t���O*/
			scanf("%d", &amp;no);
			if (no != na)puts("�Ⴂ�܂��B");
		}while(no != na);
		
		puts("�����ł��B");
		printf("������x���킵�܂����H(0-NO!/1-YES):");
		scanf("%d",&amp;retry);
	}while (retry == 1);

	return 0;
}

</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>600</function>
<correct id="1">cand</correct>
<score>3</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�I������\�����邽�߂ɂ͂ǂ�ȏ�񂪕K�v���l���Ă��������B</explanation>
</item>
</program_set>');