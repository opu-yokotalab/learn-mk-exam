import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import antlr.*;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.*;
import org.w3c.dom.*;
import javax.swing.border.*;
import java.sql.*;
import java.net.*;

/*mainな処理をするクラス。
  レイアウトの作成と他クラスの呼び出しをする。
  パネルの使い過ぎでよくわからないことになっている。
*/

class Make extends JFrame implements ActionListener {

  int w_width;
  int w_height;
  
  JFrame frm = new JFrame("");
  JPanel mainPanel;
  JPanel framePanel;
  JScrollPane frameScroll;
  GridBagLayout frameLayout;
  CardLayout maincenterLayout;
  CardLayout mainrightLayout;

  JPanel fileinputPanel;
  JTextArea filead;
  JButton fileButton;
  String filename;
  FileDialog fd;

  JTextArea inputtext;
  static JScrollPane inputScroll;
  JTextArea outputtext;
  JScrollPane outputScroll;
  JPanel centerPanel;
  JPanel rightPanel;
  
  JPanel subrightPanel1;
  JPanel subrightPanel2;

  JPanel subcenterPanel1;
  
  JPanel subcenterPanel2;
  JScrollPane sectionScroll;
  JPanel subcenterPanel3;
  JScrollPane selectScroll;
  
  JPanel subcenterPanel4;

  JPanel subselectpanel;

  JPanel subsubright1;
  JPanel subsubright2;
  JScrollPane subsubrightScroll;
  
  JScrollPane questionScroll;

  JButton config;
  
  //入力項目定義
  JTextArea question;
  //JTextArea sentence;
  JTextField sentence;
  JTextField program_set;
  JTextField id;
  //JTextArea hints;
  JTextField hints;
  JTextField function;
  JTextField score;
  JTextField weight;
  JTextField point;
  //JTextArea explanation;
  JTextField explanation;
  
  JCheckBox[] rbutton;
  JCheckBox[] sectiontitle;
  JCheckBox[][] subsectionTitle;
  
  //createblankのコンボボックス定義
  JTextField nodename;
  JComboBox appointcomb;
  JComboBox conditcomb;
  int cmb_i,cmb_j;

  int sectioncount ;
  int[] subsectioncount ; 
  String[] sectiontitleString;
  String[][] subsectionTitleString;


  public static QuestionMap QM;

  int number;
  InputStream inp;
  GridBagLayout layout2;
  JPanel selection;
  
  public static void main(String args[]) {
    Make win = new Make();

    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //win.setBounds(10,10,1300,1000);
    //setWindowSize(win.getBounds());
    //win.setSize(1100,800);
     
    win.setTitle("穴埋め問題作成");
    win.setVisible(true);
  }

