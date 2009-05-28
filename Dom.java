import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.*;


class Dom{

  DocumentBuilderFactory dbfactory;
  DocumentBuilder builder;
  Document doc;
  Element root;
  Node[] answer;
  ArrayList answerlist;
  Document copydoc;
  //Node hole;
  int counter = 0;
  int holecounter = 0;
  public static final int ALL = 0;
  public static final int SELECT = 1;
  public static final int BOOL = 2;
  public static final int self = 100;
  public static final int rootonly = 101;
  public static final int leftchild = 102;
  public static final int rightchild = 103;
  public static final int conditiononly = 104;
  public static final int statementonly = 105;

  public static final int exist = 1001;
  public static final int hasbrother = 1002;
  public static final int selectbrother = 1003;
  public static final int haschild = 1004;
  public static final int selectchild = 1005;
  
  Dom(InputStream input){
    answer=new Node[10];
    answerlist = new ArrayList();
    try {
      // ドキュメントビルダーファクトリを生成
      dbfactory = DocumentBuilderFactory.newInstance();
      // ドキュメントビルダーを生成
      builder = dbfactory.newDocumentBuilder();
      // パースを実行してDocumentオブジェクトを取得
      doc = builder.parse(input);
      copydoc = (Document)doc.cloneNode(true);
      // ルート要素を取得（タグ名：message）
      root = doc.getDocumentElement();
      // 最初の子ノード（テキストノード）の値を表示

      //hole = doc.createElement("hole");
      //hole.appendChild(doc.createTextNode("?????"));


    } catch (Exception e) {
      e.printStackTrace();
    }
    createXML();
  }

  void createXML(){
    NodeList nodes;
    //System.out.println("1");
    nodes = doc.getElementsByTagName("TYPEDEF");
    if(nodes.getLength() > 0)
      makeTableTypedef(nodes);
    //System.out.println("2");
    nodes = doc.getElementsByTagName("STRUCT");
    if(nodes.getLength() > 0)
      makeTableStruct(nodes);
    //System.out.println("3");
    nodes = doc.getElementsByTagName("GLOBAL");
    if(nodes.getLength() > 0)
      makeTableGlobalFunc(nodes);
    //System.out.println("4");
    nodes = doc.getElementsByTagName("FUNC");
    if(nodes.getLength() > 0)
      enterFunction(nodes);
    copydoc = (Document)doc.cloneNode(true);
try{
      StringWriter sw = new StringWriter();
      TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer transformer = tfactory.newTransformer();
      DOMSource dom = new DOMSource(doc);
      StreamResult sr = new StreamResult(sw);
      transformer.transform(dom, sr);
      String xml = sw.toString();
      //System.out.println(xml);
                       // Fileオブジェクトの作成
 	        File file = new File("output2.xml");
 	         // newFile.txtを新規作成する
 	        file.createNewFile();
 	         // Fileオブジェクトを引数にファイル出力ストリームをオープン
 	        FileWriter fw = new FileWriter(file);
 	         fw.write(xml);
 	         fw.close();
    }catch(Exception e){
      //e.printStackTrace();
      System.out.println(e);
    }
  }

  void makeTableTypedef(NodeList list){
    NodeList nodes;
    for(int i=0 ; i<list.getLength() ; i++){
      nodes = ((Element)getChildNode(list.item(i),"DECLARATOR")).getElementsByTagName("VARIABLE");
      for(int j=0 ; j<nodes.getLength() ; j++){
        counter++;
        //Main.symbolT.defineType();
      }
    }
  }
  
