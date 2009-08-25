import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.*;

/*
  Dom���g������������N���X
  
 �E�V���{���e�[�u���ɂ��id�̐U�蕪��
 �E���̍쐬

  SymbolTable�N���X�ƈ�@�(����)

  �قƂ�Ǌ���(PARENTHESIS)�̑��݂�z�肵�Ă��Ȃ��Ƃ��낪��肩��
*/

class Dom{
  
  SymbolTable symbolT = new SymbolTable();
  
  DocumentBuilderFactory dbfactory;
  DocumentBuilder builder;
  Document doc;
  Element root;
  Node[] answer;
  ArrayList answerlist;
  Document copydoc;
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
      // �h�L�������g�r���_�[�t�@�N�g���𐶐�
      dbfactory = DocumentBuilderFactory.newInstance();
      // �h�L�������g�r���_�[�𐶐�
      builder = dbfactory.newDocumentBuilder();
      // �p�[�X�����s����Document�I�u�W�F�N�g���擾
      doc = builder.parse(input);
      copydoc = (Document)doc.cloneNode(true);
      // ���[�g�v�f���擾�i�^�O���Fmessage�j
      root = doc.getDocumentElement();
      // �ŏ��̎q�m�[�h�i�e�L�X�g�m�[�h�j�̒l��\��

    } catch (Exception e) {
      e.printStackTrace();
    }
    createXML();
  }

  void createXML(){
    NodeList nodes;
    //nodes = doc.getElementsByTagName("TYPEDEF");  //typedef������
    //if(nodes.getLength() > 0)
    //  makeTableTypedef(nodes);
    nodes = doc.getElementsByTagName("STRUCT");
    if(nodes.getLength() > 0)
      makeTableStruct(nodes);
    nodes = doc.getElementsByTagName("GLOBAL");
    if(nodes.getLength() > 0)
      makeTableGlobalFunc(nodes);
    nodes = doc.getElementsByTagName("FUNC");
    if(nodes.getLength() > 0)
      enterFunction(nodes);
    copydoc = (Document)doc.cloneNode(true);
    /*try{  //�t�@�C���o��(�f�o�b�O�p)
      StringWriter sw = new StringWriter();
      TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer transformer = tfactory.newTransformer();
      DOMSource dom = new DOMSource(doc);
      StreamResult sr = new StreamResult(sw);
      transformer.transform(dom, sr);
      String xml = sw.toString();
                       // File�I�u�W�F�N�g�̍쐬
 	        File file = new File("output2.xml");
 	         // newFile.txt��V�K�쐬����
 	        file.createNewFile();
 	         // File�I�u�W�F�N�g�������Ƀt�@�C���o�̓X�g���[�����I�[�v��
 	        FileWriter fw = new FileWriter(file);
 	         fw.write(xml);
 	         fw.close();
    }catch(Exception e){
      //e.printStackTrace();
      System.out.println(e);
    }*/

  }

  void makeTableTypedef(NodeList list){
    NodeList nodes;
    for(int i=0 ; i<list.getLength() ; i++){
      nodes = ((Element)getChildNode(list.item(i),"DECLARATOR")).getElementsByTagName("VARIABLE");
      for(int j=0 ; j<nodes.getLength() ; j++){
        counter++;
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
      if(null == symbolT.searchStructname(structname)){
        counter++;
        symbolT.makeStruct(structname,"$"+counter);
        title = doc.createElement("STRUCTNAME");
        title.setAttribute("id","$"+counter);
      }else{
        title = doc.createElement("STRUCTNAME");
        title.setAttribute("id",symbolT.searchStructname(structname));
      }
      title.appendChild(doc.createTextNode(structname));
      (list.item(i)).replaceChild(title,list.item(i).getFirstChild());
      
      nodelist=((Element)list.item(i)).getElementsByTagName("MEMBER");
      for(int c=0 ; c<nodelist.getLength() ; c++){
        nodes = ((Element)nodelist.item(c)).getElementsByTagName("VARIABLE");
        for(int j=0 ; j<nodes.getLength() ; j++){
          counter++;
          title = doc.createElement("STMEMBER");
          title.setAttribute("id","$"+counter);
          if(((Element)nodelist.item(c)).getElementsByTagName("STRUCT").getLength()>0){
            symbolT.declareMember(structname,nodes.item(0).getFirstChild().getNodeValue(),"$"+counter,((Element)nodelist.item(c)).getElementsByTagName("STRUCT").item(0).getFirstChild().getFirstChild().getNodeValue());
          }else{
            symbolT.declareMember(structname,nodes.item(0).getFirstChild().getNodeValue(),"$"+counter);
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
          if(null != getChildNode(nodes.item(j),"ARGUMENTS")){  //�v���g�^�C�v�錾
            counter++;
            name = nodes.item(j).getFirstChild().getFirstChild().getNodeValue();
            title = doc.createElement("FNAME");
            title.setAttribute("id","$"+counter);
            symbolT.declareFunction(name,"$"+counter);
            title.appendChild(doc.createTextNode(name));
            nodes.item(j).replaceChild(title,nodes.item(j).getFirstChild());
            nlist= ((Element)nodes.item(j)).getElementsByTagName("VARIABLE");
            for(int k=0 ; k<nlist.getLength() ; k++){
              counter++;
               ((Element)nlist.item(k)).setAttribute("id","$"+counter);  //�v���g�^�C�v�錾�̈����͂ǂ�������Q�Ƃ���Ȃ�(�O��)
            }
          }else{  //�O���[�o���ϐ�
            counter++;
            name = nodes.item(j).getFirstChild().getFirstChild().getNodeValue();
            ((Element)nodes.item(j).getFirstChild()).setAttribute("id","$"+counter); 
            symbolT.declareGlobalVariable(name,"$"+counter);
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
      symbolT.enterFunction();
      node = getChildNode(list.item(i),"VARIABLE");  //�ȉ��֐���
      if(null != node){
        name = node.getFirstChild().getNodeValue();
        id = symbolT.searchFunction(name);
        if(null == id){
          counter++;
          id="$"+counter;
          symbolT.declareFunction(name,id);
        }
        title = doc.createElement("FNAME");
        title.setAttribute("id",id);
        title.appendChild(doc.createTextNode(name));
        list.item(i).replaceChild(title,node);
      }
      nodes = ((Element)list.item(i)).getElementsByTagName("ARGUMENT");  //�ȉ�����
      for(int j=0 ; j<nodes.getLength() ; j++){
        counter++;
        id = "$"+counter;
        node = getChildNode(nodes.item(j),"VARIABLE");
        if(null != node){
          name = node.getFirstChild().getNodeValue();
          nlist = ((Element)nodes.item(j)).getElementsByTagName("STRUCTNAME");
          if(nlist.getLength()>0){
            symbolT.declareParameter(name,id,nlist.item(0).getFirstChild().getNodeValue());
          }else{
            symbolT.declareParameter(name,id);
          }
          ((Element)node).setAttribute("id",id);
        } 
      }
      nodes = ((Element)list.item(i)).getElementsByTagName("BLOCK");  //�ȉ��u���b�N
      for(int j=0 ; j<nodes.getLength() ; j++){
        symbolT.enterBlock();
        enterBlock(nodes.item(j));
        //symbolT.exitBlock();   //���̍s���폜���Ă����̂��ǂ����������B
      }
      symbolT.exitFunction();
      
    }
    
  }


  void enterBlock(Node tree){
    NodeList dec = ((Element)tree).getElementsByTagName("DECLARATION");
    Node state = getChildNode(tree,"STATEMENT");
    String name;
    String id;
    NodeList list;
    Element title;
    if(dec.getLength() > 0){  //DECLARATION��
      for(int c=0 ; c<dec.getLength() ; c++){
        list = ((Element)dec.item(c)).getElementsByTagName("VARIABLE");
        for(int i=0 ; i<list.getLength() ; i++){
          counter++;
          id = "$"+counter;
          name = list.item(i).getFirstChild().getNodeValue();
          if(((Element)dec.item(c)).getElementsByTagName("STRUCTNAME").getLength()>0){
            symbolT.declareLocalVariable(name,id,((Element)dec.item(c)).getElementsByTagName("STRUCTNAME").item(0).getFirstChild().getNodeValue());
          }else{
            symbolT.declareLocalVariable(name,id);
          }
          ((Element)list.item(i)).setAttribute("id",id);
        }
      }
    }
    if(null != state){  //STATEMENT��
      list = ((Element)state).getElementsByTagName("VARIABLE");
        for(int i=0 ; i<list.getLength() ; i++){
          name = list.item(i).getFirstChild().getNodeValue();
          if(null != getChildNode(list.item(i).getParentNode(),"ARGUMENTS")){  //�֐���
            id = symbolT.searchFunction(name);
            if(null == id)
              id = "library";
            title = doc.createElement("FNAME");
            title.setAttribute("id",id);
            title.appendChild(doc.createTextNode(name));
            list.item(i).getParentNode().replaceChild(title,list.item(i));
            i--;
          }else{
            
            id = symbolT.searchSymbol(name);
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
  
  String getStruct(Node node){  //�h�b�g���Z�q��A���[���Z�q���ӂ̕ϐ���(��)��id�U�蕪��
    String left = null;
    Node rightnode = getChildNode(node.getLastChild(),"VARIABLE");
    String right = rightnode.getFirstChild().getNodeValue();
    if((null != getChildNode(node.getFirstChild(),"DOT")) || (null != getChildNode(node.getFirstChild(),"ARROW"))){
      left = getStruct(node.getFirstChild().getFirstChild());
      left = symbolT.searchStructmember(symbolT.searchStructTypeById(left),right);
    }else if(null != getChildNode(node.getFirstChild(),"VARIABLE")){
      left = getChildNode(node.getFirstChild(),"VARIABLE").getFirstChild().getNodeValue();
      left = symbolT.searchStructmember(symbolT.searchStructType(left),right);
    }
    ((Element)rightnode).setAttribute("id",left);

    return left;
    
  }

/*createblank(����,ALL)�̏���*/
  boolean createblank(String nodename,int appointment){
    NodeList list = root.getElementsByTagName(nodename);
    if(list.getLength() !=0){

      if(appointment == ALL){
        for(int i=0 ; i < list.getLength() ; i++){
          answerlist.add(makeHole(list.item(i),0,list.item(i).getChildNodes().getLength()-1));

        }
      }

    }

    if(holecounter == 0)
      return false;
    addQuestion();
    return true;
  }

  /*crateblank(����,[SELECT|BOOL],[rootonly,self,leftchild,rightchild,conditiononly,statementonly])�̏���*/
  boolean createblank(String nodename,int appointment,int contains){
    NodeList list = root.getElementsByTagName(nodename);
      if(appointment == SELECT || appointment == BOOL){
        if(contains == rootonly){
          int listlength = list.getLength();
          for(int i=0 ; i<listlength ; i++){
            answerlist.add(makeHoleSelf(list.item(0)));
          }
        }
        if(contains == self){
          for(int i=0 ; i<list.getLength() ; i++){
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
              if(find.getNodeName().equals("PARENTHESIS"))
                answerlist.add(makeHole(find));
            }
          }
        }
        if(contains == statementonly){
          for(int i=0 ; i<list.getLength() ; i++){
            NodeList nl = list.item(i).getChildNodes();
            Node find;
            for(int j=0 ; j<nl.getLength() ; j++){
              find = nl.item(j);
              if(find.getNodeName().equals("PARENTHESIS") || find.getNodeName().equals("ELSE"));
              else{
                answerlist.add(makeHole(list.item(i),j));
              }
            }
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

  /**/
  boolean createblank(String rootnode,String nodename,int appointments,int condition){
    ArrayList rootlist = getElement(rootnode);
    boolean torf = false;
    for(int i=0 ; i<rootlist.size() ; i++){
      root=(Element)rootlist.get(i);
      if(condition == exist){
        torf=bool(condition);
      }else if(condition == haschild){
        NodeList nodes = root.getElementsByTagName(nodename);
        for(int j=0 ; j<nodes.getLength() ; j++){
          if(null != nodes.item(j).getFirstChild()){
            torf=true;
            break;
          }
        }
      }
      if(torf){
        createblank(nodename,appointments);
        root = doc.getDocumentElement();
        return true;
      }
    }
    root = doc.getDocumentElement();
    return false;
  }

  boolean createblank(String rootnode,String nodename,int appointments,int condition,String node){
    ArrayList rootlist = getElement(rootnode);
    for(int i=0 ; i<rootlist.size() ; i++){
      root=(Element)rootlist.get(i);
      if(root.getElementsByTagName(nodename).getLength()>0)
        if(bool(condition,node)){
          createblank(nodename,appointments);
        root = doc.getDocumentElement();
        return true;
      }
    }
    root = doc.getDocumentElement();
    return false;
  } 

  boolean createblank(String rootnode,String nodename,int appointments,int contains,int condition){
    ArrayList rootlist = getElement(rootnode);
    for(int i=0 ; i<rootlist.size() ; i++){
      root=(Element)rootlist.get(i);
      if(bool(condition)){
        createblank(nodename,appointments,contains);
        root = doc.getDocumentElement();
        return true;
      }
    }
    root = doc.getDocumentElement();
    return false;
  }

  boolean createblank(String rootnode,String nodename,int appointments,int contains,int condition,String node){
    ArrayList rootlist = getElement(rootnode);
    for(int i=0 ; i<rootlist.size() ; i++){
      root=(Element)rootlist.get(i);
      if(root.getElementsByTagName(nodename).getLength()>0)
        if(bool(condition,node)){
          createblank(nodename,appointments,contains);
        root = doc.getDocumentElement();
        return true;
      }
    }
    root = doc.getDocumentElement();
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
    }
    if(truecount>0)
      return true;
    else
      return false;
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
      if(root.getElementsByTagName(nodename).getLength()>0)
        return true;
      else
        return false;
    }
    return false;
  }


     
     /*nodename�̗v�f�̒��g�������true
      * �֐����iFNAME)���Ǝq�v�f�̒���nodename�ƈ�v������̂�T��
      * ��v������̂������true�@*/
  boolean bool(int cond,String nodename){
    if(cond == exist){
      if(root.getElementsByTagName(nodename).getLength()>0)
        return true;
      if(root.getElementsByTagName("FNAME").getLength()>0){
        for(int i=0 ; i<root.getElementsByTagName("FNAME").getLength(); i++){
          if(root.getElementsByTagName("FNAME").item(i).getFirstChild().getNodeValue().equals(nodename)){
            return true;
          }
        }
    }
      
    }
    if(cond  == selectbrother){
      
    }
    return false;
  }
    
   boolean bool(int cond){
    if(cond == haschild){

        return true;
    }
    return false;
  }

  String convertQTS(String str){
    str.replaceAll("[\"]","[\\\"]");
    return str;
  }
     
  
  Node getChildNode(Node tree,String str){  //tree���疼�O��str�̎q�m�[�h��T���āA�Ԃ�(�P��)
    NodeList nl = tree.getChildNodes();
    Node find;
    for(int i=0 ; i<nl.getLength() ; i++){
      find = nl.item(i);
      if(find.getNodeName().equals(str))
        return find;
    }
    return null;
  }

  Node makeHole(Node tree){  //�q�v�f�S�����ɂ��A����ւ����؂�Ԃ�(����NODE�̖�)
    Node hole = doc.createElement("hole");
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
  
  Node makeHole(Node tree,int number){  //tree��number�Ԗڂ̎q�v�f�����ɂ��A���Ɠ���ւ����؂�Ԃ�
    Node hole = doc.createElement("hole");
    ((Element)hole).setAttribute("id",String.valueOf((char)('A'+holecounter)));
    String original = tree.getChildNodes().item(number).getNodeName();
    ((Element)hole).setAttribute("original",original);
    holecounter++;
    return tree.replaceChild(hole,tree.getChildNodes().item(number));
  }
  
  Node makeHole(Node tree,int firstChild,int lastChild){  //tree��firstChild�Ԗڂ���lastChild�Ԗڂ܂ł̎q�v�f�����ɂ��A����ւ����؂�Ԃ�(����NODE�̖؂ƂȂ��Ă���)
    Node hole = doc.createElement("hole");
    ((Element)hole).setAttribute("id",String.valueOf((char)('A'+holecounter)));
    ((Element)hole).setAttribute("original","complex");
    Node nodes = doc.createElement("NODE");
    tree.insertBefore(hole,tree.getChildNodes().item(firstChild));
    for(int i=0 ; i<=lastChild-firstChild ; i++){
      nodes.appendChild(tree.removeChild(tree.getChildNodes().item(firstChild+1)));
    }
    holecounter++;
    return nodes;
  }
  Node makeHoleOwn(Node tree){  //tree�S�������ɂ���Btree���Ԃ�B
    Node hole = doc.createElement("hole");
    ((Element)hole).setAttribute("id",String.valueOf((char)('A'+holecounter)));
    String original = tree.getNodeName();
    ((Element)hole).setAttribute("original",original);
    holecounter++;
    return tree.getParentNode().replaceChild(hole,tree);
  }
  Node makeHoleSelf(Node tree){  //������rootonly
    Node hole = doc.createElement("hole");
    NodeList list = tree.getChildNodes();
    int number =list.getLength();
    for(int i=0 ; i<number; i++){
      hole.appendChild(list.item(0));
    }
    String original = tree.getNodeName();
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
                       // File�I�u�W�F�N�g�̍쐬
 	        File file = new File("output3.xml");
 	         // newFile.txt��V�K�쐬����
 	        file.createNewFile();
 	         // File�I�u�W�F�N�g�������Ƀt�@�C���o�̓X�g���[�����I�[�v��
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