  public Make() {
    
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle rect = env.getMaximumWindowBounds();
    setBounds(rect); //全画面表示
    
    UIManager.put("Label.font", new Font("Dialog", Font.PLAIN, 18));//ベースフォント
    UIManager.put("TextArea.font", new Font("Dialog", Font.PLAIN, 18));
    UIManager.put("CheckBox.font", new Font("Dialog", Font.BOLD, 18));
    UIManager.put("Button.font", new Font("Dialog", Font.BOLD, 18));

    LineBorder inborder = new LineBorder(Color.gray,2);
    
    GridBagConstraints frameGBC = new GridBagConstraints();
    frameLayout = new GridBagLayout();
    framePanel = new JPanel();
    framePanel.setLayout(frameLayout);
    framePanel.setPreferredSize(new Dimension(1100,800));
    frameScroll = new JScrollPane(framePanel);

    selection = new JPanel();
    
    JMenuBar menubar = new JMenuBar();
    setJMenuBar(menubar);
    JMenu configMenu = new JMenu("設定");
    menubar.add(configMenu);

    /*章構成*/
    try{
      int data;
      FileReader fr = new FileReader(new File("section.ini"));
      BufferedReader br = new BufferedReader(fr);
      sectioncount = Integer.parseInt(br.readLine());
      
      //System.out.println("sectioncount = "+sectioncount);//デバグ出力sectioncount
      
      subsectioncount = new int[sectioncount];
      sectiontitleString = new String[sectioncount];
      subsectionTitleString = new String[sectioncount][];
      for(int i=0 ; i<sectioncount ; i++){
        subsectioncount[i] = Integer.parseInt(br.readLine());
        
        //System.out.println("subsectioncount[" +i+ "] = " + subsectioncount[i]);//デバグ出力subsectioncount
        
        sectiontitleString[i] = br.readLine();
        subsectionTitleString[i] = new String[subsectioncount[i]];
        for(int j=0 ; j<subsectioncount[i] ; j++){
          subsectionTitleString[i][j]=br.readLine();
          //System.out.println("subsectionTitle["+j+"] = "+subsectionTitleString[i][j]);//デバグ出力subsectiontitle
        }
      }
    }catch(IOException ex){
      System.out.println("error:section.ini");
      ex.printStackTrace();
    }

    /*ファイル入力*/
    fileinputPanel = new JPanel();
    filead = new JTextArea(1,20);
    fileButton = new JButton("入力");
    fileButton.setActionCommand("LOAD");
    fileButton.addActionListener(this);
    fileinputPanel.add(filead);
    fileinputPanel.add(fileButton);
    fileinputPanel.setPreferredSize(new Dimension(450,75));
    TitledBorder border1 = new TitledBorder(inborder,"ソースファイル入力",TitledBorder.RIGHT,TitledBorder.TOP);
    fileinputPanel.setBorder(border1);
    filename = new String();

    /*メイン処理レイアウト*/
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setPreferredSize(new Dimension(900,600));
    maincenterLayout = new CardLayout();
    mainrightLayout = new CardLayout();
    inputtext = new JTextArea();
    inputtext.setEnabled(false);
    inputScroll = new JScrollPane(inputtext);
    inputScroll.setPreferredSize(new Dimension(300,600));
    outputtext = new JTextArea();
    outputScroll = new JScrollPane(outputtext);
    outputtext.setEnabled(false);
    outputScroll.setPreferredSize(new Dimension(300,550));
    centerPanel = new JPanel();
    centerPanel.setPreferredSize(new Dimension(600,600));
    rightPanel = new JPanel();
    rightPanel.setPreferredSize(new Dimension(300,600));
    centerPanel.setLayout(maincenterLayout);
    rightPanel.setLayout(mainrightLayout);
    mainPanel.add(inputScroll,BorderLayout.LINE_START);
    mainPanel.add(centerPanel,BorderLayout.CENTER);

    /*メイン処理ボーダー*/
    LineBorder inborder2 = new LineBorder(Color.gray,2);
    TitledBorder border2 = new TitledBorder(inborder2,"▼オリジナルソース",TitledBorder.RIGHT,TitledBorder.TOP);
    inputScroll.setBorder(border2);

    /*メイン処理右*/
    subrightPanel1 = new JPanel();
    subrightPanel1.setPreferredSize(new Dimension(300,600));
    subrightPanel2 = new JPanel();
    subrightPanel2.setPreferredSize(new Dimension(300,600));
    rightPanel.add(subrightPanel1,"output1");
    rightPanel.add(subrightPanel2,"output2");

    /*メイン処理真ん中*/
    subcenterPanel1 = new JPanel();
    subcenterPanel1.setPreferredSize(new Dimension(600,600));
    subcenterPanel2 = new JPanel();
    sectionScroll = new JScrollPane(subcenterPanel2);
    sectionScroll.setPreferredSize(new Dimension(600,600));
    subcenterPanel3 = new JPanel();
    subcenterPanel3.setLayout(new BorderLayout());
    subcenterPanel3.setPreferredSize(new Dimension(600,600));
    subcenterPanel4 = new JPanel();
    subcenterPanel4.setLayout(new BorderLayout());
    subcenterPanel4.setPreferredSize(new Dimension(600,600));
    
    centerPanel.add(subcenterPanel1,"center1");
    centerPanel.add(sectionScroll,"center2");
    centerPanel.add(subcenterPanel3,"center3");
    centerPanel.add(subcenterPanel4,"center4");

    /*メイン処理真中レイアウト*/
    putSection();
    JButton next = new JButton("決定");
    next.setActionCommand("NEXT");
    next.addActionListener(this);
    JButton returnselect = new JButton("再選択");
    returnselect.setActionCommand("RS");
    returnselect.addActionListener(this);
    selectScroll = new JScrollPane(selection);
    selectScroll.setPreferredSize(new Dimension(300,600));
    TitledBorder border4 = new TitledBorder(inborder2,"▼問題候補",TitledBorder.RIGHT,TitledBorder.TOP);

    subselectpanel = new JPanel();
    subselectpanel.setBorder(border4);
    subselectpanel.setPreferredSize(new Dimension(300,600));
    subselectpanel.setLayout(new BorderLayout());
    subselectpanel.add(selectScroll,BorderLayout.CENTER);
    subselectpanel.add(returnselect,BorderLayout.PAGE_END);
    subcenterPanel3.add(subselectpanel,BorderLayout.LINE_START);
    subcenterPanel3.add(rightPanel,BorderLayout.CENTER);

    JPanel endPanel= new JPanel();
    JLabel endLabel = new JLabel("問題作成終了しました。");
    endPanel.add(endLabel);
    subcenterPanel4.add(endPanel,BorderLayout.CENTER);
    
    /*メイン処理右レイアウト*/
    TitledBorder border6 = new TitledBorder(inborder2,"▼出題ソース",TitledBorder.RIGHT,TitledBorder.TOP);
    config = new JButton("設定する");
    config.setActionCommand("CONFIG");
    config.addActionListener(this);
    config.setEnabled(false);
    subsubright1 = new JPanel();
    subsubright1.setBorder(border6);

    subsubright1.setPreferredSize(new Dimension(300,600));
    //subsubright1.setPreferredSize(new Dimension(150,300));
    subsubright1.setLayout(new BorderLayout());
    subsubright1.add(outputScroll,BorderLayout.CENTER);
    subsubright1.add(config,BorderLayout.PAGE_END);
    subrightPanel1.add(subsubright1);
    subsubright2 = new JPanel();
    subsubright2.setPreferredSize(new Dimension(300,400));
    //subsubright2.setPreferredSize(new Dimension(150,200));
    TitledBorder border5 = new TitledBorder(inborder2,"▼問題の設定",TitledBorder.RIGHT,TitledBorder.TOP);
    subrightPanel2.setBorder(border5);
    subsubright2.setLayout(new BoxLayout(subsubright2,BoxLayout.Y_AXIS));
    subrightPanel2.setLayout(new BoxLayout(subrightPanel2,BoxLayout.Y_AXIS));

    /*問題の項目設定画面*/
    subsubright2.setPreferredSize(new Dimension(300,400));
    //subsubright2.setPreferredSize(new Dimension(150,200));//デモ用
    GridBagLayout configlayout = new GridBagLayout();
    subsubright2.setLayout(configlayout);
    subsubrightScroll = new JScrollPane(subsubright2);

    GridBagConstraints configgbc = new GridBagConstraints();
    configgbc.fill = GridBagConstraints.BOTH;

    /*設定エリアの各項目の長さ設定*/
    Dimension minDim1 = new Dimension(175,50);//文字入力欄の最小値
    Dimension minDim2 = new Dimension(50,50);//数字入力欄の最小値
    question = new JTextArea();
    //question.setMinimumSize(minDim);
    questionScroll = new JScrollPane(question);
    questionScroll.setPreferredSize(new Dimension(300,200));
    //questionScroll.setPreferredSize(new Dimension(150,100));
    JTextArea answer = new JTextArea();
    
    
    /*問題文*/
    //sentence = new JTextArea(2,15);//問題文複数行（表示でつぶれる）
    //JScrollPane sentenceScroll = new JScrollPane(sentence);
    sentence = new JTextField(20);
    sentence.setMinimumSize(minDim1);//textfieldの幅の最小値を明示的に決める

    /*プログラムセット（グループ）*/
    program_set = new JTextField(10); 
    program_set.setMinimumSize(minDim1);
    
    /*ID*/
    id = new JTextField(10);
    id.setMinimumSize(minDim2);
    
    /*ヒント*/
    //hints = new JTextArea(2,15);//ヒント複数行（表示でつぶれる）
    //JScrollPane hintsScroll = new JScrollPane(hints);
    hints = new JTextField(20);
    hints.setMinimumSize(minDim1);
    
    /**/
    function = new JTextField(10);
    function.setMinimumSize(minDim2);
    
    /**/
    score = new JTextField(10);
    score.setMinimumSize(minDim2);
    
    /**/
    weight = new JTextField(10);
    weight.setMinimumSize(minDim2);
    
    /**/
    point = new JTextField(10);
    point.setMinimumSize(minDim2);
    
    /*解説*/
    //explanation = new JTextArea(2,15); //解説複数行（表示でつぶれる）
    //JScrollPane explanationScroll = new JScrollPane(explanation);
    explanation = new JTextField(20);
    explanation.setMinimumSize(minDim1);
    
    JPanel[] panel = new JPanel[9];
    GridBagLayout panellayout = new GridBagLayout();
    for(int i=0 ; i<9 ; i++){
      panel[i] = new JPanel();
      panel[i].setLayout(panellayout);
    }
    GridBagConstraints panelgbc = new GridBagConstraints();
    panelgbc.gridx = 0;
    panelgbc.gridy = 0;
    panelgbc.insets = new Insets(10, 0, 10, 0);
    panelgbc.anchor = GridBagConstraints.WEST;
    panelgbc.weightx = 1.0d;
    panelgbc.weighty = 1.0d;

    //設定エリアをパネルに追加
    JLabel[] label = new JLabel[9];
    label[0] = new JLabel("問題文:");
    //panellayout.setConstraints(sentenceScroll,panelgbc);
    //panel[0].add(sentenceScroll);
    panellayout.setConstraints(sentence,panelgbc);//test
    panel[0].add(sentence);//test
    //label[1] = new JLabel("program_set:");
    label[1] = new JLabel("グループ:");
    panellayout.setConstraints(program_set,panelgbc);
    panel[1].add(program_set);
    label[2] = new JLabel("id:");
    panellayout.setConstraints(id,panelgbc);
    panel[2].add(id);
    label[3] = new JLabel("ヒント:");
    //panellayout.setConstraints(hintsScroll,panelgbc);
    //panel[3].add(hintsScroll);
    panellayout.setConstraints(hints,panelgbc);//test
    panel[3].add(hints);//test
    label[4] = new JLabel("function:");
    panellayout.setConstraints(function,panelgbc);
    panel[4].add(function);
    label[5] = new JLabel("score:");
    panellayout.setConstraints(score,panelgbc);
    panel[5].add(score);
    label[6] = new JLabel("weight:");
    panellayout.setConstraints(weight,panelgbc);
    panel[6].add(weight);
    label[7] = new JLabel("point:");
    panellayout.setConstraints(point,panelgbc);
    panel[7].add(point);
    label[8] = new JLabel("解説:");
    //panellayout.setConstraints(explanationScroll,panelgbc);
    //panel[8].add(explanationScroll);
    panellayout.setConstraints(explanation,panelgbc);//test
    panel[8].add(explanation);//test
    
    subrightPanel2.add(questionScroll);
    for(int i=0 ; i<9 ; i++){
      configgbc.gridx = 0;
      configgbc.gridy = i;
      configgbc.weightx = 0.05d;
      configgbc.weighty = 1.0d;
      label[i].setHorizontalAlignment(JLabel.TRAILING);
      configlayout.setConstraints(label[i], configgbc);
      subsubright2.add(label[i]);
      configgbc.gridx = 1;
      configgbc.weightx = 1.95d;
      configlayout.setConstraints(panel[i], configgbc);
      subsubright2.add(panel[i]);
    }
    JButton save = new JButton("適用");
    save.setActionCommand("SAVE");
    save.addActionListener(this);
    configgbc.gridwidth=2;
    configgbc.gridx = 0;
    configgbc.gridy = 9;
    configgbc.weightx = 0;
    configgbc.weighty = 0;
    configlayout.setConstraints(save, configgbc);
    subsubright2.add(save);
    //subrightPanel2.add(subsubright2,BorderLayout.CENTER);
    subrightPanel2.add(subsubrightScroll,BorderLayout.CENTER);
    

    
    frameGBC.insets = new Insets(10, 0, 10, 0);
    frameGBC.gridy=0;
    frameGBC.anchor = GridBagConstraints.NORTH;
    frameLayout.setConstraints(fileinputPanel,frameGBC);
    framePanel.add(fileinputPanel);
    frameGBC.insets = new Insets(0, 0, 0, 0);
    frameGBC.gridy=1;
    frameGBC.anchor = GridBagConstraints.CENTER;
    frameLayout.setConstraints(mainPanel,frameGBC);
    framePanel.add(mainPanel);

    getContentPane().add(framePanel);

    
    addComponentListener(new ComponentAdapter(){  //ウィンドウサイズ変更時の処理
      public void componentResized(ComponentEvent ev) {
        
        w_width = (int)getBounds().getWidth();
        w_height = (int)getBounds().getHeight();
        Dimension mainD = new Dimension(w_width,w_height);
        framePanel.setPreferredSize(mainD);
        framePanel.repaint();
        w_width-=200;
        w_height-=200;
        if(w_width < 0) w_width=200;
        if(w_height < 0) w_width=200;
        mainD = new Dimension(w_width,w_height);
        mainPanel.setPreferredSize(mainD);
        mainPanel.repaint();
        inputScroll.setPreferredSize(new Dimension((int)w_width/3,w_height));
        outputScroll.setPreferredSize(new Dimension((int)w_width/3,w_height-50));
        rightPanel.setPreferredSize(new Dimension((int)w_width/3,w_height));
        subrightPanel1.setPreferredSize(new Dimension((int)w_width/3,w_height));
        subrightPanel2.setPreferredSize(new Dimension((int)w_width/3,w_height));
        subcenterPanel1.setPreferredSize(new Dimension((int)w_width/3*2,w_height));
        sectionScroll.setPreferredSize(new Dimension((int)w_width/3*2,w_height));
        subcenterPanel3.setPreferredSize(new Dimension((int)w_width/3*2,w_height));
        selectScroll.setPreferredSize(new Dimension((int)w_width/3,w_height));
        subselectpanel.setPreferredSize(new Dimension((int)w_width/3,w_height));
        subsubright1.setPreferredSize(new Dimension((int)w_width/3,w_height));
        subsubright2.setPreferredSize(new Dimension((int)w_width/3,(int)w_height*2/3));
        questionScroll.setPreferredSize(new Dimension((int)w_width/3,(int)w_height/3));
        repaint();
      }
    });
  }
  