  void makeTableStruct(NodeList list){
    String structname;
    Element title;
    NodeList nodes;
    NodeList nodelist;
    for(int i=0 ; i<list.getLength() ; i++){
      structname = getChildNode(list.item(i),"NAME").getFirstChild().getNodeValue();
      if(null == structname)
        structname = list.item(i).getFirstChild().getFirstChild().getNodeValue();
      if(null == Main.symbolT.searchStructname(structname)){
        counter++;
        Main.symbolT.makeStruct(structname,"$"+counter);
        title = doc.createElement("STRUCTNAME");
        title.setAttribute("id","$"+counter);
      }else{
        title = doc.createElement("STRUCTNAME");
        title.setAttribute("id",Main.symbolT.searchStructname(structname));
      }
      title.appendChild(doc.createTextNode(structname));

      list.item(i).replaceChild(title,list.item(i).getFirstChild());
      nodelist=((Element)list.item(i)).getElementsByTagName("MEMBER");
      for(int c=0 ; c<nodelist.getLength() ; c++){
        nodes = ((Element)nodelist.item(c)).getElementsByTagName("VARIABLE");
        for(int j=0 ; j<nodes.getLength() ; j++){
          counter++;
          title = doc.createElement("STMEMBER");
          title.setAttribute("id","$"+counter);
          if(((Element)nodelist.item(c)).getElementsByTagName("STRUCT").getLength()>0){
            Main.symbolT.declareMember(structname,nodes.item(0).getFirstChild().getNodeValue(),"$"+counter,((Element)nodelist.item(c)).getElementsByTagName("STRUCT").item(0).getFirstChild().getNodeValue());
          }else{
            Main.symbolT.declareMember(structname,nodes.item(0).getFirstChild().getNodeValue(),"$"+counter);
          }
          title.appendChild(doc.createTextNode(nodes.item(0).getFirstChild().getNodeValue()));
          ((nodes.item(0)).getParentNode()).replaceChild(title,nodes.item(0));
        }
      }
    }
  }

  void makeTableGlobalFunc(NodeList list){
    NodeList nodes;
    NodeList nlist;
    Element title;
    String name;
    for(int i=0 ; i<list.getLength() ; i++){
      if(((Element)list.item(i)).getElementsByTagName("VARIABLE").getLength()>0){
        nodes = ((Element)list.item(i)).getElementsByTagName("DECLARATOR");
        for(int j=0 ; j<nodes.getLength() ; j++){
          if(null != getChildNode(nodes.item(j),"ARGUMENTS")){  //プロトタイプ宣言
            counter++;
            name = nodes.item(j).getFirstChild().getFirstChild().getNodeValue();
            title = doc.createElement("FNAME");
            title.setAttribute("id","$"+counter);
            Main.symbolT.declareFunction(name,"$"+counter);
            title.appendChild(doc.createTextNode(name));
            nodes.item(j).replaceChild(title,nodes.item(j).getFirstChild());
            nlist= ((Element)nodes.item(j)).getElementsByTagName("VARIABLE");
            for(int k=0 ; k<nlist.getLength() ; k++){
              counter++;
               ((Element)nlist.item(k)).setAttribute("id","$"+counter);  //プロトタイプ宣言の引数はどこからも参照されない(前提)
            }
          }else{  //グローバル変数
            counter++;
            name = nodes.item(j).getFirstChild().getFirstChild().getNodeValue();
            ((Element)nodes.item(j).getFirstChild()).setAttribute("id","$"+counter); 
            Main.symbolT.declareGlobalVariable(name,"$"+counter);
          }
        }
      }
    }
  }

  void enterFunction(NodeList list){
    NodeList nodes;
    NodeList nlist;
    NodeList noli;
    Node node;
    String name;
    String id;
    Element title;
    for(int i=0 ; i<list.getLength() ; i++){
      Main.symbolT.enterFunction();
      node = getChildNode(list.item(i),"VARIABLE");  //以下関数名
      if(null != node){
        name = node.getFirstChild().getNodeValue();
        id = Main.symbolT.searchFunction(name);
        if(null == id){
          counter++;
          id="$"+counter;
          Main.symbolT.declareFunction(name,id);
        }
        title = doc.createElement("FNAME");
        title.setAttribute("id",id);
        title.appendChild(doc.createTextNode(name));
        list.item(i).replaceChild(title,node);
      }
      nodes = ((Element)list.item(i)).getElementsByTagName("ARGUMENT");  //以下引数
      for(int j=0 ; j<nodes.getLength() ; j++){
        counter++;
        id = "$"+counter;
        node = getChildNode(nodes.item(j),"VARIABLE");
        if(null != node){
          name = node.getFirstChild().getNodeValue();
          nlist = ((Element)nodes.item(j)).getElementsByTagName("STRUCTNAME");
          if(nlist.getLength()>0){
            Main.symbolT.declareParameter(name,id,nlist.item(0).getFirstChild().getNodeValue());
          }else{
            Main.symbolT.declareParameter(name,id);
          }
          ((Element)node).setAttribute("id",id);
        } 
      }
      nodes = ((Element)list.item(i)).getElementsByTagName("BLOCK");  //以下ブロック
      for(int j=0 ; j<nodes.getLength() ; j++){
        Main.symbolT.enterBlock();
        enterBlock(nodes.item(j));
        Main.symbolT.exitBlock();
        }
      Main.symbolT.exitFunction();
    }
  }


