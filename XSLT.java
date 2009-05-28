import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import java.io.*;


public class XSLT {
  public static void main( String[] args) {
    try {
      // TransformerFactoryインスタンスを取得
      TransformerFactory factory = TransformerFactory.newInstance();
      // XSLファイルからtranceformerを取得
      Transformer transformer = 
        factory.newTransformer(new StreamSource("convert.xsl"));
      // 出力するエンコーディングを設定
      transformer.setOutputProperty("encoding","Shift_JIS");
      // XMLファイルをXSLTで変換して出力
      transformer.transform(new StreamSource("output2.xml"), 
                            new StreamResult("output3.txt"));
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static StringWriter convertToSource(Document doc){
    DOMSource dom = new DOMSource(doc);
    try {
      
      // TransformerFactoryインスタンスを取得
      TransformerFactory factory = TransformerFactory.newInstance();
      // XSLファイルからtranceformerを取得
      Transformer transformer1 = 
        factory.newTransformer(new StreamSource("convert.xsl"));
      Transformer transformer2 = 
        factory.newTransformer(new StreamSource("indent.xsl"));
      // 出力するエンコーディングを設定
      transformer1.setOutputProperty("encoding","Shift_JIS");
      transformer2.setOutputProperty("encoding","Shift_JIS");
      // XMLファイルをXSLTで変換して出力
      //transformer.transform(dom, new StreamResult("output3.txt"));
      StringWriter write = new StringWriter();
      //StringWriter write2 = new StringWriter();
      //Reader read = new StringReader(write.toString());
      transformer1.transform(dom,new StreamResult(write));
      //transformer2.transform(new StreamSource(read) ,new StreamResult(write2));
      //System.out.println(write.toString());
      return write;
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

    public static StringWriter convertToSource(Node node){
    DOMSource dom = new DOMSource(node);
        try {
      // TransformerFactoryインスタンスを取得
      TransformerFactory factory = TransformerFactory.newInstance();
      // XSLファイルからtranceformerを取得
      Transformer transformer = 
        factory.newTransformer(new StreamSource("convert.xsl"));
      // 出力するエンコーディングを設定
      transformer.setOutputProperty("encoding","Shift_JIS");
      // XMLファイルをXSLTで変換して出力
          StringWriter write = new StringWriter();
      transformer.transform(dom, 
                            new StreamResult(write));
          return write;
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}