  //ファイル読み込み部
  public void actionPerformed(ActionEvent e) {//イベント発生
    if (e.getActionCommand() == "LOAD" ) {//ファイルの展開
      QM = new QuestionMap(sectioncount,subsectioncount);
      number = 0;
      fd = new FileDialog(frm , "ファイル読み込み" , FileDialog.LOAD);
      fd.setVisible(true);
      filename = fd.getDirectory()+fd.getFile();
      if(null != filename){
        filead.setText(filename);
      }
     try{
        int data;
        FileReader fr = new FileReader(new File(filename));
        BufferedReader br = new BufferedReader(fr);
        String line;
        inputtext.setText(null);
        while((line = br.readLine()) != null){
          inputtext.append(line+"\n");
        }
        br.close();
        inputtext.setDisabledTextColor(Color.BLACK);
        inputtext.setEnabled(false);
        maincenterLayout.show(centerPanel,"center2");
      }catch(IOException ex){
        inputtext.setText("ファイルを開けませんでした。");
        ex.printStackTrace();
      }
    }else if(e.getActionCommand() == "NEXT"){  //出題方針選択後
      int counter = 0;
      try{
        InputStream source = new FileInputStream(filename);
        ANTLRInputStream input = new ANTLRInputStream(source,"SHIFT-JIS");
        CtreeLexer lexer = new CtreeLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CtreeParser parser = new CtreeParser(tokens);
        String Stree = parser.translation_unit().tree.toStringTree();
        Check ST = new Check(Stree);//XML化
        inp = ST.toXML();
        layout2 = new GridBagLayout();
        selection = new JPanel();
        selection.setLayout(layout2);
        GridBagConstraints gbc = new GridBagConstraints();

        makeBlankChecker();  //問題の作成

        /*問題候補一覧の作成*/
        int sectiontotal=0;
        for(int i=0 ; i<subsectioncount.length ; i++){
          sectiontotal+=subsectioncount[i];
        }
        JLabel[] slabel = new JLabel[sectiontotal];  //selectionlabel
        JLabel[] qlabel = new JLabel[QM.total];  //questionlabel
        JButton[] button = new JButton[QM.total];  //見るボタン
        rbutton = new JCheckBox[QM.total];  //チェックボックス
        int page = 0;
        int sum = 0;
        for(int i=0 ; i<sectioncount ; i++){
          for(int j=0 ; j<subsectioncount[i] ; j++){
            if(QM.qtype[i][j] != 0){
              slabel[page] = new JLabel();
              slabel[page].setText(subsectionTitleString[i][j]);
              gbc.gridx = 0;
              gbc.gridy = page+sum;
              gbc.gridwidth = 3;
              gbc.anchor = GridBagConstraints.WEST;
              layout2.setConstraints(slabel[page],gbc);
              selection.add(slabel[page]);
              gbc.gridwidth = 1;
              gbc.anchor = GridBagConstraints.CENTER;
              page++;
              for(int k=0 ; k<QM.qtype[i][j] ; k++){
                qlabel[sum] = new JLabel();
                qlabel[sum].setText("候補"+(sum+1));
                gbc.gridx = 0;
                gbc.gridy = page+sum;
                layout2.setConstraints(qlabel[sum],gbc);
                selection.add(qlabel[sum]);
                button[sum] = new JButton("見る");
                button[sum].setActionCommand("TRIAL"+sum);
                button[sum].addActionListener(this);
                rbutton[sum]=new JCheckBox("採用");
                gbc.gridx = 1;
                gbc.gridy = sum+page;
                layout2.setConstraints(button[sum],gbc);
                gbc.gridx=2;
                layout2.setConstraints(rbutton[sum],gbc);
                selection.add(button[sum]);
                selection.add(rbutton[sum]);
                sum++;
              }
            }
          }
        }

        JButton out = new JButton("問題の出力");
        out.setActionCommand("OUTPUT");
        out.addActionListener(this);
        gbc.gridx=2;
        gbc.gridy=sum+page+1;
        layout2.setConstraints(out,gbc);
        selection.add(out);
        JScrollPane scroll4 = new JScrollPane(selection);
        selectScroll.setViewportView(selection);
        maincenterLayout.show(centerPanel,"center3");
      }catch(Exception antlrex){
        System.out.println(antlrex);
      }
      
    }else if(e.getActionCommand() == "RS"){
      QM = new QuestionMap(sectioncount,subsectioncount);
      maincenterLayout.show(centerPanel,"center2");
    }else if(e.getActionCommand().matches("TRIAL[0-9]+")){//見るボタン
      number = Integer.parseInt(e.getActionCommand().replaceAll("TRIAL",""));
      StringWriter string = XSLT.convertToSource((Document)QM.questionlist.get(number));//XMLからCのコードに変換
      outputtext.setText(null);
      outputtext.append(string.toString());
      outputtext.setDisabledTextColor(Color.BLACK);
      outputtext.setEnabled(false);
      config.setEnabled(true);
      mainrightLayout.show(rightPanel,"output1");
    }else if(e.getActionCommand() == "CONFIG"){
      question.setText(outputtext.getText());
      sentence.setText((String)QM.sentence.get(number));
      program_set.setText((String)QM.program_set.get(number));
      id.setText((String)QM.id.get(number));
      hints.setText((String)QM.hints.get(number));
      function.setText((String)QM.function.get(number));
      score.setText((String)QM.score.get(number));
      weight.setText((String)QM.weight.get(number));
      point.setText((String)QM.point.get(number));
      explanation.setText((String)QM.explanation.get(number));
      mainrightLayout.next(rightPanel);
    }else if(e.getActionCommand() == "SAVE"){
      QM.sentence.set(number,sentence.getText());
      QM.program_set.set(number,program_set.getText());
      QM.id.set(number,id.getText());
      QM.hints.set(number,hints.getText());
      QM.function.set(number,function.getText());
      QM.score.set(number,score.getText());
      QM.weight.set(number,weight.getText());
      QM.point.set(number,point.getText());
      QM.explanation.set(number,explanation.getText());
      rbutton[number].setSelected(true);
      mainrightLayout.next(rightPanel);
    }else if(e.getActionCommand() == "OUTPUT"){
      /*出力ボタンを押したときの処理*/
      int number = QM.total;
      for(int i=0 ; i<number ; i++){
        if(rbutton[i].isSelected()){
          makeOutputFile(i);
        }
        maincenterLayout.show(centerPanel,"center4");
      }
    }else if(e.getActionCommand() == "N"){
      maincenterLayout.next(centerPanel);
    }
  }
      