  void enterBlock(Node tree){
    NodeList dec = ((Element)tree).getElementsByTagName("DECLARATION");
    Node state = getChildNode(tree,"STATEMENT");
    String name;
    String id;
    NodeList list;
    Element title;
    if(dec.getLength() > 0){  //DECLARATION木
      for(int c=0 ; c<dec.getLength() ; c++){
        list = ((Element)dec.item(c)).getElementsByTagName("VARIABLE");
        for(int i=0 ; i<list.getLength() ; i++){
          counter++;
          id = "$"+counter;
          name = list.item(i).getFirstChild().getNodeValue();
          if(((Element)dec.item(c)).getElementsByTagName("STRUCTNAME").getLength()>0){
            Main.symbolT.declareLocalVariable(name,id,((Element)dec.item(c)).getElementsByTagName("STRUCTNAME").item(0).getFirstChild().getNodeValue());
          }else{
            Main.symbolT.declareLocalVariable(name,id);
          }
          ((Element)list.item(i)).setAttribute("id",id);
        }
      }
    }
    if(null != state){  //STATEMENT木
      list = ((Element)state).getElementsByTagName("VARIABLE");
        for(int i=0 ; i<list.getLength() ; i++){
          name = list.item(i).getFirstChild().getNodeValue();
          if(null != getChildNode(list.item(i).getParentNode(),"ARGUMENTS")){  //関数名
            id = Main.symbolT.searchFunction(name);
            if(null == id)
              id = "library";
            title = doc.createElement("FNAME");
            title.setAttribute("id",id);
            title.appendChild(doc.createTextNode(name));
            list.item(i).getParentNode().replaceChild(title,list.item(i));
            i--;
          }else{
            
            id = Main.symbolT.searchSymbol(name);
            if(null != id)
              ((Element)list.item(i)).setAttribute("id",id);
          }
        }
      structOperator(((Element)state).getElementsByTagName("DOT"));
      structOperator(((Element)state).getElementsByTagName("ARROW"));
    }
  }

  void structOperator(NodeList list){
    NodeList leaves;
    String id;

    for(int i=0 ; i<list.getLength() ; i++){
      getStruct(list.item(i));
    }
  }


  String getStruct(Node node){
    String left;
    if(node.getNodeName().equals("VARIABLE")){
      left = node.getFirstChild().getNodeValue();
    }else{
      left = getStruct(node.getFirstChild());
      NodeList list = node.getChildNodes();
      String right = null;
      Node rightnode = null;
      for(int i=0 ; i<list.getLength() ; i++){
        if(list.item(i).getNodeName().equals("VARIABLE")){
          rightnode = list.item(i);
          right = rightnode.getFirstChild().getNodeValue();
          }
      }
      left=Main.symbolT.searchStructmember(Main.symbolT.searchStructType(left),right);
      ((Element)rightnode).setAttribute("id",left);
    }
   
    return left;
  }
  
  /*Document createQuestion(){

    //pickupVariable();
    //pickupFor();
    //pickupWhile();
    //pickupIf();a
    formatPrintf();
    //pickupType();*/

