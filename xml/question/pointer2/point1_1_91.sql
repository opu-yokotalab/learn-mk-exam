insert into question values ('91','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="91" type="fill_in_the_blank"><question>
���͂���������̉p������������啶���ɕϊ����ĕ\������v���O���������B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
���֐�����
#include &lt;ctype.h&gt;
int toupper(int ch); 
����ch���p�������ł���΁A�啶���ɕϊ������l��Ԃ��A����ȊO�͂��̂܂܂̒l��Ԃ��B

--��������\�[�X�R�[�h--
#include &lt;stdio.h&gt;
#include &lt;ctype.h&gt;

int main(void)
{
    char buff[256], *pb;    /* ���͕����� */

    pb = [ A ];    /* �|�C���^�ϐ������� */

    printf("������ : ");
    gets(buff);            /* ��������� */

    while(*pb){    /* �k�������܂ŌJ��Ԃ� */
        *pb = toupper(*pb);	/* �啶���ϊ� */
        [ B ];				/*���̕�����*/
    }
    printf("�������%s�ł�\n", buff);    /* �\�� */
    return(0);
}
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">buff</correct>
<correct id="2">++pb</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�z��̖��O�͔z��̐擪�v�f�̃A�h���X���w���̂ŏ���������Ƃ�&amp;�͂���܂���B</explanation>
</item>
</program_set>');