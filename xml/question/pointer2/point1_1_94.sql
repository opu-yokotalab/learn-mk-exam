insert into question values ('94','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="94" type="fill_in_the_blank"><question>
�Í��̃v���O�����ɂ͂��낢��ȃA���S���Y�������邪�A<br/>
���̒��ŁA�����R�[�h��1�����Z���ĈÍ������쐬����v���O���������B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main(void)
{
    char buff[256], *pb;    /* ���͕����� */

	pb = buff;    /* �|�C���^�ϐ������� */

    printf("�Í����쐬�v���O����");
    printf("������ : ");    /* �v�����v�g�\�� */
    gets(buff);            /* ��������� */

	while(*pb){      /* �k�������܂ŌJ��Ԃ� */
		*pb = [ A ];    /* �Í��ɕϊ� */
        ++pb;
    }
    printf("�Í��� :%s", buff);
    return(0);
}


���s��
�Í����쐬�v���O����
������Fabcde
�Í����Fbcdef
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">*pb+1</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>1������������1�����Z���Ă����܂��B</explanation>
</item>
</program_set>');