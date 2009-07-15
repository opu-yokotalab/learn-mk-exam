import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import java.io.*;

/*
  xslt�̂��߂̃N���X

*/
public class XSLT {

  public static StringWriter convertToSource(Document doc){
    DOMSource dom = new DOMSource(doc);
    try {
      
      // TransformerFactory�C���X�^���X���擾
      TransformerFactory factory = TransformerFactory.newInstance();
      // XSL�t�@�C������tranceformer���擾
      Transformer transformer1 = 
        factory.newTransformer(new StreamSource("convert.xsl"));
      //Transformer transformer2 = 
      // �o�͂���G���R�[�f�B���O��ݒ�
      transformer1.setOutputProperty("encoding","Shift_JIS");
      // XML�t�@�C����XSLT�ŕϊ����ďo��
      StringWriter write = new StringWriter();
      transformer1.transform(dom,new StreamResult(write));
      //System.out.println(dom);//�`�F�b�N�o��
      return Indent.convert(write);
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
          return Indent.convert(write);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}