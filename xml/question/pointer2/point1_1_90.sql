insert into question values ('90','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="90" type="fill_in_the_blank"><question>
���͂��ꂽ������̒�����\��������v���O�������쐬����B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main(void)
{
    char name[100];
    char *p;
    int n=0;

	printf("���������͂��Ă��������F");/*��������擾����*/
	scanf("%s",name);

    for([ A ]; *p != '\0'; p++){/*�������𐔂���*/
        n++;
    }

    printf("%s ��%d�����ł��B",name, n);

    return 0;
}

</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">p=name</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�|�C���^�ϐ��̏��������s��Ȃ��Ƃ�����Ɠ��삵�܂���B</explanation>
</item>
</program_set>');