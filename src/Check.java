import java.util.*;
import java.io.*;

/*
  Parser���f���o����AST��XML������N���X
  ���łɃ^�O���̒u����A�\���؂̐��`�������B
  '+'�Ƃ�'.'�Ƃ���LVALUE,RVALUE�����̂͂����B
*/

class Check{

  StringTokenizer ST;
  private int nest=0;
  StringBuffer sb= new StringBuffer();
  int notVariable = 0;

  
  Check(String str){
    ST = new StringTokenizer(str,"(,), ",true);  //�g�[�N����؂蕶���́@( �� ) ��  (���X�y�[�X)
  }

  InputStream toXML(){
    sb=sb.append("<?xml version=\"1.0\" encoding=\"shift_JIS\" ?>\n");
    convert(ST.nextToken());
    
    try{  //�t�@�C����output.xml�ŏo��(�f�o�b�O�p)
      // File�I�u�W�F�N�g�̍쐬
      File file = new File("output.xml");
      // newFile.txt��V�K�쐬����
      file.createNewFile();
      // File�I�u�W�F�N�g�������Ƀt�@�C���o�̓X�g���[�����I�[�v��
      FileWriter fw = new FileWriter(file);
      fw.write(sb.toString());
      fw.close();
    }catch(IOException ex){
      System.out.println("IO�G���[���������܂���");
    }
    
    return new ByteArrayInputStream(sb.toString().getBytes());
  }




  void rootconvert2(String token){
    if(token.equals(")")){  //�q�̏I���
      nest--;
      return ;
    }else if(token.equals(" "))
      return;
    else if(token.equals("(")){
      convert(token);
    }else{  //�t�̗v�f�ɊY��
      if(token.startsWith("\"")){  //�Z�~�R�������܂�(������v�f)�ꍇ�A���ʂȏ�����
        modeString(token);
      }else{
        if(notVariable == 0){  //CONSTANT,TYPE�ȊO
        if(token.equals("NONE")){
          sb = sb.append("<"+token+" />");
        }else if(token.equals("*")){
          sb = sb.append("<POINTER />");
        }else if(token.equals("?")){
          sb = sb.append("<QUESTION />");
        }else if(token.equals(":")){
          sb = sb.append("<CORON />");
        }else{
          sb = sb.append("<VARIABLE>"+token+"</VARIABLE>");
        }
        }else{
         sb=sb.append(token);
        }
        if(nest==0)
          return;
        rootconvert2(ST.nextToken());
      }

    }
  }
  
  void rootconvert(String token){
    int nest2=nest;
    StringBuffer subsb = sb;
    sb = new StringBuffer();
    String past = new String();
    String current = new String();
    String left = new String();
    String right = null;
    int counter=0;
    while(!token.equals(")")){
      nest=0;
      do{
        token=ST.nextToken();
      }while(token.equals(" "));
      rootconvert2(token);
      nest=nest2;
      current=sb.toString();
      if(counter==0){
        left = current.trim();
        past = current.trim();
      }
      if(counter>0){
        if((past.startsWith("<VARIABLE>") || past.startsWith("<CONSTANT>") || past.startsWith("<PARENTHESIS>")) && (current.startsWith("<ARRAY>") || current.startsWith("<ARGUMENTS>"))){
          left = left.concat(current);
        }else{
          if(null == right){
            right = left.trim();
            left = new String();
            left = current.trim();
          }
          past = current.trim();
        }
      }
      sb = new StringBuffer();
      counter++;
    }
    sb = subsb;
    sb = sb.append("<LVALUE>"+right+"</LVALUE><RVALUE>"+left+"</RVALUE>");

  }

  void convert(String token){//���C���ƂȂ郁�\�b�h
    if(!(ST.hasMoreTokens())){  //�悪�Ȃ���
      return ;
    }
    if(token.equals(")")){  //�q�̏I���
      nest--;
      return ;
    }else if(token.equals(" ")){  //�X�y�[�X�͖���
      convert(ST.nextToken());
    }else if(token.equals("(")){  //���̃g�[�N���͎q������
      nest++;
      parent(ST.nextToken());  //�e
      if(ST.hasMoreTokens()){
        if(nest!=0){  //���̃N���X�̃C���X�^���X�̌��݂̐[����0�łȂ��Ȃ�A���X�g�ɂ͑���������B(�͂�)
          convert(ST.nextToken());
        }
      }
    }else{  //�t�̗v�f�ɊY��
      if(token.startsWith("\"")){  //�Z�~�R�������܂�(������v�f)�ꍇ�A���ʂȏ�����
        modeString(token);
      }else
        leaf(token);
    }
  }

  void parent(String token){
    if(token.equals("TYPE") || token.equals("CONSTANT"))
      judgeVariable(token);
    else
      addTag(token);
    return ;

  }

  void leaf(String token){
    if(notVariable == 0){  //CONSTANT,TYPE�ȊO
      if(token.equals("BREAK")){
        sb = sb.append("<"+token+" />");
      }else if(token.equals("NONE")){
        sb = sb.append("<"+token+" />");
      }else if(token.equals("typedef")){
        sb = sb.append("<TYPEDEF />");
      }else if(token.equals("*")){
        sb = sb.append("<POINTER />");
      }else if(token.equals("return")){
        sb = sb.append("<RETURN />");
      }else if(token.equals("?")){
        sb = sb.append("<QUESTION />");
      }else if(token.equals(":")){
        sb = sb.append("<CORON />");
      }else{
        sb = sb.append("<VARIABLE>"+token+"</VARIABLE>");
      }
    }else{
      sb=sb.append(token);
    }
    convert(ST.nextToken());
  }