  //出力部分（ファイル書き込み、DB書き込み）
  void makeOutputFile(int number){
	
	/*問題DBのあるサーバ・ポート指定*/
	  String host = "emerald.c.oka-pu.ac.jp";
	  int port = 3000;
	  
	//テストデータ
	  //int id= 1;	// id
	  //String group=new String("Sample");	// グループ
	  //String source = new String ("<xml><program_set id=\"sample\"><item id=\"1\"></item></program_set></xml>");

	  
    /*以下出力*/
	/*ファイルへ出力*/
    StringBuffer sb = new StringBuffer();
    sb.append("<?xml version=\"1.0\" encoding=\"shift_JIS\" ?>\n");
    sb.append("<program_set id=\""+(String)QM.program_set.get(number)+"\">\n");
    sb.append("<item id=\""+(String)QM.id.get(number)+"\" type=\"fill_in_the_blank\">");
    sb.append("<question>\n");
    sb.append(convertforXML((String)QM.sentence.get(number))+"<br/><br/>\n");
    sb.append("<pre>\n");
    StringWriter string = XSLT.convertToSource((Document)QM.questionlist.get(number));
    sb.append(convertforXML(string.toString())+"\n");
    sb.append("</pre>\n</question>\n");
    for(int i=0 ; i<((ArrayList)QM.answerlist.get(number)).size() ; i++){
      sb.append("<response id=\""+(i+1)+"\">["+String.valueOf((char)('A'+i))+"]=</response>\n");
    }
    sb.append("<evaluate>");
    sb.append("<function>");
    sb.append((String)QM.function.get(number));
    sb.append("</function>");
    for(int i=0 ; i<((ArrayList)QM.answerlist.get(number)).size() ; i++){
      sb.append("<correct id=\""+(i+1)+"\">");
      ArrayList node=(ArrayList)QM.answerlist.get(number);
      StringWriter string2 = XSLT.convertToSource((Node)node.get(i));
      sb.append(convertforXML(string2.toString()));
      sb.append("</correct>\n");
    }
    sb.append("<score>");
    sb.append((String)QM.score.get(number));
    sb.append("</score>");
    sb.append("<weight/>");
    sb.append("<point>");
    sb.append((String)QM.point.get(number));
    sb.append("</point>");
    sb.append("</evaluate>");
    sb.append("<explanation>");
    sb.append((String)QM.explanation.get(number));
    sb.append("</explanation>");
    sb.append("</item>\n");
    sb.append("</program_set>\n");
    try{  //ファイル名output.xmlで出力
      // Fileオブジェクトの作成
      File file = new File("output"+(String)QM.program_set.get(number)+"_"+(String)QM.id.get(number)+".xml");
      // newFile.txtを新規作成する
      file.createNewFile();
      // Fileオブジェクトを引数にファイル出力ストリームをオープン
      FileWriter fw = new FileWriter(file);
      fw.write(sb.toString());
      fw.close();
      
    }catch(IOException ex){
    	System.out.println("IOエラーが発生しました");
    	}
    try{
    	//DBへの記述（ローカル）
    	//ドライバクラスをロード
    	Class.forName("org.postgresql.Driver");
    	//DBへ接続
    	Connection con = DriverManager.getConnection("jdbc:postgresql:problem_DB","postgres","postgres");
    	//System.out.println("DB conect OK");//DB接続チェック
    	//ステートメントオブジェクトを生成
    	Statement stmt = con.createStatement();
    	//SQL文を作成
    	String sql = "INSERT INTO question VALUES('" + (String)QM.id.get(number) + "','" + (String)QM.program_set.get(number) + "','"+ (String)QM.weight.get(number) +"','" + sb.toString() + "');";
    	//System.out.println(sql);//クエリチェック出力
    	//クエリを実行
    	int result = stmt.executeUpdate(sql);
    	//DBからの切断
    	stmt.close();
    	con.close();
    	//	DBアクセス時の例外処理
      	}catch (Exception e){
      		e.printStackTrace();
      	}	
     
      	//ソケット通信部
    /*try{
        // ソケット接続
  	  System.out.println("Start!!");//接続開始確認用
  	  
        Socket soc=new Socket(host,port);
        DataOutputStream os=new DataOutputStream(soc.getOutputStream());
        DataInputStream is=new DataInputStream(soc.getInputStream());

        // サーバーへ数値を送信
        //os.writeInt(id);		// id を送信（テスト用）
        //os.writeUTF(group);	// group を送信(テスト用）
        //os.writeUTF(source);  // sourceを送信(テスト用）
        
        os.writeUTF((String)QM.id.get(number));//idを送信
        os.writeUTF((String)QM.program_set.get(number));//program_setを送信
        os.writeUTF(sb.toString());//sourceを送信

        // サーバーからの結果を受信
        //double r=is.readDouble();

        // 結果を表示
        System.out.println("End!!");//転送終了

        // ソケットを閉じる
        soc.close();
      }
      //例外処理
      catch(UnknownHostException e){
        System.err.println("Unknown host: "+host);
      }
      catch(IOException e){
        System.err.println("IO error: "+e);
      }*/
    //ソケット通信部終わり
  }
  
