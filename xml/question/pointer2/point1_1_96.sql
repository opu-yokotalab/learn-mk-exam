insert into question values ('96','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="96" type="fill_in_the_blank"><question>
�uone�v,�utwo�v,�uthree�v,�ufour�v,�ufive�v,�usix�v,�useven�v,�ueight�v,�unine�v,�uten�v��10��<br/>
��������|�C���^�̔z���p���Đ錾���AASCII�R�[�h�ŏ����ɕ��ёւ���v���O���������܂��B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;
#include &lt;string.h&gt;
#define N 10
        
int main( void )
{
        char *p[N] = {
                "one", "two", "three", 
                "four", "five", "six", 
                "seven", "eight", "nine", 
                "ten"
        };
        char *temp;
        int i, j, min;

        for ( i = 0; i &lt; N - 1; i++ ) {
                min = i;
                for ( j = i + 1; j &lt; N; j++ ) {
                        /* ������̔�r */
                        if ( strcmp(p[min], p[j]) &gt; 0 ) {
                                min = j;
                        }
                }
                /* �|�C���^�̔z��̓��ꊷ�� */
                temp = p[i];
                p[i] = p[min];
                [ A ] = temp;
        }
        for ( i = 0; i &lt; N; i++ )
                puts( p[i] );

        return 0;
}
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>180</function>
<correct id="1">p[min]</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>�l���m�̓���ւ��̎菇���v���o���Ă��������B</explanation>
</item>
</program_set>');