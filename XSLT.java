import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import java.io.*;


public class XSLT {
  public static void main( String[] args) {
    try {
      // TransformerFactory�C���X�^���X���擾
      TransformerFactory factory = TransformerFactory.newInstance();
      // XSL�t�@�C������tranceformer���擾
      Transformer transformer = 
        factory.newTransformer(new StreamSource("convert.xsl"));
      // �o�͂���G���R�[�f�B���O��ݒ�
      transformer.setOutputProperty("encoding","Shift_JIS");
      // XML�t�@�C����XSLT�ŕϊ����ďo��
      transformer.transform(new StreamSource("output2.xml"), 
                            new StreamResult("output3.txt"));
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static StringWriter convertToSource(Document doc){
    DOMSource dom = new DOMSource(doc);
    try {
      
      // TransformerFactory�C���X�^���X���擾
      TransformerFactory factory = TransformerFactory.newInstance();
      // XSL�t�@�C������tranceformer���擾
      Transformer transformer1 = 
        factory.newTransformer(new StreamSource("convert.xsl"));
      Transformer transformer2 = 
        factory.newTransformer(new StreamSource("indent.xsl"));
      // �o�͂���G���R�[�f�B���O��ݒ�
      transformer1.setOutputProperty("encoding","Shift_JIS");
      transformer2.setOutputProperty("encoding","Shift_JIS");
      // XML�t�@�C����XSLT�ŕϊ����ďo��
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
      // TransformerFactory�C���X�^���X���擾
      TransformerFactory factory = TransformerFactory.newInstance();
      // XSL�t�@�C������tranceformer���擾
      Transformer transformer = 
        factory.newTransformer(new StreamSource("convert.xsl"));
      // �o�͂���G���R�[�f�B���O��ݒ�
      transformer.setOutputProperty("encoding","Shift_JIS");
      // XML�t�@�C����XSLT�ŕϊ����ďo��
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