  //エスケープ文字の変換
  String convertforXML(String str){
    str = str.replaceAll("&","&amp;");
    str = str.replaceAll("<","&lt;");
    str = str.replaceAll(">","&gt;");
    str = str.replaceAll("\"","&quot;");
    str = str.replaceAll("\'","&apos;");
    
    return str;
  }

  void putSection(){
	try{
		//ルールのチェックボックス生成
		GridBagLayout gblayout = new GridBagLayout();
		subcenterPanel2.setLayout(gblayout);
		GridBagConstraints gbconst = new GridBagConstraints();
		sectiontitle = new JCheckBox[sectioncount];
		subsectionTitle = new JCheckBox[sectioncount][];
		
    	//CREATEBLANK用の定義
    	String[] appoint = {"ALL","SELECT","BOOL"};
    	String[] condit = {"rootonly","self","leftchild","rightchild","conditiononly","statementonly"};
    	//nodenameのTEXTFIELD
    	nodename = new JTextField(10);
    	//appointmentのCOMBOBOX
    	appointcomb = new JComboBox(appoint);
    	appointcomb.setPreferredSize(new Dimension(20, 20));
    	//conditionのCOMBOBOX
    	conditcomb = new JComboBox(condit);
    	conditcomb.setPreferredSize(new Dimension(20,20));
		
		for(int i=0 ; i<sectioncount ; i++){
			sectiontitle[i] = new JCheckBox(sectiontitleString[i]);
			subsectionTitle[i] = new JCheckBox[subsectioncount[i]];
			for(int j=0; j<subsectioncount[i] ; j++){
    	  
				if(subsectionTitleString[i][j].equals("choise")){
    			System.out.println("check OK!!");

    			subsectionTitle[i][j] = new JCheckBox("createblank");
    			cmb_i = i;
    			cmb_j = j;
    			}
    			else{  
					subsectionTitle[i][j] = new JCheckBox(subsectionTitleString[i][j]);
				}
			}
		}
    
    	JPanel[] setSectionLabel = new JPanel[sectioncount*2];
    	int sum=0,loop1,loop2;
    	for(int i=0 ; i<sectioncount ; i++){
    		loop1 = i*2;
    		setSectionLabel[loop1] = new JPanel();
    		gbconst.fill = GridBagConstraints.BOTH;
    		gbconst.gridx=0;
    		gbconst.gridy=i;
    		gbconst.gridwidth = 1;
    		gbconst.gridheight = 1;
    		setSectionLabel[loop1].add(sectiontitle[i]);
    		gblayout.setConstraints(setSectionLabel[loop1],gbconst);
    		subcenterPanel2.add(setSectionLabel[loop1]);
    		setSectionLabel[loop1].setBorder(new LineBorder(Color.black, 2));
    		loop2 = loop1 + 1;
    		setSectionLabel[loop2] = new JPanel();
    		setSectionLabel[loop2].setLayout(new BoxLayout(setSectionLabel[loop2], BoxLayout.Y_AXIS));
    		//リストへサブセクション追加
    		for(int j=0 ; j<subsectioncount[i] ; j++){
    			if(cmb_i == i && cmb_j == j){
    				System.out.println("test001");
    				setSectionLabel[loop2].add(subsectionTitle[i][j]);
    				setSectionLabel[loop2].add(nodename);
    				setSectionLabel[loop2].add(appointcomb);
    				setSectionLabel[loop2].add(conditcomb);
    			}
    			else{
    				setSectionLabel[loop2].add(subsectionTitle[i][j]);
    			}
    		}
      
    		gbconst.gridx=1;
    		gbconst.gridy=i;
    		gblayout.setConstraints(setSectionLabel[loop2],gbconst);
    		subcenterPanel2.add(setSectionLabel[loop2]);
    		setSectionLabel[loop2].setBorder(new LineBorder(Color.black, 2));
    	}
    	
    	JButton next = new JButton("決定");
    	next.setActionCommand("NEXT");
    	next.addActionListener(this);
    	gbconst.gridx=1;
    	gbconst.gridy=sectioncount+1;
    	gblayout.setConstraints(next,gbconst);
    	subcenterPanel2.add(next);
    } catch(Exception e) {
    	System.out.println("例外：" + e);
    }
  }	
  