    /*以下出力*/
    /*try{
      StringWriter sw = new StringWriter();
      TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer transformer = tfactory.newTransformer();
      DOMSource dom = new DOMSource(doc);
      StreamResult sr = new StreamResult(sw);
      transformer.transform(dom, sr);
      String xml = sw.toString();
      //System.out.println(xml);
                       // Fileオブジェクトの作成
 	        File file = new File("output2.xml");
 	         // newFile.txtを新規作成する
 	        file.createNewFile();
 	         // Fileオブジェクトを引数にファイル出力ストリームをオープン
 	        FileWriter fw = new FileWriter(file);
 	         fw.write(xml);
 	         fw.close();
    }catch(Exception e){
      //e.printStackTrace();
      System.out.println(e);
    }*/
/*
    return doc;
  }*/

  /*void pickupFor(){
    NodeList list = root.getElementsByTagName("FOR");
    NodeList childlist = list.item(0).getChildNodes();
    int i=5;
    holecounter++;
    Node hole = doc.createElement("hole");
    //hole.appendChild(doc.createTextNode("?????"));
    ((Element)hole).setAttribute("id",String.valueOf(holecounter));
    switch(i){
      case 0 :
      case 1 :
      case 2 :
      case 3 :
        answer[holecounter] = list.item(0).getFirstChild().replaceChild(hole,childlist.item(i));
      case 4 :
        //answer = makeHole(list.item(0),0,2);
        break;
      default :
        answer[holecounter] = makeHoleOwn(list.item(0));
    }
    //System.out.println(answer.getNodeName());
  }

  void pickupParenthesis(Node tree,int number){  //渡したtreeの最初の子要素にPARENTHESISがあること前提で使うこと
    NodeList nl = tree.getChildNodes();
    answer[holecounter] = makeHole(nl.item(0),number);
    
  }
  void pickupParenthesis(Node tree,int startChild,int endChild){  //上に同じ
    NodeList nl = tree.getChildNodes();
    answer[holecounter] = makeHole(nl.item(0),startChild,endChild);
    
  }

  void pickupBlock(Node tree){
  }

  void pickupDeclaration(Node tree){  //treeは子要素にDECLARATIONを持つことが前提

  }

  void pickupStatement(Node tree,int number){  //treeは子要素にSTATEMENTを持つことが前提
    tree = getChildNode(tree,"STATEMENT");
    answer[holecounter] = makeHole(tree,number);
  }
  void pickupStatement(Node tree,int startChild,int endChild){  //treeは子要素にSTATEMENTを持つことが前提
    tree = getChildNode(tree,"STATEMENT");
    answer[holecounter] = makeHole(tree,startChild,endChild);
  }

  void pickupIf(){
    NodeList list = root.getElementsByTagName("IF");
    NodeList childlist = list.item(0).getChildNodes();
    int i=1;
    holecounter++;
    Node hole = doc.createElement("hole");
    //hole.appendChild(doc.createTextNode("?????"));
    ((Element)hole).setAttribute("id",String.valueOf(holecounter));
    switch(i){
      case 0 :
      case 1 :
        answer[holecounter] = list.item(0).replaceChild(hole,childlist.item(i));
      break;
      default :
        answer[holecounter] = list.item(0).getParentNode().replaceChild(hole,list.item(0));
    }
    //System.out.println(answer.getNodeName());
  }

  void pickupWhile(){
    NodeList list = root.getElementsByTagName("WHILE");
    //NodeList childlist = list.item(0).getChildNodes();
    int i=0;
    switch(i){
      case 0:
        pickupParenthesis(list.item(0),i);
        break;
      case 1:
        //pickupBlock(list.item(0));
        break;
      default:
        answer[holecounter] = makeHoleOwn(list.item(0));
    } 
  }


  void pickupSwitch(){
  }
  
  void pickupVariable(){
      NodeList list = root.getElementsByTagName("VARIABLE");
      for (int i=0; i < list.getLength() ; i++) {
        Element element = (Element)list.item(i);
        String id = element.getAttribute("id");
        String variable = element.getFirstChild().getNodeValue();

        System.out.println("ID：" + id + "  " + "変数：" + variable);
      }
  }

  void formatPrintf(){
    NodeList list = root.getElementsByTagName("FORMAT");
    for(int i=0 ; i < list.getLength() ; i++){
      answer[holecounter] = makeHole(list.item(i),0);
    }
    Make.QM.format++;
    addQuestion();

  }

  void pickupType(){
    NodeList list = root.getElementsByTagName("TYPE");
    for(int i=0 ; i < list.getLength() ; i++){
      answer[holecounter] = makeHole(list.item(i),0);
    }
  }*/

