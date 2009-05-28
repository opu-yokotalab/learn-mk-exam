import org.w3c.dom.*;
import java.util.*;

public class QuestionMap{
  /*int format;
  int printf;
  int scanf;
  int adress;*/
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
    /*format=0;
    printf=0;
    scanf=0;
    adress=0;*/
    total=0;
    qtype = new int[section][];
    for(int i=0 ; i<section ; i++){
      //System.out.println(subsection[i]);
      qtype[i] = new int[subsection[i]];
      //qtypr[i][
      //System.out.println("loop");
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
    questionlist.add(doc);
    answerlist.add(ans);
    program_set.add(null);
    id.add(null);
    hints.add(null);
    function.add(null);
    sentence.add(null);
    score.add(null);
    weight.add(null);
    point.add(null);
    explanation.add(null);
    total++;
  }
}