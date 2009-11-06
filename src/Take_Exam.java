import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Take_Exam extends JFrame implements ActionListener {

	JFrame mainFrame = new JFrame();
	
	JPanel mainPanel;//CardLayout Base
	JPanel ChoicePanel;//コース選択画面
	JPanel TakePanel;//出題画面
	JPanel AnswerPanel;//解答表示画面
	JPanel EndPanel;//終了画面
	//PageLayout
	CardLayout layout1;
	BorderLayout layout2;

	JButton checkBut; //TakePanel 採点ボタン
	JButton guBut; //TakePanel ギブアップボタン
	JButton nextBut;//AnserPanel 次へボタン
	
	int flag;//画面提示フラグ
	
	public static void main (String[] args) {
		int flag = 1;
		Take_Exam win = new Take_Exam(flag);

	}
	

	public Take_Exam(int flag) {
		
		//mainFrame = new JFrame();
		//終了ボタンの処理
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameの大きさを指定
		mainFrame.setSize(800, 500);
		
		
		//CardLayoutのベースパネル
		mainPanel = new JPanel();
		mainPanel.setLayout(new CardLayout());
		
		
		if(flag == 1){
			//Start画面
			ChoicePanel = new JPanel();
			ChoicePanel.setLayout(new BorderLayout());
		
			JPanel basePanel = new JPanel();
		
			//開始画面提示部
			String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\test_course.xml";
		 
			try {
				// ドキュメントビルダーファクトリを生成
				DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
				// ドキュメントビルダーを生成
				DocumentBuilder builder = dbfactory.newDocumentBuilder();
				// パースを実行してDocumentオブジェクトを取得
				Document doc = builder.parse(new File(filename));
				// ルート要素を取得
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
					cou_bot[i]= new JButton("学習する");
					cou_bot[i].setActionCommand("START");
					cou_bot[i].addActionListener(this);
					basePanel.add(cou_name[i]);
					basePanel.add(cou_bot[i]);
				}
	      
				JLabel title = new JLabel("演習システム");
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
			//出題画面
			TakePanel = new JPanel();
			TakePanel.setLayout(new BorderLayout());
			mainPanel.add(TakePanel,"TakePage");
		}
		
		if(flag == 3){
		//解答画面
			AnswerPanel = new JPanel();
			AnswerPanel.setLayout(new BorderLayout());
			mainPanel.add(AnswerPanel,"AnswerPage");
		}
		
		if(flag == 4){
		//終了画面
			EndPanel = new JPanel();
			EndPanel.setLayout(new BorderLayout());
			mainPanel.add(EndPanel,"EndPage");
		}
		mainFrame.add(mainPanel);
		//frameの描画
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
	
	int i;//ループ変数
	JFrame mainFrame = new JFrame();
	JPanel mainPanel = new JPanel();
	
	JPanel quesPanel = new JPanel();//Center Panel
	JPanel ansPanel = new JPanel(); //West Panel
	ansPanel.setLayout(new GridLayout(6,1));
	JPanel butPanel = new JPanel(); //South Panel
	JPanel statePanel = new JPanel(); //Nouth Panel
	
	//状態
	String l = new String("1");
	JLabel level = new JLabel("Level:"+l);
	JLabel set = new JLabel(" program_set:"+program_set);
	
	statePanel.add(level);
	statePanel.add(set);
	
	//問題文提示
	JTextArea mainArea = new JTextArea();//問題文提示エリア
	String questiontext = new String();
	//回答欄
	JTextField[] resp = new JTextField[10];
	
	JButton ans = new JButton("採点");//採点ボタン
	JButton g_up = new JButton("GIVE UP");//Give up ボタン
	JButton hints = new JButton("HINT");//ヒントボタン
	
	butPanel.add(ans);
	butPanel.add(g_up);
	butPanel.add(hints);
	
	
	String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\outputtest11_2.xml";
	  

    try {
      // ドキュメントビルダーファクトリを生成
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      // ドキュメントビルダーを生成
      DocumentBuilder builder = dbfactory.newDocumentBuilder();
      // パースを実行してDocumentオブジェクトを取得
      Document doc = builder.parse(new File(filename));
      // ルート要素を取得
      Element root = doc.getDocumentElement();
      
      //----------問題文提示--------------
      //問題文を記述しているquestion要素を探し出す
      NodeList list = root.getElementsByTagName("question");
      for(i=0;i<list.getLength();i++){
    	  Element element = (Element)list.item(i);
    	  NodeList clist = element.getChildNodes();
    	  
    	  //問題文を取得
    	  for(int j=0;j<clist.getLength();j++){
    		  if(clist.item(j).getNodeValue()!=null){
    			  questiontext = questiontext+clist.item(j).getNodeValue();
    		  }
    	  }
    	  //pre以下の問題のソースコードを取得
    	  NodeList pre = element.getElementsByTagName("pre");
    	  for(int k=0;k<pre.getLength();k++){
    		  //System.out.println("pre.getLength():"+pre.getLength());
    		  questiontext = questiontext + pre.item(k).getFirstChild().getNodeValue();
    	  }
      }
      
	  mainArea.setText(questiontext);
	  quesPanel.add(mainArea); 
      
	  //-----ここまで問題文提示-----------
	  
      //------回答欄の作成-------------
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


