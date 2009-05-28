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

class Make extends JFrame implements ActionListener {

  
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
  JScrollPane inputScroll;
  JTextArea outputtext;
  JScrollPane outputScroll;
  JPanel centerPanel;
  JPanel rightPanel;

  JPanel subcenterPanel2;
  JScrollPane sectionScroll;
  JPanel subcenterPanel3;
  JScrollPane selectScroll;

  JButton config;
  JTextArea question;
  JTextArea sentence;
  JTextField program_set;
  JTextField id;
  JTextField hints;
  JTextField function;
  JTextField score;
  JTextField weight;
  JTextField point;
  JTextField explanation;
  
  JCheckBox[] rbutton;
  JCheckBox[] sectiontitle;
  JCheckBox[][] subsectionTitle;

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
    win.setBounds(10,10,1100,800);
    win.setTitle("穴埋め問題作成");
    win.setVisible(true);
  }

  public Make() {
    

    LineBorder inborder = new LineBorder(Color.gray,2);
    
    GridBagConstraints frameGBC = new GridBagConstraints();
    frameLayout = new GridBagLayout();
    framePanel = new JPanel();
    framePanel.setLayout(frameLayout);
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
      //String line;
      //while((line = br.readLine()) != null){
      sectioncount = Integer.parseInt(br.readLine());
      subsectioncount = new int[sectioncount];
      sectiontitleString = new String[sectioncount];
      subsectionTitleString = new String[sectioncount][];
      for(int i=0 ; i<sectioncount ; i++){
        subsectioncount[i] = Integer.parseInt(br.readLine());
        sectiontitleString[i] = br.readLine();
        subsectionTitleString[i] = new String[subsectioncount[i]];
        for(int j=0 ; j<subsectioncount[i] ; j++){
          subsectionTitleString[i][j]=br.readLine();
        }
      }
      //}

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
    //JButton Button = new JButton("次");
    //Button.setActionCommand("N");
    //Button.addActionListener(this);
    fileinputPanel.add(filead);
    fileinputPanel.add(fileButton);
    //fileinputPanel.add(Button);
    fileinputPanel.setPreferredSize(new Dimension(450,60));
    TitledBorder border1 = new TitledBorder(inborder,"ソースファイル入力",TitledBorder.RIGHT,TitledBorder.TOP);
    fileinputPanel.setBorder(border1);
    filename = new String();

    /*メイン処理レイアウト*/
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
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
    //TitledBorder border3 = new TitledBorder(inborder2,"▼出題ソース",TitledBorder.RIGHT,TitledBorder.TOP);
    //outputScroll.setBorder(border3);

    /*メイン処理右*/
    JPanel subrightPanel1 = new JPanel();
    subrightPanel1.setPreferredSize(new Dimension(300,600));
    JPanel subrightPanel2 = new JPanel();
    subrightPanel2.setPreferredSize(new Dimension(300,600));
    rightPanel.add(subrightPanel1,"output1");
    rightPanel.add(subrightPanel2,"output2");

    /*メイン処理真ん中*/
    JPanel subcenterPanel1 = new JPanel();
    subcenterPanel1.setPreferredSize(new Dimension(600,600));
    subcenterPanel2 = new JPanel();
    //subcenterPanel2.setPreferredSize(new Dimension(600,400));
    sectionScroll = new JScrollPane(subcenterPanel2);
    sectionScroll.setPreferredSize(new Dimension(600,600));
    //JPanel subcenterPanel3 = new JPanel();
    subcenterPanel3 = new JPanel();
    subcenterPanel3.setLayout(new BorderLayout());
    subcenterPanel3.setPreferredSize(new Dimension(600,600));
    centerPanel.add(subcenterPanel1,"center1");
    centerPanel.add(sectionScroll,"center2");
    centerPanel.add(subcenterPanel3,"center3");

    /*メイン処理真中レイアウト*/
    putSection();
    //subcenterPanel2.add(sectionScroll);
    JButton next = new JButton("決定");
    next.setActionCommand("NEXT");
    next.addActionListener(this);
    //JScrollPane selectScroll = new JScrollPane(selection);
    selectScroll = new JScrollPane(selection);
    selectScroll.setPreferredSize(new Dimension(300,600));
    TitledBorder border4 = new TitledBorder(inborder2,"▼問題候補",TitledBorder.RIGHT,TitledBorder.TOP);
    selectScroll.setBorder(border4);
    subcenterPanel3.add(selectScroll,BorderLayout.LINE_START);
    subcenterPanel3.add(rightPanel,BorderLayout.CENTER);

    /*メイン処理右レイアウト*/
    TitledBorder border6 = new TitledBorder(inborder2,"▼出題ソース",TitledBorder.RIGHT,TitledBorder.TOP);
    //subsubright1.setBorder(border6);
    config = new JButton("設定する");
    config.setActionCommand("CONFIG");
    config.addActionListener(this);
    config.setEnabled(false);
    JPanel subsubright1 = new JPanel();
        subsubright1.setBorder(border6);

    subsubright1.setPreferredSize(new Dimension(300,600));
    subsubright1.setLayout(new BorderLayout());
    subsubright1.add(outputScroll,BorderLayout.CENTER);
    subsubright1.add(config,BorderLayout.PAGE_END);
    subrightPanel1.add(subsubright1);
    //subrightPanel1.add(outputScroll,BorderLayout.CENTER);
    //subrightPanel1.add(config,BorderLayout.PAGE_END);
    JPanel subsubright2 = new JPanel();
    subsubright2.setPreferredSize(new Dimension(300,400));
    TitledBorder border5 = new TitledBorder(inborder2,"▼問題の設定",TitledBorder.RIGHT,TitledBorder.TOP);
    subrightPanel2.setBorder(border5);
    subsubright2.setLayout(new BoxLayout(subsubright2,BoxLayout.Y_AXIS));
    //outputScroll.setPreferredSize(new Dimension(300,200));
    subrightPanel2.setLayout(new BoxLayout(subrightPanel2,BoxLayout.Y_AXIS));
    //subrightPanel2.add(outputScroll);
    //subrightPanel2.add(subsubright2);

    /*設定画面*/
    question = new JTextArea();
    JScrollPane questionScroll = new JScrollPane(question);
    questionScroll.setPreferredSize(new Dimension(300,200));
    JTextArea answer = new JTextArea();
    sentence = new JTextArea(2,20);
    program_set = new JTextField(10); 
    id = new JTextField(10);
    hints = new JTextField(10);
    function = new JTextField(10);
    score = new JTextField(10);
    weight = new JTextField(10);
    point = new JTextField(10);
    explanation = new JTextField(10);
    JPanel[] panel = new JPanel[9];
    for(int i=0 ; i<9 ; i++){
      panel[i] = new JPanel();
    }
    JLabel[] label = new JLabel[9];
    label[0] = new JLabel("問題文");
    //panel[0].setLayout(new BorderLayout());
    panel[0].add(label[0],BorderLayout.PAGE_START);
    panel[0].add(sentence,BorderLayout.CENTER);
    label[1] = new JLabel("program_set:");
    panel[1].add(label[1]);
    panel[1].add(program_set);
    label[2] = new JLabel("id:");
    panel[2].add(label[2]);
    panel[2].add(id);
    label[3] = new JLabel("ヒント:");
    panel[3].add(label[3]);
    panel[3].add(hints);
    label[4] = new JLabel("function:");
    panel[4].add(label[4]);
    panel[4].add(function);
    label[5] = new JLabel("score:");
    panel[5].add(label[5]);
    panel[5].add(score);
    label[6] = new JLabel("weight:");
    panel[6].add(label[6]);
    panel[6].add(weight);
    label[7] = new JLabel("point:");
    panel[7].add(label[7]);
    panel[7].add(point);
    label[8] = new JLabel("解説:");
    panel[8].add(label[8]);
    panel[8].add(explanation);
    subrightPanel2.add(questionScroll);
    for(int i=0 ; i<9 ; i++){
      subsubright2.add(panel[i]);
    }
    JButton save = new JButton("適用");
    save.setActionCommand("SAVE");
    save.addActionListener(this);
    subsubright2.add(save);
    subrightPanel2.add(subsubright2);

    

    
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
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == "LOAD" ) {
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
        //layout.next(mainpanel);
      }catch(IOException ex){
        inputtext.setText("ファイルを開けませんでした。");
        ex.printStackTrace();
      }
    }else if(e.getActionCommand() == "NEXT"){
      int counter = 0;
      try{
        InputStream source = new FileInputStream(filename);
        ANTLRInputStream input = new ANTLRInputStream(source,"SHIFT-JIS");
        CtreeLexer lexer = new CtreeLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CtreeParser parser = new CtreeParser(tokens);
        String Stree = parser.translation_unit().tree.toStringTree();
        Check ST = new Check(Stree);
        inp = ST.toXML();
        layout2 = new GridBagLayout();
        selection = new JPanel();
        selection.setLayout(layout2);
        GridBagConstraints gbc = new GridBagConstraints();

        makeBlankChecker();
      //Dom domtree = new Dom(inp);
      //domtree.createQuestion();
        //System.out.println("error?");
        JLabel[] slabel = new JLabel[sectioncount];  //selectionlabel
        JLabel[] qlabel = new JLabel[QM.total];  //questionlabel
        JButton[] button = new JButton[QM.total];  //見るボタン
        rbutton = new JCheckBox[QM.total];  //チェックボックス
        //System.out.println(QM.total);
        //int i=0;
        int page = 0;
        int sum = 0;
        for(int i=0 ; i<sectioncount ; i++){
          for(int j=0 ; j<subsectioncount[i] ; j++){
            if(QM.qtype[i][j] != 0){
              slabel[page] = new JLabel();
              slabel[page].setText(subsectionTitleString[i][j]);
              gbc.gridx = 0;
              gbc.gridy = page+sum;
              layout2.setConstraints(slabel[page],gbc);
              selection.add(slabel[page]);
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
        /*int sum=0;
        int ccount=0;
        int sectionc=0;
        for(int i=0 ; i<sectioncount ; i++){
          for(int j=0 ; j<subsectioncount[i] ; j++){
            if(QM.qtype[i][j] != 0){
              slabel[sectionc] = new JLabel();
              slabel[sectionc].setText(subsectionTitleString[i][j]);
              gbc.gridx=0;
              gbc.gridy=sectionc+sum;
              layout2.setConstraints(slabel[sectionc], gbc);
              selection.add(slabel[sectionc]);
              sectionc++;
              j++;
              for(int i=0 ; i<ccount ; i++){
            //System.out.println("!"+sum);
            qlabel[sum] = new JLabel();
            qlabel[sum].setText("候補"+(i+1));
            gbc.gridx=0;
            gbc.gridy=sum+sectionc;
            layout2.setConstraints(qlabel[sum], gbc);
            selection.add(qlabel[sum]);
            button[sum] = new JButton("見る");
            button[sum].setActionCommand("TRIAL"+(counter++));
            button[sum].addActionListener(this);
            rbutton[sum] = new JCheckBox("採用");
            gbc.gridx=1;
            gbc.gridy=sum+sectionc;
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
        for(int j=0 ; j<4 ;){
          ccount=0;
          switch(j){
          case 0:
            //System.out.println("printf:"+QM.printf);
            if(QM.printf != 0){
              slabel[sectionc] = new JLabel();
              slabel[sectionc].setText("printf");
              ccount=QM.printf;
              break;
            }
            j++;
          case 1:
            //System.out.println("format:"+QM.format);
            if(QM.format != 0){
              slabel[sectionc] = new JLabel();
              slabel[sectionc].setText("変換指定子");
              ccount=QM.format;
              break;
            }
            j++;
          case 2:
            //System.out.println("scanf:"+QM.scanf);
            if(QM.scanf != 0){
              slabel[sectionc] = new JLabel();
              slabel[sectionc].setText("scanf");
              ccount=QM.scanf;
              break;
            }
            j++;
          case 3:
            //System.out.println("adress:"+QM.adress);
            if(QM.adress != 0){
              slabel[sectionc] = new JLabel();
              slabel[sectionc].setText("アドレス演算子");
              ccount=QM.adress;
              break;
            }
            j++;
          }
          if(j<4){
            gbc.gridx=0;
            gbc.gridy=sectionc+sum;
            layout2.setConstraints(slabel[sectionc], gbc);
            selection.add(slabel[sectionc]);
            sectionc++;
            j++;
          }
          for(int i=0 ; i<ccount ; i++){
            //System.out.println("!"+sum);
            qlabel[sum] = new JLabel();
            qlabel[sum].setText("候補"+(i+1));
            gbc.gridx=0;
            gbc.gridy=sum+sectionc;
            layout2.setConstraints(qlabel[sum], gbc);
            selection.add(qlabel[sum]);
            button[sum] = new JButton("見る");
            button[sum].setActionCommand("TRIAL"+(counter++));
            button[sum].addActionListener(this);
            rbutton[sum] = new JCheckBox("採用");
            gbc.gridx=1;
            gbc.gridy=sum+sectionc;
            layout2.setConstraints(button[sum],gbc);
            gbc.gridx=2;
            layout2.setConstraints(rbutton[sum],gbc);
            selection.add(button[sum]);
            selection.add(rbutton[sum]);
            sum++;

          }

      }*/

        JButton out = new JButton("問題の出力");
        out.setActionCommand("OUTPUT");
        out.addActionListener(this);
        gbc.gridx=2;
        gbc.gridy=sum+page+1;
        layout2.setConstraints(out,gbc);
        selection.add(out);
        JScrollPane scroll4 = new JScrollPane(selection);
        //subcenterPanel3.add(scroll4,BorderLayout.LINE_START);
        //panel3.add(selection ,BorderLayout.CENTER);
        //panel3.add(scroll3 ,BorderLayout.LINE_END);
        //getContentPane().add(scroll4);
        selectScroll.setViewportView(selection);
        //selectScroll = scroll4;
        maincenterLayout.show(centerPanel,"center3");
        //layout.next(mainpanel);
      }catch(Exception antlrex){
        System.out.println(antlrex);
      }
      
    }else if(e.getActionCommand().matches("TRIAL[0-9]+")){//見るボタン
      //ST.toXML();
      number = Integer.parseInt(e.getActionCommand().replaceAll("TRIAL",""));
      //Dom domtree = new Dom(inp);
      //domtree.createQuestion();
      //System.out.println(QM.questionlist.get(number));
      StringWriter string = XSLT.convertToSource((Document)QM.questionlist.get(number));
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
      int number = QM.total;
      System.out.println(number);
      for(int i=0 ; i<number ; i++){
        if(rbutton[i].isSelected())
          makeOutputFile(i);
      }
    }else if(e.getActionCommand() == "N"){
      maincenterLayout.next(centerPanel);
    }
  }

  void makeOutputFile(int number){
    /*以下出力*/
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
      sb.append("<response id=\""+(i+1)+"\">=["+String.valueOf((char)('A'+i))+"]</response>\n");
    }
    sb.append("<evaluate>");
    sb.append("<function>");
    sb.append((String)QM.function.get(number));
    sb.append("</function>");
    for(int i=0 ; i<((ArrayList)QM.answerlist.get(number)).size() ; i++){
      sb.append("<correct id=\""+(i+1)+"\">");
      ArrayList node=(ArrayList)QM.answerlist.get(number);
      //System.out.println(node[i]);
      StringWriter string2 = XSLT.convertToSource((Node)node.get(i));
      sb.append(convertforXML(string2.toString()));
      //System.out.println(string2.toString());
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
      //layout.next(mainpanel);
    }catch(IOException ex){
      System.out.println("IOエラーが発生しました");
    }
  }
  String convertforXML(String str){
    str = str.replaceAll("&","&amp;");
    str = str.replaceAll("<","&lt;");
    str = str.replaceAll(">","&gt;");
    
    return str;
  }

  void putSection(){
    //int section=5;
    //int[] subsection={3,5,6,3,6};
    //int[][] subcategory= new int[section][];
    
    //JPanel subpanel = new JPanel();
    GridBagLayout gblayout = new GridBagLayout();
    subcenterPanel2.setLayout(gblayout);
    GridBagConstraints gbconst = new GridBagConstraints();
    sectiontitle = new JCheckBox[sectioncount];
    subsectionTitle = new JCheckBox[sectioncount][];
    for(int i=0 ; i<sectioncount ; i++){
      sectiontitle[i] = new JCheckBox(sectiontitleString[i]);
      subsectionTitle[i] = new JCheckBox[subsectioncount[i]];
      for(int j=0; j<subsectioncount[i] ; j++){
        subsectionTitle[i][j] = new JCheckBox(subsectionTitleString[i][j]);
      }
    }
    /*sectiontitle = new JCheckBox[section];
    subsectionTitle = new JCheckBox[section][];
    //JCheckBox[][][] subsubcategory = new JCheckBox[section][][];
    sectiontitle[0] = new JCheckBox("第1章:基本概念");
    sectiontitle[1] = new JCheckBox("第2章:入出力と式");
    sectiontitle[2] = new JCheckBox("第3章:制御構文");
    sectiontitle[3] = new JCheckBox("第4章:配列");
    sectiontitle[4] = new JCheckBox("第5章:関数");*/
    /*第1章*/
    /*subsectionTitle[0] = new JCheckBox[3];
    subsectionTitle[0][0] = new JCheckBox("基本構造");
    //subsectionTitle[0][0].setEnabled(false);
    subsectionTitle[0][1] = new JCheckBox("定数，変数，データ型");
    //subsectionTitle[0][1].setEnabled(false);
    subsectionTitle[0][2] = new JCheckBox("printfと書式指定");*/
    /*第2章*/
    /*subsectionTitle[1] = new JCheckBox[5];
    subsectionTitle[1][0] = new JCheckBox("scanfとアドレス演算子");
    subsectionTitle[1][1] = new JCheckBox("式");
    //subsectionTitle[1][1].setEnabled(false);
    subsectionTitle[1][2] = new JCheckBox("型変換");
    //subsectionTitle[1][2].setEnabled(false);
    subsectionTitle[1][3] = new JCheckBox("代入演算子");
    //subsectionTitle[1][3].setEnabled(false);
    subsectionTitle[1][4] = new JCheckBox("インクリメント，デクリメント演算子");
    //subsectionTitle[1][4].setEnabled(false);*/
    /*第3章*/
    /*subsectionTitle[2] = new JCheckBox[6];
    subsectionTitle[2][0] = new JCheckBox("比較演算子と等価演算子");
    //subsectionTitle[2][0].setEnabled(false);
    subsectionTitle[2][1] = new JCheckBox("if文");
    //subsectionTitle[2][1].setEnabled(false);
    subsectionTitle[2][2] = new JCheckBox("while文");
    //subsectionTitle[2][2].setEnabled(false);
    subsectionTitle[2][3] = new JCheckBox("for文");
    //subsectionTitle[2][3].setEnabled(false);
    subsectionTitle[2][4] = new JCheckBox("do-while文");
    //subsectionTitle[2][4].setEnabled(false);
    subsectionTitle[2][5] = new JCheckBox("switch文");
    //subsectionTitle[2][5].setEnabled(false);*/
    /*第4章*/
    /*subsectionTitle[3] = new JCheckBox[3];
    subsectionTitle[3][0] = new JCheckBox("配列と配列の初期化");
    //subsectionTitle[3][0].setEnabled(false);
    subsectionTitle[3][1] = new JCheckBox("文字型配列");
    //subsectionTitle[3][1].setEnabled(false);
    subsectionTitle[3][2] = new JCheckBox("多次元配列");
    //subsectionTitle[3][2].setEnabled(false);*/
    /*第5章*/
    /*subsectionTitle[4] = new JCheckBox[6];
    subsectionTitle[4][0] = new JCheckBox("関数の定義");
    //subsectionTitle[4][0].setEnabled(false);
    subsectionTitle[4][1] = new JCheckBox("関数呼び出し");
    //subsectionTitle[4][1].setEnabled(false);
    subsectionTitle[4][2] = new JCheckBox("関数の戻り値");
    //subsectionTitle[4][2].setEnabled(false);
    subsectionTitle[4][3] = new JCheckBox("プロトタイプ宣言");
    //subsectionTitle[4][3].setEnabled(false);
    subsectionTitle[4][4] = new JCheckBox("ライブラリ");
    //subsectionTitle[4][4].setEnabled(false);
    subsectionTitle[4][5] = new JCheckBox("記憶クラスとスコープ");
    //subsectionTitle[4][5].setEnabled(false);*/
    int sum=0;
    for(int i=0 ; i<sectioncount ; i++){
      gbconst.gridx=0;
      gbconst.gridy=i+sum;
      gbconst.gridheight=subsectioncount[i];
      gblayout.setConstraints(sectiontitle[i],gbconst);
      subcenterPanel2.add(sectiontitle[i]);
      sectiontitle[i].setBorder(new LineBorder(Color.black, 2));
      for(int j=0 ; j<subsectioncount[i] ; j++){
        //System.out.println(j);
        gbconst.gridx=1;
        gbconst.gridy=sum;
        gbconst.gridheight=1;
        //System.out.println(sum);
        sum++;
        gblayout.setConstraints(subsectionTitle[i][j],gbconst);
        subcenterPanel2.add(subsectionTitle[i][j]);
      }
    }
    JButton next = new JButton("決定");
    next.setActionCommand("NEXT");
    next.addActionListener(this);
    gbconst.gridx=3;
    gbconst.gridy=sum;
    gbconst.gridheight=1;
    gblayout.setConstraints(next,gbconst);
    subcenterPanel2.add(next);
  }

  void makeBlankChecker(){
    Dom domtree = new Dom(inp);
    /*1.1基本構造*/
    if(subsectionTitle[0][0].isSelected() || sectiontitle[0].isSelected()){

    }
    /*1.2定数、変数、データ型*/
    if(subsectionTitle[0][1].isSelected() || sectiontitle[0].isSelected()){

    }
    /*1.3printfと書式指定*/
    if(subsectionTitle[0][2].isSelected() || sectiontitle[0].isSelected()){
      if(domtree.createblank("printf",Dom.SELECT,Dom.self))
        QM.qtype[0][2]++;
        //QM.printf++;
      //if(domtree.createblank("FORMAT",Dom.ALL))
      //  QM.qtype[0][2]++;
        //QM.format++;
      if(domtree.createblank(domtree.getElement("EXPRESSION"),"FORMAT",Dom.BOOL,domtree.condition(Dom.exist,"printf"),Dom.ALL))
        QM.qtype[0][2]++;
    }
    /*2.1scanfとアドレス演算子*/
    if(subsectionTitle[1][0].isSelected() || sectiontitle[0].isSelected()){
      if(domtree.createblank("FORMAT",Dom.ALL))
        QM.qtype[1][0]++;
        //QM.format++;
      if(domtree.createblank("scanf",Dom.SELECT,Dom.self))
        QM.qtype[1][0]++;
        //QM.scanf++;
      if(domtree.createblank("AMP",Dom.ALL))
        QM.qtype[1][0]++;
        //QM.adress++;
      if(domtree.createblank("AMP",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][0]++;
        //QM.adress++;
    }
    /*2.2式*/
    if(subsectionTitle[1][1].isSelected() || sectiontitle[1].isSelected()){
    }
    /*2.3型変換*/
    if(subsectionTitle[1][2].isSelected() || sectiontitle[1].isSelected()){
    }
    /*2.4代入演算子*/
    if(subsectionTitle[1][3].isSelected() || sectiontitle[1].isSelected()){
      if(domtree.createblank("ASSIGNMENT",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][3]++;
      if(domtree.createblank("ASSIGNMENT",Dom.SELECT,Dom.leftchild))
        QM.qtype[1][3]++;
      if(domtree.createblank("ASSIGNMENT",Dom.SELECT,Dom.rightchild))
        QM.qtype[1][3]++;
      if(domtree.createblank("ASSIGNMENT",Dom.SELECT,Dom.self));
      if(domtree.createblank("PLUSE",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][3]++;
      if(domtree.createblank("PLUSE",Dom.SELECT,Dom.self))
        QM.qtype[1][3]++;
      if(domtree.createblank("MINUSE",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][3]++;
      if(domtree.createblank("MINUSE",Dom.SELECT,Dom.self))
        QM.qtype[1][3]++;
      if(domtree.createblank("RESTE",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][3]++;
      if(domtree.createblank("RESTE",Dom.SELECT,Dom.self))
        QM.qtype[1][3]++;
      if(domtree.createblank("DIVE",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][3]++;
      if(domtree.createblank("DIVE",Dom.SELECT,Dom.self))
        QM.qtype[1][3]++;
      if(domtree.createblank("MULTIE",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][3]++;
      if(domtree.createblank("MULTIE",Dom.SELECT,Dom.self))
        QM.qtype[1][3]++;
    }
    /*2.5インクリメント、デクリメント演算子*/
    if(subsectionTitle[1][4].isSelected() || sectiontitle[1].isSelected()){
      if(domtree.createblank("UNARYPLUS",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][4]++;
      if(domtree.createblank("UNARYMINUS",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][4]++;
      if(domtree.createblank("POSTFIXPLUS",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][4]++;
      if(domtree.createblank("POSTFIXMINUS",Dom.SELECT,Dom.rootonly))
        QM.qtype[1][4]++;
    }
    /*3.1比較演算子と等価演算子*/
    if(subsectionTitle[2][0].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("EQUAL",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][0]++;
      if(domtree.createblank("EQUAL",Dom.SELECT,Dom.self))
        QM.qtype[2][0]++;
      if(domtree.createblank("NOTE",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][0]++;
      if(domtree.createblank("NOTE",Dom.SELECT,Dom.self))
        QM.qtype[2][0]++;
      if(domtree.createblank("FEWER",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][0]++;
      if(domtree.createblank("FEWER",Dom.SELECT,Dom.self))
        QM.qtype[2][0]++;
      if(domtree.createblank("GREATER",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][0]++;
      if(domtree.createblank("GREATER",Dom.SELECT,Dom.self))
        QM.qtype[2][0]++;
      if(domtree.createblank("FORE",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][0]++;
      if(domtree.createblank("FORE",Dom.SELECT,Dom.self))
        QM.qtype[2][0]++;
      if(domtree.createblank("BORE",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][0]++;
      if(domtree.createblank("BORE",Dom.SELECT,Dom.self))
        QM.qtype[2][0]++;
    }
    /*3.2if文*/
    if(subsectionTitle[2][1].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("IF",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][1]++;
      if(domtree.createblank("IF",Dom.SELECT,Dom.self))
        QM.qtype[2][1]++;
      if(domtree.createblank("IF",Dom.ALL))
        QM.qtype[2][1]++;
      if(domtree.createblank("IF",Dom.SELECT,Dom.conditiononly))
        QM.qtype[2][1]++;
      if(domtree.createblank("IF",Dom.SELECT,Dom.statementonly))
        QM.qtype[2][1]++;
      if(domtree.createblank("ELSE",Dom.ALL))
        QM.qtype[2][1]++;
      if(domtree.createblank("ELSE",Dom.SELECT,Dom.statementonly))
        QM.qtype[2][1]++;
    }
    /*3.3while文*/
    if(subsectionTitle[2][2].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("WHILE",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][2]++;
      if(domtree.createblank("WILE",Dom.SELECT,Dom.self))
        QM.qtype[2][2]++;
      if(domtree.createblank("WHILE",Dom.ALL))
        QM.qtype[2][2]++;
      if(domtree.createblank("WHILE",Dom.SELECT,Dom.statementonly))
        QM.qtype[2][2]++;
    }
    /*3.4for文*/
    if(subsectionTitle[2][3].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("FOR",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][3]++;
      if(domtree.createblank("FOR",Dom.SELECT,Dom.self))
        QM.qtype[2][3]++;
      if(domtree.createblank("FOR",Dom.ALL))
        QM.qtype[2][3]++;
      if(domtree.createblank("FOR",Dom.SELECT,Dom.statementonly))
        QM.qtype[2][3]++;
    }
    /*3.5do-while文*/
    if(subsectionTitle[2][4].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("DOWHILE",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][4]++;
      if(domtree.createblank("DOWHILE",Dom.SELECT,Dom.self))
        QM.qtype[2][4]++;
      if(domtree.createblank("DOWHILE",Dom.ALL))
        QM.qtype[2][4]++;
      if(domtree.createblank("DOWHILE",Dom.SELECT,Dom.statementonly))
        QM.qtype[2][4]++;
    }
    /*3.6switch*/
    if(subsectionTitle[2][5].isSelected() || sectiontitle[2].isSelected()){
      if(domtree.createblank("SWITCH",Dom.SELECT,Dom.rootonly))
        QM.qtype[2][5]++;
      if(domtree.createblank("SWITCH",Dom.SELECT,Dom.self))
        QM.qtype[2][5]++;
      if(domtree.createblank("SWITCH",Dom.ALL))
        QM.qtype[2][5]++;
      if(domtree.createblank("SWITCH",Dom.SELECT,Dom.statementonly))
        QM.qtype[2][5]++;
    }
    /*4.1配列と配列の初期化*/
    if(subsectionTitle[3][0].isSelected() || sectiontitle[3].isSelected()){
    }
    /*4.2文字型配列*/
    if(subsectionTitle[3][1].isSelected() || sectiontitle[3].isSelected()){
    }
    /*4.3多次元配列*/
    if(subsectionTitle[3][2].isSelected() || sectiontitle[3].isSelected()){
    }
    /*5.1関数の定義*/
    if(subsectionTitle[4][0].isSelected() || sectiontitle[4].isSelected()){
    }
    /*5.2関数呼び出し*/
    if(subsectionTitle[4][1].isSelected() || sectiontitle[4].isSelected()){
    }
    /*5.3関数の戻り値*/
    if(subsectionTitle[4][2].isSelected() || sectiontitle[4].isSelected()){
    }
    /*5.4プロトタイプ宣言*/
    if(subsectionTitle[4][3].isSelected() || sectiontitle[4].isSelected()){
    }
    /*5.5ライブラリ*/
    if(subsectionTitle[4][4].isSelected() || sectiontitle[4].isSelected()){
    }
    /*5.6記憶クラスとスコープ*/
    if(subsectionTitle[4][5].isSelected() || sectiontitle[4].isSelected()){
    }
  }

}