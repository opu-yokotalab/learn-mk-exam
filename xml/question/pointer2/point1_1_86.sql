insert into question values ('86','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="86" type="fill_in_the_blank"><question>
������̔z��𓮓I�Ɋm�ۂ��A�����ɕ������L��������v���O�����ł��B<br/>
15�����ȉ��ł���΂��̂܂܊i�[���A����ȏゾ�Ɛ擪14�����ƃi���������i�[����<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include&lt;stdio.h&gt;
#include&lt;stdlib.h&gt;

int main(void){
	int num;		/*������̌�*/
	char (*p)[15];	/*�������͒萔15*/
	int i;
	char temp[100];

	printf("������͉��F");
	scanf("%d",&amp;num);

	[ A ] = (char (*)[15])[ B ](num * 15);

	if(p == NULL){
		puts("�L���̈�̊m�ۂɎ��s���܂����B");
	}
	else{

		for(i=0;i&lt;num;i++){
			printf("p[%d]:",i);		/*������ǂݍ���*/
			scanf("%s",temp);		/*15�����ȏ�̓��͂��󂯕t����*/
			sprintf(p[i], "%.14s", temp);	/*�擪����14�����܂ł��L���̈�Ɋi�[*/
		}

		for(i=0; i&lt;num; i++){
			printf("p[%d] = %s",i,p[i]);	/*�������\��*/
		}

		free(p);					/*�L���̈�����*/
	}

	return 0;

}

�����s��
������͉��F3
p[0]:12345678910111213
p[1]:HELLO!!
p[2]:Doyoulikebaseball?
p[0] = 12345678910111
p[1] = HELLO!!
p[2] = Doyoulikebaseb
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">p</correct>
<correct id="2">malloc</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>������𓮓I�Ɋm�ۂ���ɂ�malloc��p���܂��B</explanation>
</item>
</program_set>');