insert into question values ('79','point1','2',
'<?xml version="1.0" encoding="shift_JIS" ?>
<program_set id="point1">
<item id="79" type="fill_in_the_blank"><question>
int b[3][2][4];�̑S�v�f�̃A�h���X��\������v���O�������쐬�A���s����B<br/>
�ȉ��Ƀ\�[�X�R�[�h�ƁA���s���ʂ̈���\������B<br/>
[ A ]��[ B ]�ɓ��Ă͂܂���͉̂��ł��傤�B<br/><br/>

<pre>
#include&lt;stdio.h&gt;

int main(void){
	int i,j,k;
	int b[3][2][4];
	
	for(i=0; i &lt; 3; i++){
		for(j=0; j &lt; 2; j++){
			for(k=0; k &lt; 4; k++){
				printf("&amp;b[%d][%d][%d] = %p",i,j,k, &amp;b[i][j][k])
			}
		}
	}
}

���s���ʈ��
&amp;[0][0][0] = 1000
&amp;[0][0][1] = [ A ]
&amp;[0][0][2] = 1004
</pre>
</question>
<response id="1">[A]=</response>
<evaluate>
<function>240</function>
<correct id="1">1002</correct>
<score>2</score>
<weight/>
<point>10</point>
</evaluate>
<explanation>int�^��2�o�C�g�Ȃ̂�1���̗v�f�̃A�h���X�͖͔͉𓚂̂悤�ɂȂ�B</explanation>
</item>
</program_set>');