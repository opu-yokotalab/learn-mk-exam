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
    String defaultsentence = "次の空欄を埋めなさい。";
    questionlist.add(doc);
    answerlist.add(ans);
    program_set.add(null);
    id.add(null);
    hints.add(null);
    function.add(null);
    sentence.add(defaultsentence);
    score.add(null);
    weight.add(null);
    point.add(null);
    explanation.add(null);
    total++;
  }
}