  void addTag(String token){
    token=escape(token);
    //System.out.println(""+token+":"+nest);
    sb=sb.append("<"+token+">");
    if(token.equals("PLUS") || token.equals("MINUS") || token.equals("MULTI") || token.equals("REST") || token.equals("DIV") || token.equals("PIPE") || token.equals("EQUAL") || token.equals("SHIFTLEFT") || token.equals("FORE") || token.equals("FEWER") || token.equals("SHIFTRIGHT") || token.equals("BORE") || token.equals("GREATER") || token.equals("AND") || token.equals("SAND") || token.equals("MT") || token.equals("PIPE") || token.equals("SPIPE") || token.equals("NOTE")|| token.equals("DOT")|| token.equals("ARROW")){
      rootconvert(ST.nextToken());
      nest-=1;
    }
    else{
      convert(ST.nextToken());
    }
    sb=sb.append("</"+token+">");
  }
  
  String escape(String token){  //�^�O�̕s���ȕ�����u��
    if(token.indexOf("+")!=-1){
      if(token.equals("++"))
        token="POSTFIXPLUS";
      if(token.equals("+="))
         token="PLUSE";
      if(token.equals("+"))
         token="PLUS";
    }
    if(token.indexOf("-")!=-1){
      token = token.replaceAll("--","POSTFIXMINUS");
      token = token.replaceAll("-=","MINUSE");
      token = token.replaceAll("->","ARROW");
      token = token.replaceAll("-","MINUS");
    }
    if(token.indexOf("*")!=-1){
      if(token.equals("*="))
         token="MULTIE";
      if(token.equals("*"))
         token="MULTI";
    }
    if(token.indexOf("%")!=-1){
      token = token.replaceAll("%=","RESTE");
      token = token.replaceAll("%","REST");
    }
    if(token.indexOf("/")!=-1){
      token = token.replaceAll("/=","DIVE");
      token = token.replaceAll("/","DIV");
    }

    if(token.indexOf("<")!=-1){
      token = token.replaceAll("<<=","SHIFTLEFTE");
      token = token.replaceAll("<<","SHIFTLEFT");
      token = token.replaceAll("<=","FORE");
      token = token.replaceAll("<","FEWER");
    }
    if(token.indexOf(">")!=-1){
      token = token.replaceAll(">>=","SHIFTRIGHTE");
      token = token.replaceAll(">>","SHIFTRIGHT");
      token = token.replaceAll(">=","BORE");
      token = token.replaceAll(">","GREATER");
    }
    if(token.indexOf("&")!=-1){
      token = token.replaceAll("&=","ANDE");
      token = token.replaceAll("&&","AND");
      token = token.replaceAll("&","SAND");
    }
    if(token.indexOf("^")!=-1){
      token = token.replaceAll("^=","MTE");
      token = token.replaceAll("^","MT");
    }
    if(token.indexOf("|")!=-1){
      token = token.replaceAll("|=","PIPEE");
      token = token.replaceAll("||","PIPE");
      token = token.replaceAll("|","SPIPE");
    }
    if(token.indexOf(".")!=-1){
      token = token.replaceAll(".","DOT");
    }
    if(token.indexOf("?")!=-1){
      token = token.replaceAll("[?]","QUESTION");
    }
    if(token.indexOf(":")!=-1){
      token = token.replaceAll(":","CORON");
    }
    if(token.indexOf("~")!=-1){
      token = token.replaceAll("~","TIRUDA");
    }
    if(token.indexOf("!")!=-1){
      token = token.replaceAll("!=","NOTE");
      token = token.replaceAll("!","NOT");
    }
    if(token.indexOf("=")!=-1){
      if(token.equals("=="))
         token="EQUAL";
    }
    return token;
  }

  void modeString(String token){  //�t�H�[�}�b�g�w��q�̐؂�o��
    String operator = null;
    if(!token.endsWith("\"")){
      token=token.concat(ST.nextToken("\"")+"\"");
      ST.nextToken();  //���]�v��\"�����p
    }
    sb = sb.append("<STRING>");
    StringTokenizer format =new StringTokenizer(token," ,%,\\,\"",true);
    while(format.hasMoreTokens()){
      if((token=format.nextToken()).equals("%")){
        operator=token+format.nextToken();
        if(!operator.matches("%[0-9a-zA-Z[+][-][.]]+")){
          String[] str1 = operator.split("%[0-9a-zA-Z[+][-][.]]+");
          String[] str2 = operator.split(str1[1]);
          operator = str2[0];
          sb=sb.append("<FORMAT>"+operator+"</FORMAT>"+str1[1]);
        }else{
          sb=sb.append("<FORMAT>"+operator+"</FORMAT>");
        }
      }else
        sb=sb.append(token);
    }
      
    sb = sb.append("</STRING>");
    
    convert(ST.nextToken("(,), "));
  }

  void judgeVariable(String token){  //�ϐ����ۂ�
    notVariable = 1 ;
    addTag(token);
    notVariable = 0;
  }

}