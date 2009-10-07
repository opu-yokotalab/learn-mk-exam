import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class REF_Trans {/*���K�t�H�[�}�b�g�iRegular Expression Format)�ւ̕ϊ��i���C���j*/

	/*���K�t�H�[�}�b�g�ւ̕ϊ����C��*/
	public static void main (String[] args){
		
		//�ϊ�����xml�����̎w��
		String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_h.xml";
		//String f_name= filename.split("\\")[(filename.split("\\").length)-1];
		String f_name = new String("output_h");
	    try {
	      // �h�L�������g�r���_�[�t�@�N�g���𐶐�
	      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	      // �h�L�������g�r���_�[�𐶐�
	      DocumentBuilder builder = dbfactory.newDocumentBuilder();
	      // �p�[�X�����s����Document�I�u�W�F�N�g���擾
	      Document doc = builder.parse(new File(filename));
	      
	      /*�Ή��e�[�u���쐬*/
	      doc = Make_Table(doc,f_name);
	      
	      /*���K�t�H�[�}�b�g�֕ϊ�*/
	      //doc = Transformation(doc,f_name);
	      
	      //�ŏI�I�Ȑ��K�t�H�[�}�b�g�ɂȂ����\���؂̏o��
	      try{
				DOMSource source= new DOMSource(doc); 
				//==============================//
				//  �ϊ���i�t�@�C���j����      //
				//==============================//
				File f =new File(f_name+"_REF.xml"); 
				FileOutputStream fo = new FileOutputStream(f); 
				StreamResult result = new StreamResult(fo); 
				
				//==============================//
				//	�ϊ�		     //
				//==============================//
				TransformerFactory transFactory = TransformerFactory.newInstance(); 
				Transformer transformer = transFactory.newTransformer(); 
				transformer.transform(source, result); 

				//==============================//
				//	���Ƃ��܂�	     //
				//==============================//
				fo.close(); 
			}catch (Exception e)
			{
				e.printStackTrace();
			}
	      
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	}
	
	/*�ϐ��̑Ή��e�[�u���쐬*/
	public static Document Make_Table(Document doc,String f_name){
	//public static Document Make_Table(document doc){
		/*document��������DOM��p���ĕϐ��̑Ή��e�[�u���̍쐬��
		 * �e�ϐ��m�[�h��ID�t���s���B�Ԃ�l��document */
		
		String[][] v_table = new String[50][4];//�ϐ��̑Ή��e�[�u��
		int table_c = 0;//�Ή��e�[�u���p�̃J�E���^
		int node_c = 0;//�m�[�h�J�E���^
		String type;
		
		// ���[�g�v�f���擾
		Element root = doc.getDocumentElement();
		//�ϐ��̒�`���s���Ă���DECLARATION�m�[�h�̃��X�g�𓾂�
		NodeList list1 = root.getElementsByTagName("DECLARATION");
		for(int i=0;i<list1.getLength();i++){
			Element element1 = (Element)list1.item(i);
			
			//DECLARATION�̎q�m�[�h��TYPE��T���i�ϐ��̌^�j
			NodeList element_list1 = element1.getElementsByTagName("TYPE");
			Element type_elm = (Element)element_list1.item(0);
			type = type_elm.getFirstChild().getNodeValue();
			
			//DECLARATION�̎q�m�[�h�ɂ���VARIABLE��T���i�ϐ����j
			element_list1 = element1.getElementsByTagName("VARIABLE");
			for(int j=0;j<element_list1.getLength();j++){
				
				//�ϐ��̃��X�g��������
				Element elm1 = (Element)element_list1.item(j);
				
				//�ϐ��̑Ή��e�[�u����ID�Ɩ��O���i�[
				v_table[table_c][0] = "$"+table_c;
				v_table[table_c][1] = elm1.getFirstChild().getNodeValue();
				v_table[table_c][2] = type;
				v_table[table_c][3] = "N"+node_c+",";
				
				//ID���ӂ�
				elm1.setAttribute("id",v_table[table_c][0]);
				elm1.setAttribute("n_id","N"+node_c);
				
				//�J�E���^��1�����߂�
				table_c++;
				node_c++;
			}
		}
		//�����̕����ɂ���ϐ���Ή��e�[�u���ɑΉ�������
		
		//�������e���L�q���Ă���STATEMENT�m�[�h�̃��X�g�𓾂�
		NodeList list2 = root.getElementsByTagName("STATEMENT");
		
		for(int k=0;k<list2.getLength();k++){
			
			Element element2 = (Element)list2.item(k);
			//STATEMENT�̎q�m�[�h�ɂ���VARIABLE��T��(�������̕ϐ��ɂ�����j
			NodeList variable_list = element2.getElementsByTagName("VARIABLE");
			
			for(int l=0;l<variable_list.getLength();l++){
				Element elm2 = (Element)variable_list.item(l);
				for(int m=0;m<table_c;m++){

					//�Ή��e�[�u���Ƃ̑Ή��t�����s���i�ϐ�������������Εϐ���ID[id="$X"](�ƃm�[�h��ID[n_id="NX"])���ӂ�)(X�͕ϐ��j
					if(elm2.getFirstChild().getNodeValue().equals(v_table[m][1])){
						elm2.setAttribute("id",v_table[m][0]);
						elm2.setAttribute("n_id","N"+node_c);

						//�Ή��e�[�u���ւ̏�������
						v_table[m][3]=v_table[m][3]+"N"+node_c+",";
						node_c++;
					}
				}
			}
		}
		
		/*//�ϐ��̑Ή����������݁i�f�o�b�N�p�j
		try{
			DOMSource source= new DOMSource(doc); 
			//==============================//
			//  �ϊ���i�t�@�C���j����      //
			//==============================//
			File f =new File(f_name+"_table.xml"); 
			FileOutputStream fo = new FileOutputStream(f); 
			StreamResult result = new StreamResult(fo); 
			
			//==============================//
			//	�ϊ�		     //
			//==============================//
			TransformerFactory transFactory = TransformerFactory.newInstance(); 
			Transformer transformer = transFactory.newTransformer(); 
			transformer.transform(source, result); 

			//==============================//
			//	���Ƃ��܂�	     //
			//==============================//
			fo.close(); 
		}catch (Exception e)
		{
			e.printStackTrace();
		}*/

		//�e�[�u���̕\��
		System.out.println("ID | name | type | node_location");
		for(int a=0;a<table_c;a++){
			System.out.println(v_table[a][0]+" |  "+v_table[a][1]+"   | "+v_table[a][2]+" | "+v_table[a][3]);
		}

		//�Ԃ�l
		return doc;

	}
	
	/*���K�t�H�[�}�b�g�֕ϊ�*/
	public static Document Transformation(Document doc,String f_name){
		
		// ���[�g�v�f���擾
		Element root = doc.getDocumentElement();
		
		//�ϐ��̒�`�̕��ёւ�
		
		//�ϐ��̒�`���s���Ă���DECLARATION�m�[�h�̃��X�g�𓾂�
		NodeList list1 = root.getElementsByTagName("DECLARATION");
		for(int i=0;i<list1.getLength();i++){
			Element element1 = (Element)list1.item(i);
			
			//DECLARATION�̎q�m�[�h��TYPE��T���i�ϐ��̌^�j
			NodeList element_list1 = element1.getElementsByTagName("TYPE");
			Element type_elm = (Element)element_list1.item(0);
			//type = type_elm.getFirstChild().getNodeValue();
			
			//DECLARATION�̎q�m�[�h�ɂ���VARIABLE��T���i�ϐ����j
			element_list1 = element1.getElementsByTagName("VARIABLE");
			for(int j=0;j<element_list1.getLength();j++){
				
				//�ϐ��̃��X�g��������
				Element elm1 = (Element)element_list1.item(j);
				
				//�ϐ��̑Ή��e�[�u����ID�Ɩ��O���i�[
				//v_table[table_c][0] = "$"+table_c;
				//v_table[table_c][1] = elm1.getFirstChild().getNodeValue();
				//v_table[table_c][2] = type;
				//v_table[table_c][3] = "N"+node_c+",";
				
				//ID���ӂ�
				//elm1.setAttribute("id",v_table[table_c][0]);
				//elm1.setAttribute("n_id","N"+node_c);
				
				//�J�E���^��1�����߂�
				//table_c++;
				//node_c++;
			}
		}
		
		
		
		return doc;
		
		/*//xml�̍\���؂�ǂݍ����xslt�Ő��K�t�H�[�}�b�g�֕ϊ����o�� 
		DOMSource dom = new DOMSource(doc);
		//�K������xslt�������̎w��
		String xsltdoc = new String("C:\\Users\\ariyasu\\workspace\\mkexam2\\src\\trans.xsl");
		//String xsltdoc = new String("trans.xsl");
		try {
		      // TransformerFactory�C���X�^���X���擾
		      TransformerFactory factory = TransformerFactory.newInstance();
		      // XSL�t�@�C������tranceformer���擾
		      Transformer transformer = 
		        factory.newTransformer(new StreamSource(xsltdoc));
		      // �o�͂���G���R�[�f�B���O��ݒ�
		      transformer.setOutputProperty("encoding","Shift_JIS");
		      //�o��xml���FOut(���̃t�@�C����).xml
		      StreamResult result = new StreamResult(new FileOutputStream("Out_"+f_name+".xml"));
		      // XML�t�@�C����XSLT�ŕϊ����ďo��
		      transformer.transform(dom,result);
		    } catch(Exception e) {
		      e.printStackTrace();
		    }
		    */
		    
	}
					
}
