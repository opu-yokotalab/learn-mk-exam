import java.util.*;
import java.io.*;

/*
  最終的な整形(インデント)をしているクラス。
  粗がすごい。
*/

public class Indent{

   static StringTokenizer ST;
   static StringBuffer SB;

  static int nest = 0;
  
  static StringWriter convert(StringWriter write){
    ST = new StringTokenizer(write.toString(),"<>",true);
    SB = new StringBuffer();
   
    String next;
    while(ST.hasMoreTokens()){
      SB = SB.append(indentC(ST.nextToken()));
    }
    StringWriter SW = new StringWriter();
    SW.write(SB.toString());
    return SW;
  }

  static String indentC(String str){
    if(str.equals("<")){
      String next= ST.nextToken();
      if(next.equals("br/")){
        ST.nextToken();
        String br = "\n";
        for(int i=0 ; i<nest ; i++)
          br = br.concat("  ");
        return br;
      }else if(next.equals("indent")){
        nest++;
        ST.nextToken();
        return "";
      }else if(next.equals("/indent")){
        nest--;
        ST.nextToken();
        return "";
      }else if(next.equals("parenthesis")){
        ST.nextToken();
        return "";
      }else if(next.equals("/parenthesis")){
        ST.nextToken();
        String br = "\n";
        for(int i=0 ; i<nest+1 ; i++)
          br = br.concat("  ");
        return br;
      }else if(next.equals("state")){
        ST.nextToken();
        return "";
      }else if(next.equals("/state")){
        ST.nextToken();
        return "";
      }else if(next.equals("AMP/")){
        ST.nextToken();
        return "&";
      }else if(next.equals("SHIFTLEFTE/")){
        ST.nextToken();
        return "<<=";
      }else if(next.equals("SHIFTLEFT/")){
        ST.nextToken();
        return "<<";
      }else if(next.equals("FORE/")){
        ST.nextToken();
        return "<=";
      }else if(next.equals("FEWER/")){
        ST.nextToken();
        return "<";
      }else if(next.equals("SHIFTRIGHTE/")){
        ST.nextToken();
        return ">>=";
      }else if(next.equals("SHIFTRIGHT/")){
        ST.nextToken();
        return ">>";
      }else if(next.equals("BORE/")){
        ST.nextToken();
        return ">=";
      }else if(next.equals("GREATER/")){
        ST.nextToken();
        return ">";
      }else if(next.equals("ANDE/")){
        ST.nextToken();
        return "&=";
      }else if(next.equals("AND/")){
        ST.nextToken();
        return "&&";
      }else if(next.equals("SAND/")){
        ST.nextToken();
        return "&";
      }else if(next.equals("ARROW/")){
        ST.nextToken();
        return "->";
      }
      else if(next.startsWith("tag")){
        ST.nextToken();
        return "";
      }
      else if(next.startsWith("?xml")){
        ST.nextToken();
        return "";
      }
      else if(next.equals("/tag")){
        ST.nextToken();
        return "";
      }
    }else{
        return str;
    }
    return str;
  }

}
