import org.w3c.dom.*;
import java.util.*;

/*
  作った問題の各データを保持しているクラス。
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
    String defaultsentence = "次の空欄を埋めなさい。";//問題文
    String defaultp_set = "test_set001";//プログラムセット
    String defaultid = "200";//問題ID
    String defaulthints = "ここにヒントを入力します。";//ヒント
    String defaultfunc = "60";//制限時間
    String defaultscore = "10";//（現在使えていない)
    String defaultweight = "1";//レベル（仮おき）
    String defaultpoint = "10";//（現在使えていない）
    String defaultexp = "ここにコメントを入力します。";//コメント
    
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