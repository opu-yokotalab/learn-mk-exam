insert into question values ('95','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="95" type="fill_in_the_blank"><question>
�����^�z�� str1 �ɁA�uABCDEFGHIJKLMNOPQRSTUVWXYZ�v �Ƃ��������񂪊i�[����Ă���B<br/>
�|�C���^��p���āA�����^�z�� str2 �ɁA���̕�������t���Ɋi�[����v���O���������Ȃ����B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include &lt;stdio.h&gt;

int main( void )
{
	char str1[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	char str2[30];
	char *p1, *p2;
	
	p1 = str1;
	p2 = str2;
	
	while ( *p1 != '\0' ) {	/* (1)p1��str1�̍Ō�܂Ői�߂� */
		[ A ];
	}
	while (p1 &gt; str1) {	/* p1��str1���w���ԃ��[�v */
		p1--;		/* p1�͌�납��O�ɐi�߂� */
		*p2 = *p1;	/* p2�̎w���Ƃ����p1�̎w���l���� */
		p2++;		/* p2�͑O������ɐi�߂� */
	}
	[ B ] = '\0';		/* '\0'�̕t�� */
	printf( "str1 = %s\n", str1 );
	printf( "str2 = %s\n", str2 );

	return 0;
}


���s��
str1 = ABCDEFGHIJKLMNOPQRSTUVWXYZ
str2 = ZYXWVUTSRQPONMLKJIHGFEDCBA
</pre>
</question>
<response id="1">[A]=</response>
<response id="2">[B]=</response>
<evaluate>
<function>180</function>
<correct id="1">p1++</correct>
<correct id="2">*p2</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>str2�̍Ō�ɂ̓i�����������Ȃ��Ƃ����܂���B</explanation>
</item>
</program_set>');