  boolean createblank(String nodename,int appointment){
    NodeList list = root.getElementsByTagName(nodename);
    if(list.getLength() !=0){

      if(appointment == ALL){
        for(int i=0 ; i < list.getLength() ; i++){
          //answer[holecounter] = makeHole(list.item(i),0);
          answerlist.add(makeHole(list.item(i),0,list.item(i).getChildNodes().getLength()-1));
          //System.out.println(doc);

        }
      }
      /*if(appointment == SELF){
        //System.out.println(list.getLength());
        for(int i=0 ; i<list.getLength() ; i++){
          //answer[holecounter] = makeHoleOwn(list.item(i));
          answerlist.add(makeHoleOwn(list.item(i)));
        }
      }*/
      //addQuestion();
      //return true;
    }
    /*try{
      StringWriter sw = new StringWriter();
      TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer transformer = tfactory.newTransformer();
      DOMSource dom = new DOMSource(doc);
      StreamResult sr = new StreamResult(sw);
      transformer.transform(dom, sr);
      String xml = sw.toString();
      //System.out.println(xml);
                       // Fileオブジェクトの作成
 	        File file = new File("output2.xml");
 	         // newFile.txtを新規作成する
 	        file.createNewFile();
 	         // Fileオブジェクトを引数にファイル出力ストリームをオープン
 	        FileWriter fw = new FileWriter(file);
 	         fw.write(xml);
 	         fw.close();
    }catch(Exception e){
      //e.printStackTrace();
      System.out.println(e);
    }*/
    if(holecounter == 0)
      return false;
    addQuestion();
    return true;    /*try{
      StringWriter sw = new StringWriter();
      TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer transformer = tfactory.newTransformer();
      DOMSource dom = new DOMSource(doc);
      StreamResult sr = new StreamResult(sw);
      transformer.transform(dom, sr);
      String xml = sw.toString();
      //System.out.println(xml);
                       // Fileオブジェクトの作成
 	        File file = new File("output2.xml");
 	         // newFile.txtを新規作成する
 	        file.createNewFile();
 	         // Fileオブジェクトを引数にファイル出力ストリームをオープン
 	        FileWriter fw = new FileWriter(file);
 	         fw.write(xml);
 	         fw.close();
    }catch(Exception e){
      //e.printStackTrace();
      System.out.println(e);
    }*/
  }

