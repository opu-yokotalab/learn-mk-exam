import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import java.io.*;

/*
  xsltのためのクラス

*/
public class XSLT {

  public static StringWriter convertToSource(Document doc){
    DOMSource dom = new DOMSource(doc);
    try {
      
      // TransformerFactoryインスタンスを取得
      TransformerFactory factory = TransformerFactory.newInstance();
      // XSLファイルからtranceformerを取得
      Transformer transformer1 = 
        factory.newTransformer(new StreamSource("convert.xsl"));
      //Transformer transformer2 = 
      // 出力するエンコーディングを設定
      transformer1.setOutputProperty("encoding","Shift_JIS");
      // XMLファイルをXSLTで変換して出力
      StringWriter write = new StringWriter();
      transformer1.transform(dom,new StreamResult(write));
      //System.out.println(dom);//チェック出力
      return Indent.convert(write);
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
          return Indent.convert(write);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}