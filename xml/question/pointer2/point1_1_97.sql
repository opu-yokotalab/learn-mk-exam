insert into question values ('97','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="97" type="fill_in_the_blank"><question>
���k�����\���̂Ƃ��č쐬���A���ꂼ��̉Ȗڂ̕��ϓ_�����߂�v���O���������܂��B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;

#define	 NINZU 5	/* �w���̐l�� */
#define	 KAMOKU 4	/* �Ȗڐ� */

struct seiseki {
	int no;				/*�w�Дԍ�*/
	int kamoku[KAMOKU];	/*�Ȗڂ��Ƃ̓_��*/
};

int main( void )
{
	struct seiseki mycls[NINZU+1] = {
		{ 1001, 85, 74, 63, 90 }, 
		{ 1002, 78, 65, 70, 62 }, 
		{ 1003, 89, 92, 88, 76 }, 
		{ 1004, 32, 48, 66, 25 }, 
		{ 1005, 92, 76, 81, 98 }, 
		{   -1,  0,  0,  0,  0 } };
	struct seiseki *my_p;
	char *kname[] = { "����", "���w", "����", "�Љ�" };
	int i, j;
	double heikin;

	my_p = [ A ];	/*�\���̃|�C���^�̏�����*/
	for ( j = 0; j &lt; KAMOKU; j++ ) {
		i = 0;
		heikin = 0.0;	/*���ϒl�̏�����*/
		while( ( my_p+i )-&gt;no != -1 ) {
			heikin = heikin + ( [ B ] )-&gt;kamoku[j];/*�|�C���^��p���ē_�������v���Ă���*/
			i++;
		}
		heikin = heikin / i;	/*���v�_��l���Ŋ���*/
		printf( "%s ���� = %6.2f\n", kname[j], heikin );
	}
	
	return 0;
}

���s���ʗ�

���� ���� = 75.20
���w ���� = 71.00
���� ���� = 73.60
�Љ� ���� = 70.20
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">mycls</correct>
<correct id="2">my_p+i</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�|�C���^�ϐ��͏���������K�v������A�A�N�Z�X����Ƃ��͔z��̐擪�A�h���X��\���܂��B</explanation>
</item>
</program_set>');