  boolean createblank(String nodename,int appointment,int contains){
    NodeList list = root.getElementsByTagName(nodename);
    //if(list.getLength() != 0){
      if(appointment == SELECT || appointment == BOOL){
        if(contains == rootonly){
          int listlength = list.getLength();
          for(int i=0 ; i<listlength ; i++){
            //System.out.println("i"+list.getLength());
            //answer[holecounter] = makeHoleSelf(list.item(i));
            answerlist.add(makeHoleSelf(list.item(0)));
          }
          /*try{
      StringWriter sw = new StringWriter();
      TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer transformer = tfactory.newTransformer();
      DOMSource dom = new DOMSource(root);
      StreamResult sr = new StreamResult(sw);
      transformer.transform(dom, sr);
      String xml = sw.toString();
      //System.out.println(xml);
                       // Fileオブジェクトの作成
 	        File file = new File("output3.xml");
 	         // newFile.txtを新規作成する
 	        file.createNewFile();
 	         // Fileオブジェクトを引数にファイル出力ストリームをオープン
 	        FileWriter fw = new FileWriter(file);
 	         fw.write(xml);
 	         fw.close();
    }catch(Exception e){
      //e.printStackTrace();
      System.out.println(e);
    }*/
        }
        if(contains == self){
        //System.out.println("?");
        //System.out.println(list.getLength());
          for(int i=0 ; i<list.getLength() ; i++){
          //answer[holecounter] = makeHoleOwn(list.item(i));
            answerlist.add(makeHoleOwn(list.item(i)));
          }
          if(root.getElementsByTagName("FNAME").getLength()!=0){
            list = root.getElementsByTagName("FNAME");
            for(int i=0 ; i<list.getLength() ; i++){
              if(list.item(i).getFirstChild().getNodeValue().equals(nodename)){
                answerlist.add(makeHole(list.item(i),0));
              }
            }
          }
          
        }
        if(contains == leftchild){
          for(int i=0 ; i<list.getLength() ; i++){
            answerlist.add(makeHole(list.item(i),0));
          }
        }
        if(contains == rightchild){
          for(int i=0 ; i<list.getLength() ; i++){
            answerlist.add(makeHole(list.item(i),1));
          }
        }
        if(contains == conditiononly){
          for(int i=0 ; i<list.getLength() ; i++){
            NodeList nl = list.item(i).getChildNodes();
            Node find;
            for(int j=0 ; j<nl.getLength() ; j++){
              find = nl.item(j);
              //System.out.println(find.getNodeName());
              if(find.getNodeName().equals("PARENTHESIS"))
                answerlist.add(makeHole(find));
            }
            //return null;
          }
        }
        if(contains == statementonly){
          for(int i=0 ; i<list.getLength() ; i++){
            NodeList nl = list.item(i).getChildNodes();
            Node find;
            //System.out.println("getLength:"+nl.getLength());
            for(int j=0 ; j<nl.getLength() ; j++){
              find = nl.item(j);
              //System.out.println("j:"+j);
              //System.out.println(find.getNodeName());
              if(find.getNodeName().equals("PARENTHESIS") || find.getNodeName().equals("ELSE"));
              else{
                //System.out.println("answer");
                answerlist.add(makeHole(list.item(i),j));
                //System.out.println("answerout");
              }
            }
            //return null;
          }
        }
      }

  if(holecounter != 0){
      addQuestion();
      return true;
    }
    //}
    return false;
  }

  boolean createblank(String nodename,int appointment,boolean condition,int contains){
    if(condition)
      return createblank(nodename,appointment,contains);
    else
      return false;
  }

  boolean createblank(Element rootnode,String nodename,int appointment,boolean condition,int contains){
    root=rootnode;
    //System.out.println(root.getNodeName());
    System.out.println("condition"+condition);
    if(condition){
      boolean bool = createblank(nodename,appointment,contains);
      root = doc.getDocumentElement();
      return bool;
    }else{
      root = doc.getDocumentElement();
      return false;
    }
  }

  boolean createblank(ArrayList rootnode,String nodename,int appointment,boolean condition,int contains){
    boolean bool = false;
    int truecount=0;
    for(int i=0 ; i<rootnode.size() ; i++){
      bool=createblank((Element)rootnode.get(i),nodename,appointment,condition,contains);
      //if(true == bool)
        //truecount++;
      System.out.println(bool);
    }
    System.out.println(truecount);
    if(truecount>0)
      return true;
    else
      return false;
    //return bool;
  }

  ArrayList getElement(String nodename){
    ArrayList returnlist = new ArrayList();
    NodeList list = root.getElementsByTagName(nodename);
    for(int i=0 ; i<list.getLength(); i++)
      returnlist.add((Element)list.item(i));
    return returnlist;
  }

  boolean condition(int cond,String nodename){
    if(cond == exist){
      //System.out.println(nodename+":"+(root.getChildNode));
      if(root.getElementsByTagName(nodename).getLength()>0)
        return true;
      else
        return false;
    }
    return false;
  }
  
  Node getChildNode(Node tree,String str){  //treeから名前がstrのノードを探して、返す(１個)
    NodeList nl = tree.getChildNodes();
    Node find;
    for(int i=0 ; i<nl.getLength() ; i++){
      find = nl.item(i);
      //System.out.println(find.getNodeName());
      if(find.getNodeName().equals(str))
        return find;
    }
    return null;
  }

