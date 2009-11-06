import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Take_Exam extends JFrame implements ActionListener {

	JFrame mainFrame = new JFrame();
	
	JPanel mainPanel;//CardLayout Base
	JPanel ChoicePanel;//�R�[�X�I�����
	JPanel TakePanel;//�o����
	JPanel AnswerPanel;//�𓚕\�����
	JPanel EndPanel;//�I�����
	//PageLayout
	CardLayout layout1;
	BorderLayout layout2;

	JButton checkBut; //TakePanel �̓_�{�^��
	JButton guBut; //TakePanel �M�u�A�b�v�{�^��
	JButton nextBut;//AnserPanel ���փ{�^��
	
	int flag;//��ʒ񎦃t���O
	
	public static void main (String[] args) {
		int flag = 1;
		Take_Exam win = new Take_Exam(flag);

	}
	

	public Take_Exam(int flag) {
		
		//mainFrame = new JFrame();
		//�I���{�^���̏���
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame�̑傫�����w��
		mainFrame.setSize(800, 500);
		
		
		//CardLayout�̃x�[�X�p�l��
		mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());
		
		
		if(flag == 1){
			//Start���
			ChoicePanel = new JPanel();
			ChoicePanel.setLayout(new BorderLayout());
		
			JPanel basePanel = new JPanel();
		
			//�J�n��ʒ񎦕�
			String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\test_course.xml";
		 
			try {
				// �h�L�������g�r���_�[�t�@�N�g���𐶐�
				DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
				// �h�L�������g�r���_�[�𐶐�
				DocumentBuilder builder = dbfactory.newDocumentBuilder();
				// �p�[�X�����s����Document�I�u�W�F�N�g���擾
				Document doc = builder.parse(new File(filename));
				// ���[�g�v�f���擾
				Element root = doc.getDocumentElement();
	      
				NodeList course_list = root.getElementsByTagName("COURSE");
				int length = course_list.getLength();
				basePanel.setLayout(new GridLayout(length,1));
				JButton[] cou_bot = new JButton[length];
				JLabel[] cou_name = new JLabel[length];
	      
	      
				for(int i=0;i<length;i++){
					Element elm = (Element)course_list.item(i);
					cou_name[i]= new JLabel(elm.getAttribute("title"));
					System.out.println("p_set:"+elm.getChildNodes().getLength());
					System.out.println("p_set:"+elm.getChildNodes().item(1).getNodeName());
					//p_set[i] = new String(elm.getChildNodes().item(1).getNodeValue());
					cou_bot[i]= new JButton("�w�K����");
					cou_bot[i].setActionCommand("START");
					cou_bot[i].addActionListener(this);
					basePanel.add(cou_name[i]);
					basePanel.add(cou_bot[i]);
				}
	      
				JLabel title = new JLabel("���K�V�X�e��");
				JPanel titlePanel = new JPanel();
				titlePanel.add(title);
	      
				ChoicePanel.add(basePanel,"Center");
				ChoicePanel.add(titlePanel,"North");
				//ChoicePanel.add(SouthP,"South");
				//ChoicePanel.add(EastP,"East");
				//ChoicePanel.add(WestP,"West");
				mainPanel.add(ChoicePanel,"StartPage");
				//----------------------------------------------------------
				mainPanel.add(ChoicePanel,"ChoicePage");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(flag == 2){
			//�o����
			TakePanel = new JPanel();
			TakePanel.setLayout(new BorderLayout());
			mainPanel.add(TakePanel,"TakePage");
		}
		
		if(flag == 3){
		//�𓚉��
			AnswerPanel = new JPanel();
			AnswerPanel.setLayout(new BorderLayout());
			mainPanel.add(AnswerPanel,"AnswerPage");
		}
		
		if(flag == 4){
		//�I�����
			EndPanel = new JPanel();
			EndPanel.setLayout(new BorderLayout());
			mainPanel.add(EndPanel,"EndPage");
		}
		mainFrame.add(mainPanel);
		//frame�̕`��
		mainFrame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "START" ){
			System.out.println("Event:"+e.getActionCommand());
			System.out.println("Event2:"+e.paramString());
			String[] com = e.paramString().split("=");
			String push = com[com.length-1];
			System.out.println("Event3:"+push);
			flag = 2;
			
			//Take_Exam(flag);
		}
	}

	//---------------------------------------------------
	public static void pict_exam(String program_set){
	
	int i;//���[�v�ϐ�
	JFrame mainFrame = new JFrame();
	JPanel mainPanel = new JPanel();
	
	JPanel quesPanel = new JPanel();//Center Panel
	JPanel ansPanel = new JPanel(); //West Panel
	ansPanel.setLayout(new GridLayout(6,1));
	JPanel butPanel = new JPanel(); //South Panel
	JPanel statePanel = new JPanel(); //Nouth Panel
	
	//���
	String l = new String("1");
	JLabel level = new JLabel("Level:"+l);
	JLabel set = new JLabel(" program_set:"+program_set);
	
	statePanel.add(level);
	statePanel.add(set);
	
	//��蕶��
	JTextArea mainArea = new JTextArea();//��蕶�񎦃G���A
	String questiontext = new String();
	//�񓚗�
	JTextField[] resp = new JTextField[10];
	
	JButton ans = new JButton("�̓_");//�̓_�{�^��
	JButton g_up = new JButton("GIVE UP");//Give up �{�^��
	JButton hints = new JButton("HINT");//�q���g�{�^��
	
	butPanel.add(ans);
	butPanel.add(g_up);
	butPanel.add(hints);
	
	
	String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\outputtest11_2.xml";
	  

    try {
      // �h�L�������g�r���_�[�t�@�N�g���𐶐�
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      // �h�L�������g�r���_�[�𐶐�
      DocumentBuilder builder = dbfactory.newDocumentBuilder();
      // �p�[�X�����s����Document�I�u�W�F�N�g���擾
      Document doc = builder.parse(new File(filename));
      // ���[�g�v�f���擾
      Element root = doc.getDocumentElement();
      
      //----------��蕶��--------------
      //��蕶���L�q���Ă���question�v�f��T���o��
      NodeList list = root.getElementsByTagName("question");
      for(i=0;i<list.getLength();i++){
    	  Element element = (Element)list.item(i);
    	  NodeList clist = element.getChildNodes();
    	  
    	  //��蕶���擾
    	  for(int j=0;j<clist.getLength();j++){
    		  if(clist.item(j).getNodeValue()!=null){
    			  questiontext = questiontext+clist.item(j).getNodeValue();
    		  }
    	  }
    	  //pre�ȉ��̖��̃\�[�X�R�[�h���擾
    	  NodeList pre = element.getElementsByTagName("pre");
    	  for(int k=0;k<pre.getLength();k++){
    		  //System.out.println("pre.getLength():"+pre.getLength());
    		  questiontext = questiontext + pre.item(k).getFirstChild().getNodeValue();
    	  }
      }
      
	  mainArea.setText(questiontext);
	  quesPanel.add(mainArea); 
      
	  //-----�����܂Ŗ�蕶��-----------
	  
      //------�񓚗��̍쐬-------------
      list = root.getElementsByTagName("response");
      //System.out.println("length:"+list.getLength());
      JPanel[] ansP = new JPanel[list.getLength()];
      for(i=0;i<list.getLength();i++){
    	  ansP[i] = new JPanel();
    	  JLabel ansLabel=new JLabel(list.item(i).getFirstChild().getNodeValue());
    	  //ansLabel[i].setText(list.item(i).getFirstChild().getNodeValue());
    	  //System.out.println("response:"+list.item(i).getFirstChild().getNodeValue());
    	  //ansPanel.add(ansLabel);
    	  //ansPanel.add(resp[i]=new JTextField(10));
    	  ansP[i].add(ansLabel);
    	  ansP[i].add(resp[i]= new JTextField(10));
    	  ansPanel.add(ansP[i]);
      }
      
      

      mainFrame.add(statePanel,"North");
      mainFrame.add(quesPanel,"Center");
      mainFrame.add(ansPanel,"East");
      mainFrame.add(butPanel,"South");
      //mainFrame.add(mainPanel);
    
    } catch (Exception e) {
    	e.printStackTrace();
    }
    mainFrame.setVisible(true);
}
	
	
}


