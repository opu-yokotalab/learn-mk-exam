insert into question values ('93','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="93" type="fill_in_the_blank"><question>
���͂��ꂽ�������1�����Ԋu�ŕ\������v���O���������܂��B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main(void)
{
    char buff[256], *pb;    /* ���͕�����p, �|�C���^ */

    pb = buff;     /* �|�C���^�ϐ������� */

    printf("������ : ");     /* �v�����v�g�\�� */
    gets(pb);               /* ��������� */

    while(*pb != '\0'){     /* �i�������܂ŌJ��Ԃ� */
        printf("%c ", [ A ]);    /* 1�������\�� */
        ++pb;
    }
    return(0);
}

���s��
������Fabcde
a b c d e
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">*pb</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>buff�Ɋi�[���ꂽ������1�������Q�Ƃ��Ă����܂��B</explanation>
</item>
</program_set>');