  /*以下出題方針(問題作成)*/
  void makeBlankChecker(){
	try{
    Dom domtree = new Dom(inp);
    //入出力関数
    /*1.1入力関数の引数が分かっているか*/
    if(subsectionTitle[0][0].isSelected() || sectiontitle[0].isSelected()){
    	/*有安テスト*/
        if(domtree.createblank("EXPRESSION","FORMAT",Dom.SELECT,Dom.self,Dom.exist,"scanf")){
            QM.qtype[0][0]++;
        }	
        if(domtree.createblank("scanf",Dom.SELECT,Dom.self)){
        	QM.qtype[0][0]++;
        }
    	//if(domtree.createblank("UNARYPLUS",Dom.SELECT,Dom.self)){
    	//	QM.qtype[0][0]++;
    	//}
    	else {
    		System.out.println("Erorr:Can't create question 1.1");
    	}
    }
    /*1.2出力関数の引数が分かっているか*/
    //定数変数データ型
    if(subsectionTitle[0][1].isSelected() || sectiontitle[0].isSelected()){/*動いてない*/
    	//System.out.println("1.2="+domtree.createblank("ARGUMENT",Dom.SELECT,Dom.rightchild));
    	/*if(domtree.createblank("ARGUMENT",Dom.SELECT,Dom.rightchild)){
    		System.out.println("OK!!!!!");
    		QM.qtype[0][1]++;
    	}
    	else {
    		System.out.println("Erorr:Can't create question 1.2");
    	}*/
    	if(domtree.createblank("printf",Dom.SELECT,Dom.self)){
    		QM.qtype[0][1]++;
    	}
    	if(domtree.createblank("EXPRESSION","FORMAT",Dom.SELECT,Dom.self,Dom.exist,"printf")){
    		QM.qtype[0][1]++;
    	}
    }
    /*1.3関数使用時のヘッダが分かっているか*/
    //printf関数
    if(subsectionTitle[0][2].isSelected() || sectiontitle[0].isSelected()){

    }
    //2簡単な入力と式
    /*2.1変数がうまく定義出来ているか
     *scanfとアドレス演算子*/
    if(subsectionTitle[1][0].isSelected() || sectiontitle[1].isSelected()){
      if(domtree.createblank("AMP",Dom.ALL)){
        QM.qtype[1][0]++;
      }
      if(domtree.createblank("AMP",Dom.SELECT,Dom.rootonly)){
        QM.qtype[1][0]++;
      }
    }
    /*2.2変数の使い方が適切か
     * 式*/
    if(subsectionTitle[1][1].isSelected() || sectiontitle[1].isSelected()){
    	if(domtree.createblank("DECLARATION",Dom.SELECT,Dom.leftchild)){
    		System.out.println("create2.2-1");
    		QM.qtype[1][1]++;
    	}
    	if(domtree.createblank("DECLARATION",Dom.SELECT,Dom.rightchild)){
    		System.out.println("create2.2-1");
    		QM.qtype[1][1]++;
    	}
    }
    /*2.3演算子の使い方を理解しているか
     * 型変換*/
    if(subsectionTitle[1][2].isSelected() || sectiontitle[1].isSelected()){
        if(domtree.createblank("ASSIGNMENT",Dom.SELECT,Dom.rootonly)){
        	QM.qtype[1][2]++;
          }
          if(domtree.createblank("ASSIGNMENT",Dom.SELECT,Dom.leftchild)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("ASSIGNMENT",Dom.SELECT,Dom.rightchild)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("ASSIGNMENT",Dom.SELECT,Dom.self)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("PLUSE",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("PLUSE",Dom.SELECT,Dom.self)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("MINUSE",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("MINUSE",Dom.SELECT,Dom.self)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("RESTE",Dom.SELECT,Dom.rootonly)){
   	         QM.qtype[1][2]++;
          }
          if(domtree.createblank("RESTE",Dom.SELECT,Dom.self)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("DIVE",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("DIVE",Dom.SELECT,Dom.self)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("MULTIE",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("MULTIE",Dom.SELECT,Dom.self)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("EQUAL",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("NOTE",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("FEWER",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("GREATER",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("FORE",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
          if(domtree.createblank("BORE",Dom.SELECT,Dom.rootonly)){
        	  QM.qtype[1][2]++;
          }
    }
    /*2.4フォーマット指定しが分かっているか
     * 代入演算子*/
    if(subsectionTitle[1][3].isSelected() || sectiontitle[1].isSelected()){
    	if(domtree.createblank("CAST",Dom.SELECT,Dom.leftchild)){
    		QM.qtype[1][3]++;
    		System.out.println("create2.4-1");
    	}

    }
    /*2.5インクリメント、デクリメント演算子
     * インクリメント、デクリメント演算子*/
    if(subsectionTitle[1][4].isSelected() || sectiontitle[1].isSelected()){
      if(domtree.createblank("UNARYPLUS",Dom.SELECT,Dom.rootonly)){
        QM.qtype[1][4]++;
      }
      if(domtree.createblank("UNARYMINUS",Dom.SELECT,Dom.rootonly)){
        QM.qtype[1][4]++;
      }
      if(domtree.createblank("POSTFIXPLUS",Dom.SELECT,Dom.rootonly)){
        QM.qtype[1][4]++;
      }
      if(domtree.createblank("POSTFIXMINUS",Dom.SELECT,Dom.rootonly)){
        QM.qtype[1][4]++;
      }
    }
    //制御構文
    /*3.1必要となる引数が分かっているか
     * 比較演算子と等価演算子*/
    if(subsectionTitle[2][0].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("EQUAL",Dom.SELECT,Dom.self)){
        QM.qtype[2][0]++;
      }
      if(domtree.createblank("NOTE",Dom.SELECT,Dom.self)){
        QM.qtype[2][0]++;
      }
      if(domtree.createblank("FEWER",Dom.SELECT,Dom.self)){
        QM.qtype[2][0]++;
      }
      if(domtree.createblank("GREATER",Dom.SELECT,Dom.self)){
        QM.qtype[2][0]++;
      }
      if(domtree.createblank("FORE",Dom.SELECT,Dom.self)){
        QM.qtype[2][0]++;
      }
      if(domtree.createblank("BORE",Dom.SELECT,Dom.self)){
        QM.qtype[2][0]++;
      }
      //2.5インクリメントと同じ問題作成ルール
      if(domtree.createblank("UNARYPLUS",Dom.SELECT,Dom.self)){
          QM.qtype[2][0]++;
      }
      if(domtree.createblank("UNARYMINUS",Dom.SELECT,Dom.self)){
    	  QM.qtype[2][0]++;
      }
      if(domtree.createblank("POSTFIXPLUS",Dom.SELECT,Dom.self)){
    	  QM.qtype[2][0]++;
      }
      if(domtree.createblank("POSTFIXMINUS",Dom.SELECT,Dom.self)){
    	  QM.qtype[2][0]++;
      }
      //ここまで
    }
    /*3.2if文が分かっているか
     * if文*/
    if(subsectionTitle[2][1].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("IF",Dom.SELECT,Dom.rootonly)){
        QM.qtype[2][1]++;
      }
      if(domtree.createblank("IF",Dom.SELECT,Dom.self)){
        QM.qtype[2][1]++;
      }
      if(domtree.createblank("IF",Dom.ALL)){
        QM.qtype[2][1]++;
      }
      if(domtree.createblank("IF",Dom.SELECT,Dom.conditiononly)){
        QM.qtype[2][1]++;
      }
      if(domtree.createblank("IF",Dom.SELECT,Dom.statementonly)){
        QM.qtype[2][1]++;
      }
      if(domtree.createblank("ELSE",Dom.ALL)){
        QM.qtype[2][1]++;
      }
      if(domtree.createblank("ELSE",Dom.SELECT,Dom.statementonly)){
        QM.qtype[2][1]++;
      }
    }
    /*3.3while文の処理の流れを理解しているか*/
    if(subsectionTitle[2][2].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("WHILE",Dom.SELECT,Dom.rootonly)){
    	System.out.println("Output1");
        QM.qtype[2][2]++;
      }
      if(domtree.createblank("WHILE",Dom.SELECT,Dom.self)){
    	System.out.println("Output2");
        QM.qtype[2][2]++;
      }
      if(domtree.createblank("WHILE",Dom.ALL)){
    	System.out.println("Output3");
        QM.qtype[2][2]++;
      }
      if(domtree.createblank("WHILE",Dom.SELECT,Dom.statementonly)){
    	System.out.println("Output4");
    	QM.qtype[2][2]++;
      }
      if(domtree.createblank("POSTFIXPLUS",Dom.SELECT,Dom.self)){
    	  System.out.println("Output5");
    	  QM.qtype[2][2]++;
      }
      if(domtree.createblank("UNARYPLUS",Dom.SELECT,Dom.self)){/*動いてない？？*/
    	System.out.println("Output6");
    	QM.qtype[2][2]++;
      }
     }
    /*3.4for文の処理の流れを理解しているか*/
    if(subsectionTitle[2][3].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("FOR",Dom.SELECT,Dom.rootonly)){
    	QM.qtype[2][3]++;
      }
      if(domtree.createblank("FOR",Dom.SELECT,Dom.self)){
        QM.qtype[2][3]++;
      }
      if(domtree.createblank("FOR",Dom.ALL)){
        QM.qtype[2][3]++;
      }
      if(domtree.createblank("FOR",Dom.SELECT,Dom.statementonly)){
        QM.qtype[2][3]++;
      }
    }
    /*3.5do-while文の処理の流れを理解しているか*/
    if(subsectionTitle[2][4].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("DOWHILE",Dom.SELECT,Dom.rootonly)){
        QM.qtype[2][4]++;
      }
      if(domtree.createblank("DOWHILE",Dom.SELECT,Dom.self)){
        QM.qtype[2][4]++;
      }
      if(domtree.createblank("DOWHILE",Dom.ALL)){
        QM.qtype[2][4]++;
      }
      if(domtree.createblank("DOWHILE",Dom.SELECT,Dom.statementonly)){
        QM.qtype[2][4]++;
      }
    }
    /*3.6switchの処理の流れを理解しているか*/
    if(subsectionTitle[2][5].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("SWITCH",Dom.SELECT,Dom.rootonly)){
        QM.qtype[2][5]++;
      }
      if(domtree.createblank("SWITCH",Dom.SELECT,Dom.self)){
        QM.qtype[2][5]++;
      }
      if(domtree.createblank("SWITCH",Dom.ALL)){
        QM.qtype[2][5]++;
      }
      if(domtree.createblank("SWITCH",Dom.SELECT,Dom.statementonly)){
        QM.qtype[2][5]++;
      }
    }
    //4配列
    /*4.1配列の定義の仕方を理解しているか
     * 配列と配列の初期化*/
    if(subsectionTitle[3][0].isSelected() || sectiontitle[3].isSelected()){
      if(domtree.createblank("DECLARATOR","ARRAY",Dom.ALL,Dom.haschild)){
        QM.qtype[3][0]++;
      }
      if(domtree.createblank("EXPRESSION","ARRAY",Dom.ALL,Dom.haschild)){
        QM.qtype[3][0]++;
      }
      if(domtree.createblank("FOR","PARENTHESIS",Dom.ALL,Dom.exist,"ARRAY")){
        QM.qtype[3][0]++;
      }
      if(domtree.createblank("ARGUMENTS","FORMAT",Dom.ALL,Dom.exist,"ARRAY")){
        QM.qtype[3][0]++;
      }
    }
    /*4.2配列要素へのアクセスの仕方を理解しているか
     * 文字型配列*/
    if(subsectionTitle[3][1].isSelected() || sectiontitle[3].isSelected()){
      if(domtree.createblank("DECLARATOR","ARRAY",Dom.ALL,Dom.haschild)){
        QM.qtype[3][1]++;
      }
      if(domtree.createblank("DECLARATION","TYPE",Dom.ALL,Dom.exist,"ARRAY")){
        QM.qtype[3][1]++;
      }
      //if(domtree.createblank("DECLARATOR","CONSTANT",Dom.ALL,Dom.exist,"\'\\0\'"))
      //  QM.qtype[3][1]++;
      if(domtree.createblank("ARGUMENTS","FORMAT",Dom.ALL,Dom.exist,"%s")){
        QM.qtype[3][1]++;
      }
    }
    /*4.3他次元配列の定義の仕方を理解しているか
     * 多次元配列*/
    if(subsectionTitle[3][2].isSelected() || sectiontitle[3].isSelected()){
    }
    //5関数
    /*5.1関数の定義を理解しているか*/
    if(subsectionTitle[4][0].isSelected() || sectiontitle[4].isSelected()){
    }
    /*5.2関数呼び出しが出来るか*/
    if(subsectionTitle[4][1].isSelected() || sectiontitle[4].isSelected()){
    }
    /*5.3関数の戻り値が分かっているか*/
    if(subsectionTitle[4][2].isSelected() || sectiontitle[4].isSelected()){
    }
    /*5.4必要なヘッダが理解できているか
     * プロトタイプ宣言*/
    if(subsectionTitle[4][3].isSelected() || sectiontitle[4].isSelected()){
    	//if(domtree.createblank("IF",Dom.ALL)){
    	//	QM.qtype[4][3]++;
    	//}
    }
    /*5.5必要な関数が理解できているか
     * ライブラリ*/
    if(subsectionTitle[4][4].isSelected() || sectiontitle[4].isSelected()){

    	//if(domtree.createblank("IF",Dom.SELECT,Dom.rootonly)){
        //    QM.qtype[4][4]++;
    	//}
    }
    /*5.6記憶クラスとスコープ*/
    if(subsectionTitle[4][5].isSelected() || sectiontitle[4].isSelected()){
    	if(domtree.createblank("ARGUMENTS","FORMAT",Dom.ALL,Dom.exist,"%s")){
            QM.qtype[4][5]++;
    	}
    }
    /*6自作ルール1*/
    if(subsectionTitle[5][0].isSelected() || sectiontitle[5].isSelected()){
    	
    	String n_name;
    	//nodenameの中身を大文字に変換
    	n_name = nodename.getText().toUpperCase();
    	
    	//createblank(nodename,ALL)
    	if(appointcomb.getSelectedItem()=="ALL"){
    		/*System.out.println(nodename.getText());
    		System.out.println(domtree.createblank(n_name,Dom.ALL)); //デバグ用出力　*/
    		//if(domtree.createblank(nodename.getText().toUpperCase(),Dom.ALL)){
    		if(domtree.createblank(n_name,Dom.ALL)){
    			QM.qtype[5][0]++;
    		}
    	}
    	//createblank(nodename,SELECT,condition)
    	else if(appointcomb.getSelectedItem()=="SELECT"){
    		
    		if(conditcomb.getSelectedItem()=="rootonly"){/*動きがあやしい（一部分消える）*/
        		//System.out.println(domtree.createblank(n_name,Dom.SELECT,Dom.rootonly));//デバグ用
    			//if(domtree.createblank(nodename.getText(),Dom.SELECT,Dom.rootonly)){
        		if(domtree.createblank(n_name,Dom.SELECT,Dom.rootonly)){
    				QM.qtype[5][0]++;
    			}
    		
    		}else if (conditcomb.getSelectedItem()=="self"){
    			//System.out.println(domtree.createblank(n_name,Dom.SELECT,Dom.self));//デバグ用
    			//if(domtree.createblank(nodename.getText(),Dom.SELECT,Dom.self))
    			if(domtree.createblank(n_name,Dom.SELECT,Dom.self)){
    				QM.qtype[5][0]++;
    			}
    		}else if (conditcomb.getSelectedItem()=="leftchild"){/*動きがあやしい（一部分消える）*/
    			//System.out.println(domtree.createblank(n_name,Dom.SELECT,Dom.leftchild));//デバグ用
    			//if(domtree.createblank(nodename.getText(),Dom.SELECT,Dom.leftchild)){
    			if(domtree.createblank(n_name,Dom.SELECT,Dom.leftchild)){
    				QM.qtype[5][0]++;
    			}
    		}else if (conditcomb.getSelectedItem()=="rightchild"){/*動きがあやしい*/
    			//System.out.println(domtree.createblank(n_name,Dom.SELECT,Dom.rightchild));//デバグ用
    			//if(domtree.createblank(nodename.getText(),Dom.SELECT,Dom.rightchild)){
    			if(domtree.createblank(n_name,Dom.SELECT,Dom.rightchild)){
    				QM.qtype[5][0]++;
    			}
    		}else if (conditcomb.getSelectedItem()=="conditiononly"){
    			//System.out.println(domtree.createblank(n_name,Dom.SELECT,Dom.conditiononly));//デバグ用
    			//if(domtree.createblank(nodename.getText(),Dom.SELECT,Dom.conditiononly)){
    			if(domtree.createblank(n_name,Dom.SELECT,Dom.conditiononly)){
    				QM.qtype[5][0]++;
    			}
    		}else if (conditcomb.getSelectedItem()=="statementonly"){/*動きが変*/
    			//System.out.println(domtree.createblank(n_name,Dom.SELECT,Dom.statementonly));//デバグ用
    			//if(domtree.createblank(nodename.getText(),Dom.SELECT,Dom.statementonly)){
    			if(domtree.createblank(n_name,Dom.SELECT,Dom.statementonly)){
    				QM.qtype[5][0]++;
    			}
    		}
    	}
    	//createblank(nodename,BOOL,condition)
    	else {
    		if(conditcomb.getSelectedItem()=="rootonly"){
    			if(domtree.createblank(nodename.getText(),Dom.BOOL,Dom.rootonly)){
    				System.out.println("RUN!!");
    				QM.qtype[5][0]++;
    			}
    		}else if (conditcomb.getSelectedItem()=="self"){
    			if(domtree.createblank(nodename.getText(),Dom.BOOL,Dom.self))
    				QM.qtype[5][0]++;
    		}else if (conditcomb.getSelectedItem()=="leftchild"){
    			if(domtree.createblank(nodename.getText(),Dom.BOOL,Dom.leftchild))
    				QM.qtype[5][0]++;
    		}else if (conditcomb.getSelectedItem()=="rightchild"){
    			if(domtree.createblank(nodename.getText(),Dom.BOOL,Dom.rightchild))
    				QM.qtype[5][0]++;
    		}else if (conditcomb.getSelectedItem()=="conditiononly"){
    			if(domtree.createblank(nodename.getText(),Dom.BOOL,Dom.conditiononly))
    				QM.qtype[5][0]++;
    		}else if (conditcomb.getSelectedItem()=="statementonly"){
    			if(domtree.createblank(nodename.getText(),Dom.BOOL,Dom.statementonly))
    				QM.qtype[5][0]++;
    		}
    	}
    	
    }
    /*自作ルール2
    if(subsectionTitle[5][1].isSelected() || sectiontitle[5].isSelected()){
    }*/
    } catch(Exception e) {
    	System.out.println("例外処理：" + e);
    }
	}

}