  Node makeHole(Node tree){  //子要素全部穴にし、入れ替えた木を返す(根がNODEの木)
    Node hole = doc.createElement("hole");
    //hole.appendChild(doc.createTextNode("?????"));
    ((Element)hole).setAttribute("id",String.valueOf((char)('A'+holecounter)));
    Node nodes = doc.createElement("NODE");
    NodeList children = tree.getChildNodes();
    tree.insertBefore(hole,tree.getChildNodes().item(0));
    for(int i=0 ; i<children.getLength() ; i++){
      nodes.appendChild(tree.removeChild(tree.getChildNodes().item(1)));
    }
    holecounter++;
    return nodes;
  }
  
  Node makeHole(Node tree,int number){  //treeのnumber番目の子要素を穴にし、穴と入れ替えた木を返す
    Node hole = doc.createElement("hole");
    //hole.appendChild(doc.createTextNode("?????"));
    ((Element)hole).setAttribute("id",String.valueOf((char)('A'+holecounter)));
    holecounter++;
    //System.out.println(tree.getChildNodes().getLength());
    return tree.replaceChild(hole,tree.getChildNodes().item(number));
  }
  
  Node makeHole(Node tree,int firstChild,int lastChild){  //treeのfirstChild番目からlastChild番目までの子要素を穴にし、入れ替えた木を返す(根がNODEの木となっている)
    Node hole = doc.createElement("hole");
    //hole.appendChild(doc.createTextNode("?????"));
    ((Element)hole).setAttribute("id",String.valueOf((char)('A'+holecounter)));
    Node nodes = doc.createElement("NODE");
    tree.insertBefore(hole,tree.getChildNodes().item(firstChild));
    for(int i=0 ; i<=lastChild-firstChild ; i++){
      nodes.appendChild(tree.removeChild(tree.getChildNodes().item(firstChild+1)));
    }
    holecounter++;
    return nodes;
  }
  Node makeHoleOwn(Node tree){  //tree全部を穴にする。treeが返る。
    Node hole = doc.createElement("hole");
    //hole.appendChild(doc.createTextNode("?????"));
    ((Element)hole).setAttribute("id",String.valueOf((char)('A'+holecounter)));
    holecounter++;
    return tree.getParentNode().replaceChild(hole,tree);
  }
  Node makeHoleSelf(Node tree){  //いわゆるrootonly
    Node hole = doc.createElement("hole");
    NodeList list = tree.getChildNodes();
    //System.out.println(list.getLength());
    int number =list.getLength();
    for(int i=0 ; i<number; i++){
      //System.out.println(i);
      hole.appendChild(list.item(0));
    }
    String original = tree.getNodeName();
    System.out.println("original:"+original);
    ((Element)hole).setAttribute("id",String.valueOf((char)('A'+holecounter)));
    ((Element)hole).setAttribute("original",original);
    tree.getParentNode().replaceChild(hole,tree);
    holecounter++;
    return (Node)doc.createElement(original);
  }

  void addQuestion(){
    Make.QM.addQuestion((Document)doc.cloneNode(true),(ArrayList)answerlist.clone());
    /*try{
      StringWriter sw = new StringWriter();
      TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer transformer = tfactory.newTransformer();
      DOMSource dom = new DOMSource(root);
      StreamResult sr = new StreamResult(sw);
      transformer.transform(dom, sr);
      String xml = sw.toString();
      //System.out.println(xml);
                       // Fileオブジェクトの作成
 	        File file = new File("output3.xml");
 	         // newFile.txtを新規作成する
 	        file.createNewFile();
 	         // Fileオブジェクトを引数にファイル出力ストリームをオープン
 	        FileWriter fw = new FileWriter(file);
 	         fw.write(xml);
 	         fw.close();
    }catch(Exception e){
      //e.printStackTrace();
      System.out.println(e);
    }*/
    doc = (Document)copydoc.cloneNode(true);
    root = doc.getDocumentElement();
    answer=new Node[10];
    answerlist=new ArrayList();
    holecounter=0;

  }
}