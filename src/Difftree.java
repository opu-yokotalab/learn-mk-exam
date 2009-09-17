import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


	public class Difftree {

	  public static void main (String[] args) {
		  
		  String filename1 = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_while.xml";
		  String filename2 = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_if.xml";

	    try {
	      // �h�L�������g�r���_�[�t�@�N�g���𐶐�
	      DocumentBuilderFactory dbfactory1 = DocumentBuilderFactory.newInstance();
	      // �h�L�������g�r���_�[�𐶐�
	      DocumentBuilder builder1 = dbfactory1.newDocumentBuilder();
	      // �p�[�X�����s����Document�I�u�W�F�N�g���擾
	      Document doc1 = builder1.parse(new File(filename1));
	      // ���[�g�v�f���擾
	      Element root1 = doc1.getDocumentElement();
	      
	      // �h�L�������g�r���_�[�t�@�N�g���𐶐�
	      DocumentBuilderFactory dbfactory2 = DocumentBuilderFactory.newInstance();
	      // �h�L�������g�r���_�[�𐶐�
	      DocumentBuilder builder2 = dbfactory2.newDocumentBuilder();
	      // �p�[�X�����s����Document�I�u�W�F�N�g���擾
	      Document doc2 = builder2.parse(new File(filename2));
	      // ���[�g�v�f���擾
	      Element root2 = doc2.getDocumentElement();	      
	      
	      
	      
	      
	      // �ŏ��̎q�m�[�h�i�e�L�X�g�m�[�h�j�̒l��\��
	      //System.out.println("filename="+filename1);
	      //System.out.print(root1.getFirstChild().getNodeValue()+"\n");
	      
	      
	      //System.out.println("filename="+filename2);
	      //System.out.println("���[�g�v�f�̃^�O���F" + root2.getTagName());
	      
	      
	      NodeList list1 = root1.getElementsByTagName("FUNC");
	      NodeList list2 = root2.getElementsByTagName("FUNC");
	      /*for (int i=0; i < list.getLength() ; i++){
	    	  Element elm = (Element)list.item(i);
	    	  NodeList childList = elm.getChildNodes();
	    	  System.out.println("Child Length:"+ childList.getLength());
	      }*/
	      
	      SearchChild(list1,list2);/*�ċA�Ăяo��*/

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }	
	  }
	  
	  public static void SearchChild(NodeList L1,NodeList L2){/*�[���D��T����value��\�����Ă���*/
		  //System.out.println("Length:" + l.getLength());
		  if(L1.getLength() == L2.getLength()){//NodeList�̒����i���ʂ̐ߖځE�t�̐��j�����������ׂ�
			System.out.println("L1 == L2");  
			for (int i=0; i < L1.getLength() ; i++){
		    	  Element elm1 = (Element)L1.item(i);//i�ڂ̗v�f���G�������g�Ƃ��Ē�`
		    	  Element elm2 = (Element)L2.item(i);
		    	  System.out.println(elm1.getTagName());

		    	  NodeList childList1 = elm1.getChildNodes();//i�ڂ̗v�f�̎q�m�[�h���X�g���`
		    	  NodeList childList2 = elm2.getChildNodes();
		    	  
		    	  if (elm1.getTagName() == "NONE"){/*�^�O����NONE���Ǝq�v�f���Ȃ��̂ōċA���Ȃ�*/  
		    	  }
		    	  
		    	  else if(elm1.getFirstChild().getNodeValue() != null){/*���̌`�u<�^�O��>�e�L�X�g<�^�O��>�v���Ǝq�v�f�ɕ����؂������Ȃ��d�l�Ȃ̂ōċA���Ȃ�*/
		    		  System.out.println(elm1.getFirstChild().getNodeValue());  
		    	  }

		    	  else if(childList1.getLength() != 0 ){/*��L2�̏����{�v�f��0�ȊO�̂Ƃ��͍ċA����*/
		    		  
		    		  SearchChild(childList1,childList2);/*�q�v�f�̃��X�g�������ɍċA*/
		    	  }
		      }
		  }

		  else {//NodeList�̒����i�ߖځE�t�̐��j��������ꍇ
			  if(L1.getLength() > L2.getLength()){/*L1.getLength() > L2.getLength()�̂Ƃ�*/
				  System.out.println("L1 - L2 ="+ (L1.getLength()-L2.getLength()));
			  }
			  else {/*L1.getLength() < L2.getLength()�̂Ƃ�*/
				  System.out.println("L2 - L1�@="+ (L1.getLength()-L2.getLength()));
			  }
		  }
		  
	      /*for (int i=0; i < L1.getLength() ; i++){
	    	  Element elm1 = (Element)L1.item(i);
	    	  Element elm2 = (Element)L2.item(i);
	    	  System.out.println(elm1.getTagName());

	    	  NodeList childList1 = elm1.getChildNodes();
	    	  NodeList childList2 = elm2.getChildNodes();
	    	  
	    	  if (elm1.getTagName() == "NONE"){//�^�O����NONE���Ǝq�v�f���Ȃ��̂ōċA���Ȃ� 
	    	  }
	    	  
	    	  else if(elm1.getFirstChild().getNodeValue() != null){//���̌`�u<�^�O��>�e�L�X�g<�^�O��>�v���Ǝq�v�f�ɕ����؂������Ȃ��d�l�Ȃ̂ōċA���Ȃ�
	    		  System.out.println(elm1.getFirstChild().getNodeValue());  
	    	  }

	    	  else if(childList1.getLength() != 0 ){//��L2�̏����{�v�f��0�ȊO�̂Ƃ��͍ċA����
	    		  
	    		  SearchChild(childList1,childList2);//�q�v�f�̃��X�g�������ɍċA
	    	  }
	      }*/
	  }
	  
	}

