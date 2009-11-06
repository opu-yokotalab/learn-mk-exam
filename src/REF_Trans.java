import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class REF_Trans {/*���K�t�H�[�}�b�g�iRegular Expression Format)�ւ̕ϊ��i���C���j*/
	
	//�t�B�[���h
	private static Node state1;
	private static Node state2;
	private static Node state3;
	private static Node exe;
	private static String num_id;
	
	/*���K�t�H�[�}�b�g�ւ̕ϊ����C��*/
	public static void main (String[] args){
		
		//�ϊ�����xml�����̎w��
		String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_h2.xml";
		//String f_name= filename.split("\\")[(filename.split("\\").length)-1];
		String f_name = new String("output_h2");
	    try {
	      // �h�L�������g�r���_�[�t�@�N�g���𐶐�
	      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	      // �h�L�������g�r���_�[�𐶐�
	      DocumentBuilder builder = dbfactory.newDocumentBuilder();
	      // �p�[�X�����s����Document�I�u�W�F�N�g���擾
	      Document doc = builder.parse(new File(filename));
	      
	      //�ϐ��Ɏ��ʂh�c��t��
	      doc = add_ID(doc);
	      
	      /*���K�t�H�[�}�b�g�֕ϊ�*/
	      doc = Transformation(doc,f_name);
	      
	      /*�Ή��e�[�u���쐬*/
	      doc = Make_Table(doc,f_name);
	      
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
	public static Document add_ID(Document doc){
		//�ϐ��ɂh�c��t��
		
		int table_c = 0;//�Ή��e�[�u���p�̃J�E���^
		String[][] v_table = new String[50][2];//�ϐ��̑Ή��e�[�u�� 
		
		// ���[�g�v�f���擾
		Element root = doc.getDocumentElement();
		//�ϐ��̒�`���s���Ă���DECLARATION�m�[�h�̃��X�g�𓾂�
		NodeList list1 = root.getElementsByTagName("DECLARATION");
		for(int i=0;i<list1.getLength();i++){
			Element element1 = (Element)list1.item(i);
			
			//DECLARATION�̎q�m�[�h��TYPE��T���i�ϐ��̌^�j
			//NodeList element_list1 = element1.getElementsByTagName("TYPE");
			//Element type_elm = (Element)element_list1.item(0);
			//type = type_elm.getFirstChild().getNodeValue();
			
			//DECLARATION�̎q�m�[�h�ɂ���VARIABLE��T���i�ϐ����j
			NodeList element_list1 = element1.getElementsByTagName("VARIABLE");
			for(int j=0;j<element_list1.getLength();j++){
				
				//�ϐ��̃��X�g��������
				Element elm1 = (Element)element_list1.item(j);
				
				//�ϐ��̑Ή��e�[�u����ID�Ɩ��O���i�[
				v_table[table_c][0] = "$"+table_c;
				v_table[table_c][1] = elm1.getFirstChild().getNodeValue();
				//v_table[table_c][2] = type;
				//v_table[table_c][3] = "N"+node_c+",";
				
				//ID���ӂ�
				elm1.setAttribute("id","$"+table_c);
				//elm1.setAttribute("n_id","N"+node_c);
				
				//�J�E���^��1�����߂�
				table_c++;
				//node_c++;
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
						//elm2.setAttribute("n_id","N"+node_c);

						//�Ή��e�[�u���ւ̏�������
						//v_table[m][3]=v_table[m][3]+"N"+node_c+",";
						//node_c++;
					}
				}
			}
		}
		
		return doc;
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
				
				//ID�̂ӂ�Ȃ���
				elm1.removeAttribute("id");
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
						elm2.removeAttribute("id");
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
		
		// �������̃��[�g�v�f���擾
		Element root = doc.getDocumentElement();

		
		//----------�n�FWHILE��FOR�ɒu������--------------------------
		//
		//FOR�̃e���v���[�g�̓ǂݍ���
		/*String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\temp_for.xml";
	    try {
	      // �h�L�������g�r���_�[�t�@�N�g���𐶐�
	      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	      // �h�L�������g�r���_�[�𐶐�
	      DocumentBuilder builder = dbfactory.newDocumentBuilder();
	      // �p�[�X�����s����Document�I�u�W�F�N�g���擾
	      Document temp = builder.parse(new File(filename));
	    */  

		//
		//�������̉��
		//									
		//WHILE��T��
		NodeList while_list = root.getElementsByTagName("WHILE");
		if(while_list.getLength()!=0){
			for(int i=0;i<while_list.getLength();i++){
				Element elm1 = (Element)while_list.item(i);
			
				//��2������T��
				NodeList state_list = elm1.getElementsByTagName("PARENTHESIS");
				for(int j=0;j<state_list.getLength();j++){
					Element state = (Element)state_list.item(j);
					//��2����(EXPRESSION�̎q���擾�j	
					state2 = state.getFirstChild();
				}
			
				//��3������T��
				state_list = elm1.getElementsByTagName("STATEMENT");
				for (int k=0;k<state_list.getLength();k++){
					Element state = (Element)state_list.item(k);
					//old_n = state_list.item(k);
					//��3����(while�̏������̃u���b�N�̈�ԍŌ��FOR�̑�3���������邱�Ƃ��O��j
					state3 = state.getLastChild();
					//�������e(����1�s�̏ꍇ�������Ȃ��F�čl�̗]�n����)
					exe = state.getFirstChild();
					//������ID���擾����
					num_id = state3.getFirstChild().getFirstChild().getAttributes().item(0).getNodeValue();
					//System.out.println("num_id:"+num_id);
					//System.out.println("i:"+state3.getFirstChild().getFirstChild().getFirstChild().getNodeValue());
				}
				
			}
			//}
			//��1������T��
			NodeList dec_list = root.getElementsByTagName("DECLARATION");
			for(int i=0;i<dec_list.getLength();i++){
			
				int flag = 0;//ID�����������̂����邩�ǂ����̃t���O
			
				Element val = (Element)dec_list.item(i);
				NodeList val_list = val.getElementsByTagName("VARIABLE");
				for(int j=0;j<val_list.getLength();j++){
					//System.out.println("num_id:"+num_id);
					//System.out.println("value:"+val_list.item(j).getAttributes().item(0).getNodeValue());
					//System.out.println("true/false:"+num_id.equals(val_list.item(j).getAttributes().item(0).getNodeValue()));
					if(num_id.equals(val_list.item(j).getAttributes().item(0).getNodeValue())){
						flag=1;
					}
					//System.out.println("flag:"+flag);
				}
				if(flag==1){
					NodeList ass = val.getElementsByTagName("DECLARATOR");
					for(int k=0;k<ass.getLength();k++){
						Element state = (Element)ass.item(k);
						state1 = state.getFirstChild().cloneNode(true);
					}
					//System.out.println("state1:"+state1.getFirstChild().getFirstChild().getFirstChild().getNodeValue());
					flag=0;
				}
			}
			
			//System.out.println("state1:"+state1.getNodeName());
			//System.out.println("state2:"+state2.getNodeName());
			//System.out.println("state3:"+state3.getNodeName());
			//
			//�e���v���[�g�쐬
			//
			Element temp = doc.createElement("template");
			//<FOR>�쐬
			Element fo = doc.createElement("FOR");
			//�e���v���[�g��<FOR>��
			temp.appendChild(fo);
			Element pare = doc.createElement("PARENTHESIS");
			//FOR�̎q�m�[�h��PARENTHESIS
			fo.appendChild(pare);
			Element exp = doc.createElement("EXPRESSION");
			//PARENTHESIS�̎q�m�[�h��EXPRESSION(��1�����j
			pare.appendChild(exp);
			exp.appendChild(state1);
			//��2����
			pare.appendChild(state2);
			//��3����
			pare.appendChild(state3);
			//FOR�̎q��BLOCK
			Element block = doc.createElement("BLOCK");
			fo.appendChild(block);
			//BLOCK�̎q��STATEMENT
			Element state = doc.createElement("STATEMENT");
			block.appendChild(state);
			state.appendChild(exe);
		
			NodeList ch = root.getElementsByTagName("STATEMENT");
			//���͂�STATEMENT
			Element st = (Element)ch.item(0);
			st.insertBefore(fo,st.getElementsByTagName("WHILE").item(0));
			st.removeChild(st.getElementsByTagName("WHILE").item(0));
		}
		
		//---------------�I�Fwhile�u������-------------------------------
		
		//---------------�n�F�����@���u������----------------------------
		
		//�����@�������藧���̂̒u������(���Z�j
		NodeList plus_list = root.getElementsByTagName("PLUS");
		for(int i=0;i<plus_list.getLength();i++){
			Element plus = (Element)plus_list.item(i);
			NodeList hen_list = plus.getChildNodes();
			//System.out.println("hen_list:"+hen_list.getLength());
			for(int j=0;j<hen_list.getLength();j++){
				//System.out.println("j1:"+j);
				Element L_hen =(Element)hen_list.item(j);
				Element R_hen =(Element)hen_list.item(j+1);
				Node l = L_hen.getFirstChild().getFirstChild();
				Node r = R_hen.getFirstChild().getFirstChild();
				//String r = R_hen.getFirstChild().getFirstChild().getNodeValue();
				if(l.getNodeValue()!= null && r.getNodeValue()!= null){
					if((l.getNodeValue()).compareTo(r.getNodeValue())>0){//����ւ����s��
						String tmp;
						
						tmp = l.getNodeValue();
						l.setNodeValue(r.getNodeValue());
						r.setNodeValue(tmp);
					
					}
				
					//System.out.println("compareTo:"+(l.getNodeValue()).compareTo(r.getNodeValue()));
				}
				//System.out.println("hen:"+l.getNodeValue());
				//System.out.println("hen:"+r.getNodeValue());
				//System.out.println("---------------------");
				j++;
				//System.out.println("j2:"+j);
			}
		}
		
		//���z�@�������藧���̂̒u�������i��Z�j
		NodeList multi_list = root.getElementsByTagName("MULTI");
		for(int i=0;i<multi_list.getLength();i++){
			Element multi = (Element)multi_list.item(i);
			Element LNode = (Element)multi.getFirstChild();//MULTI�̍���
			Element RNode = (Element)multi.getLastChild();//MULTI�̉E��
			
			if(LNode.getElementsByTagName("PARENTHESIS").getLength() > 0){//���ӂɃJ�b�R������ꍇ
				System.out.println("LNode:"+LNode.getElementsByTagName("PARENTHESIS").getLength());
			}
			if(RNode.getElementsByTagName("PARENTHESIS").getLength() > 0){//�E�ӂɃJ�b�R������ꍇ
				System.out.println("RNode:"+RNode.getElementsByTagName("PARENTHESIS").getLength());
			}			
			//NodeList hen_list = multi.getElementsByTagName("PARENTHESIS");//�J�b�R�ɂ��P��
			
		
		}
		//�����@�������藧���̂̒u�������i��Z�j
		multi_list = root.getElementsByTagName("MULTI");
		for(int i=0;i<multi_list.getLength();i++){
			Element plus = (Element)multi_list.item(i);
			NodeList hen_list = plus.getChildNodes();
			//System.out.println("hen_list:"+hen_list.getLength());
			for(int j=0;j<hen_list.getLength();j++){
				//System.out.println("j1:"+j);
				Element L_hen =(Element)hen_list.item(j);
				Element R_hen =(Element)hen_list.item(j+1);
				Node l = L_hen.getFirstChild().getFirstChild();
				Node r = R_hen.getFirstChild().getFirstChild();
				//String r = R_hen.getFirstChild().getFirstChild().getNodeValue();
				if(l.getNodeValue()!= null && r.getNodeValue()!= null){
					if((l.getNodeValue()).compareTo(r.getNodeValue())>0){//����ւ����s��
						String tmp;
						
						tmp = l.getNodeValue();
						l.setNodeValue(r.getNodeValue());
						r.setNodeValue(tmp);
					
					}
				
					//System.out.println("compareTo:"+(l.getNodeValue()).compareTo(r.getNodeValue()));
				}
				//System.out.println("hen:"+l.getNodeValue());
				//System.out.println("hen:"+r.getNodeValue());
				//System.out.println("---------------------");
				j++;
				//System.out.println("j2:"+j);
			}
		}
		
		//------------�I�F�����@���u������-----------------------------
		
		//doc.insertBefore(temp,old_n.getChildNodes());
		//���������̒u��
		/*//for�̃e���v���[�g�̃��[�g�v�f���擾
	    Element temp_root = temp.getDocumentElement();
		NodeList par = temp_root.getElementsByTagName("PARENTHESIS");
		for(int i=0;i<par.getLength();i++){
			Element parent = (Element)par.item(i);
			NodeList exp_list = parent.getElementsByTagName("EXPRESSION");
			for(int j=0;j<exp_list.getLength();j++){
				Element express = (Element)exp_list.item(j);
				if(express.getAttribute("No").equals("1")){
					express.replaceChild(state1,express.getFirstChild());
				}
				if(express.getAttribute("No").equals("2")){
					//express.replaceChild(state2,express.getFirstChild());
				}
				if(express.getAttribute("No").equals("3")){
					//express.replaceChild(state3,express.getFirstChild());
				}
				//System.out.println("aa:"+express.getAttribute("No"));
			}
		}*/
		
		
		
	    /*} catch (Exception e) {
	    	e.printStackTrace();
	    }*/
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
		    }*/
		    
			
			return doc;
			    
	}
					
}
