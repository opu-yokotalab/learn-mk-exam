import org.w3c.dom.*;
import java.util.*;

/*
  ��������̊e�f�[�^��ێ����Ă���N���X�B
*/
public class QuestionMap{
  int qtype[][];
  ArrayList questionlist;
  ArrayList answerlist;
  //ArrayList[][] questions;
  int total;

  ArrayList program_set;
  ArrayList id;
  ArrayList hints;
  ArrayList function;
  ArrayList sentence;
  ArrayList score;
  ArrayList weight;
  ArrayList point;
  ArrayList explanation;

  QuestionMap(int section ,int[] subsection){
    //System.out.println("section:"+section);

    total=0;
    qtype = new int[section][];
    for(int i=0 ; i<section ; i++){

    qtype[i] = new int[subsection[i]];
    }
    questionlist = new ArrayList();
    answerlist = new ArrayList();
    program_set = new ArrayList();
    id = new ArrayList();
    hints = new ArrayList();
    function = new ArrayList();
    sentence = new ArrayList();
    score = new ArrayList();
    weight = new ArrayList();
    point = new ArrayList();
    explanation = new ArrayList();
  }

  public void addQuestion(Document doc,ArrayList ans){
    String defaultsentence = "���̋󗓂𖄂߂Ȃ����B";//��蕶
    String defaultp_set = "test_set001";//�v���O�����Z�b�g
    String defaultid = "200";//���ID
    String defaulthints = "�����Ƀq���g����͂��܂��B";//�q���g
    String defaultfunc = "60";//��������
    String defaultscore = "10";//�i���ݎg���Ă��Ȃ�)
    String defaultweight = "1";//���x���i�������j
    String defaultpoint = "10";//�i���ݎg���Ă��Ȃ��j
    String defaultexp = "�����ɃR�����g����͂��܂��B";//�R�����g
    
    questionlist.add(doc);
    answerlist.add(ans);
    program_set.add(defaultp_set);
    id.add(defaultid);
    hints.add(defaulthints);
    function.add(defaultfunc);
    sentence.add(defaultsentence);
    score.add(defaultscore);
    weight.add(defaultweight);
    point.add(defaultpoint);
    explanation.add(defaultexp);
    total++;
  }
}