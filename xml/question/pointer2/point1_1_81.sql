insert into question values ('81','point1','1',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="81" type="fill_in_the_blank"><question>
2�̕�����̒��g����ꂩ����֐�<br/>
void swqp_str(char s1[], char s2[])���쐬����B<br/>
[ A ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include&lt;stdio.h&gt;

/*-- �z��̊e�v�f����������֐� --*/
void swap_str(char s1[], char s2[]){

	/*-- �e�v�f���Ƃ�1������ --*/
	while(*s1 || *s2){
		char t = *s1;
		*s1++ = *s2;
		[ A ] = t;
	}
	*s1 = *s2 = '\n0';

}

int main(void){

	char str1[] = "hello";
	char str2[] = "nihao";
	
	swap_str(str1,str2);
	
	printf("str1=%s",str1);
	printf("str2=%s",str2);
	return 0;
}

</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>240</function>
<correct id="1">*s2++</correct>
<score>1</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>*s2�ɑ��������v�f��i�߂Ȃ��Ƃ����܂���B</explanation>
</item>
</program_set>');