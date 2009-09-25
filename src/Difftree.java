import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;


	public class Difftree {

	  public static void main (String[] args) {
		  
		  String filename1 = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_if.xml";
		  String filename2 = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_while.xml";

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
	      
	      	      
	      //�����œn�����߂�FUNC�ȉ����m�[�h���X�g��
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
		  int leng_l,leng_s;
		  NodeList Long_L,Short_L;
		  
		  
		  if(L1.getLength() == L2.getLength()){//NodeList�̒����i���ʂ̐ߖځE�t�̐��j�����������ׂ�
			leng_l = L1.getLength();
			leng_s = L2.getLength();
			Long_L = L1;
			Short_L = L2;
		  }

		  else {//NodeList�̒����i�ߖځE�t�̐��j��������ꍇ
			  //if(L1.getLength() > L2.getLength()){/*L1.getLength() > L2.getLength()�̂Ƃ�*/
				  leng_l = L1.getLength();
				  leng_s = L2.getLength();
				  Long_L = L1;
				  Short_L = L2;
			  //}
			  //else {/*L1.getLength() < L2.getLength()�̂Ƃ�*/
				  //leng_l = L2.getLength();
				  //leng_s = L1.getLength();
				  //Long_L = L2;
				  //Short_L = L1;
			  //}
		  }
		  //�e�߂̑Ή��\�����
		  
		  int[][] E_list= new int[leng_l*leng_s*2][2];//�Ή��\
		  int p=0;//�Ή��\�̂ǂ̈ʒu�܂Œl�������Ă��邩
		  int flag;//�Ή�������̂����������ǂ����̃t���O
		  int some;//�P�̃m�[�h�ɑ΂��ĕ����Ή����Ă��邩�ǂ����̃t���O
		  //int ch;
		  
		  //System.out.println("leng_l="+leng_l + " ,Leng_s="+leng_s);
		  
		  for (int i=0; i< leng_l ; i++){
			  Element elm1 = (Element)Long_L.item(i);//���X�gL1��i�ڂ̗v�f���G�������g�Ƃ��Ē�`
			  flag = 0;
			  some = 0;
			  for (int j=0; j< leng_s ; j++){
				  //ch = 0;
				  Element elm2 = (Element)Short_L.item(j);//���X�gL2�̂��ڂ̗v�f���G�������g�Ƃ��Ē�`
				  //System.out.println("elm1.TN:"+elm1.getTagName()+" elm2.TN:"+elm2.getTagName());
				  if(elm1.getTagName() == elm2.getTagName()){//�^�O�����ꏏ��������
					  //System.out.println("elm1.TN:"+elm1.getTagName()+" elm2.TN:"+elm2.getTagName());
					  if(some < 1){
						  if (elm1.getTagName().equals("NONE")){//<NONE/>�ɂ��Ă̏���(�q�v�f���Ȃ����߈���������j
							  E_list[p][0]=i;//�Ή��\�ɒǉ�
							  E_list[p][1]=j;
							  p++;//�|�W�V���������炷
						  }
						  else if (elm1.getFirstChild().getNodeValue() != null ){//�e�q�v�f�����邩�ǂ���
							  //System.out.println("t/f:"+(elm1.getFirstChild().getNodeValue()).equals(elm2.getFirstChild().getNodeValue()));
							  //System.out.println("elm1.child:"+elm1.getFirstChild().getNodeValue() +",elm2.child:"+elm2.getFirstChild().getNodeValue());
							  if((elm1.getFirstChild().getNodeValue()).equals(elm2.getFirstChild().getNodeValue())){//����Ɏq�̃e�L�X�g�����ꂩ�ǂ���
								  E_list[p][0]=i;//�Ή��\�ɒǉ�
								  E_list[p][1]=j;
								  p++;//�|�W�V���������炷
							  }
							  else {/*�����ŉ�������*/
								  //System.out.println("elm1.child:"+elm1.getFirstChild().getNodeValue() +",elm2.child:"+elm2.getFirstChild().getNodeValue());
								  E_list[p][0]=i;//�Ή��\�ɒǉ�
								  E_list[p][1]=j;
								  p++;//�|�W�V���������炷
								  
								  E_list[p][0]=i;//�q�m�[�h����Ή��\�ɒǉ�
								  E_list[p][1]=-2;//�q�m�[�h�t���O(-2)
								  p++;
								  //ch++;
								  //System.out.println("p:"+p+",E_list[p][0]:"+E_list[p-1][0]+",E_list[p][1]:"+E_list[p-1][1]);
								  //System.out.println("ch:"+ch);
							  }
						  }
						  else{
							  E_list[p][0]=i;//�Ή��\�ɒǉ�
							  E_list[p][1]=j;
							  p++;//�|�W�V���������炷
						  }
					  }
					  else {
						  //System.out.println("E_list[p-1][1]:"+E_list[p-1][1]);
						  if(i == j){
							  if(E_list[p-1][1]!=-2){
								  E_list[p-1][0]=i;
								  E_list[p-1][1]=j;
							  }
							  else {
								  E_list[p-2][0]=i;
								  E_list[p-1][1]=j;
							  }
						  }
					  }
					  //System.out.println("p = "+p);
					  //System.out.println("elm1:"+elm1.getTagName()+" ,elm2;"+elm2.getTagName());
					  flag = 1;//�Ή�������̂����������ǂ����̃t���O
					  some++;
					  //System.out.println("some:"+some);
				  }
				  else{
					  //System.out.println("el1:"+elm1.getTagName()+" ,el2:"+elm2.getTagName());
				  }
			  }
			  if(flag == 0){//�Ή�������̂��Ȃ����
				  E_list[p][0]=i;
				  E_list[p][1]=-1;
				  p++;//�|�W�V���������炷
			  }
			  /*if(some <1){//�����Ή�������̂��������ꍇ
				  System.out.println("Check!!");
				  for(int a=0;a<some;a++){
					  if(E_list[p-a][0]==E_list[p-a][1]){//�������Ԃŏo������\���������̂ŁA������Ή�����m�[�h�Ƃ���
						  E_list[p-some][0]=E_list[p-a][0];
						  E_list[p-some][1]=E_list[p-a][1];
						  p=p-some+1;
					  }
				  }
			  }*/
			  //System.out.println("\n");
		  }
		  
		  for (int i=0; i < p ; i++){
	      //for (int i=0; i < leng_l ; i++){
	    	  /*if(E_list[i][0] == -1){
	    		  System.out.println("����1�F"+((Element)Long_L.item(E_list[i][0])).getTagName());
	    	  }
	    	  else if(E_list[i][1] == -1){
	    		  System.out.println("����2�F"+((Element)Long_L.item(E_list[i][0])).getTagName());
	    	  }
	    	  else if(E_list[i][0] == -1 && E_list[i][1] == -1){
	    		  System.out.println("����3�F"+((Element)Long_L.item(E_list[i][0])).getTagName());  
	    	  }*/
	    	  //System.out.println("E_list["+i+"][0]:"+E_list[i][0]+" ,E_list["+i+"][1]:"+E_list[i][1]);
	    	  if(E_list[i][0] == -1 ||E_list[i][1] == -1){
	    		  //System.out.println("�����F"+((Element)Long_L.item(E_list[i][0])).getTagName());
	    		  DiffPrint((Element)Long_L.item(E_list[i][0]),0);
	    	  }
	    	  else if(E_list[i][0] == -2 || E_list[i][1]== -2){
	    		  System.out.println(((Element)Long_L.item(E_list[i][0])).getFirstChild().getNodeValue());
	    	  }
	    	  else{
	    		  
	    		  Element elm1 = (Element)Long_L.item(E_list[i][0]);//i�ڂ̗v�f���G�������g�Ƃ��Ē�`
	    		  Element elm2 = (Element)Short_L.item(E_list[i][1]);
	    		  //System.out.println(elm1.getTagName());
	    		  
	    		  NodeList childList1 = elm1.getChildNodes();//i�ڂ̗v�f�̎q�m�[�h���X�g���`
	    		  NodeList childList2 = elm2.getChildNodes();
	    	  
	    		  if (elm1.getTagName() == "NONE"){//�^�O����NONE���Ǝq�v�f���Ȃ��̂ōċA���Ȃ� 
	    		  }
	    		  
	    		  else if(elm1.getFirstChild().getNodeValue() != null){
	    			  //���̌`�u<�^�O��>�e�L�X�g<�^�O��>�v���Ǝq�v�f�ɕ����؂������Ȃ��d�l�Ȃ̂ōċA���Ȃ�
	    			  //�����ł��Ȃ��炵��<STRING></STRING>�̒��ɂ̓e�L�X�g�Ɠ����ɗv�f�������Ă����c�i���Ή��j
	    			  //System.out.println(elm1.getFirstChild().getNodeValue());  
	    		  }

	    		  else if(childList1.getLength() != 0 ){//��L2�̏����{�v�f��0�ȊO�̂Ƃ��͍ċA����
	    			  
	    			  SearchChild(childList1,childList2);//�q�v�f�̃��X�g�������ɍċA
	    		  }
	    	  }
	      }
	  }
	  
	  public static void DiffPrint(Element e,int a){//������\������
		  NodeList cList = e.getChildNodes();
		  a++;
		  //for(int j = 0;j<a;j++)System.out.println("-");
		  
		  System.out.println(e.getTagName());
		  if (e.getTagName() == "NONE"){//�^�O����NONE���Ǝq�v�f���Ȃ��̂ōċA���Ȃ� 
		  }
		  
		  else if(e.getFirstChild().getNodeValue() != null){//���̌`�u<�^�O��>�e�L�X�g<�^�O��>�v���Ǝq�v�f�ɕ����؂������Ȃ��d�l�Ȃ̂ōċA���Ȃ�
			  System.out.println(e.getFirstChild().getNodeValue());  
		  }

		  else if(cList.getLength() != 0 ){//��L2�̏����{�v�f��0�ȊO�̂Ƃ��͍ċA����
			  for(int i=0;i<cList.getLength();i++){
				  DiffPrint((Element)cList.item(i),a);
			  }
		  }
	  }
	  
	  
	}

