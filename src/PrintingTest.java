import java.awt.*;
import java.awt.event.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PrintingTest extends Frame implements ActionListener {

    PrintCanvas canvas;
	Panel p;

    public PrintingTest() {
        //super("Printing Test");
    	setSize(400,300);
    	setTitle("printing Test");
    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e){
    			System.exit(0);
    		}
    	});
    	
        //Panel p=new Panel();
        //add(p,"Center");

        canvas = new PrintCanvas();
        add("Center", canvas);
        
        Button b = new Button("Print");
        b.setActionCommand("print");
        b.addActionListener(this);
        add("South", b);

        pack();
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("print")) {
	//����f�o�C�X���擾����
            PrintJob pjob = getToolkit().getPrintJob(this,
                               "Printing Test", null, null);

            if (pjob != null) {          
	//�v�����^�ɑ���C���[�W���擾����
                Graphics pg = pjob.getGraphics();

                if (pg != null) {
		//���̃R���|�[�l���g���������
                    canvas.printAll(pg);
		//��������O���t�B�b�N�X����������
                    pg.dispose(); // flush page
                }
		//����W���u���I������
                pjob.end();

            }
        }
    }

    public static void main(String args[]) {
        PrintingTest test = new PrintingTest();
        //test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�E��~��ŏI��
        test.setVisible(true);
    }
}

class PrintCanvas extends Canvas {
	

    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
  
    public void paint(Graphics g) {
    	
    	int place;
    	String pri;
    	int len;
    	
    	/*�o�͍��ڒ�`*/
    	String question;
    	String sentence;
    	String program_set;
    	int idi = 1;
    	String id;
    	String hints;
    	String function;
    	int score;
    	int weight;
    	int point;
    	String explanation;
    	String[] answer;

    	/*�o�͍��ڑ��*/
        question = new String("�����ߖ��̖�蕶");
        sentence = new String("");
        program_set = new String("�e�X�g�O���[�v�P");
        id = Integer.toString(idi);
        hints = new String ("�q���g��񎦂����B");
        function = new String ("");
        score = 10;
        weight = 15;
        point = 20;
        explanation = new String ("");
        //answer
        
        /*���O���[�v�񎦁i��i�j*/
        g.setColor(Color.black);
        place = 10;
        g.drawString("���O���[�v:",place,10);
        place = place + 5 + "���O���[�v:".length()*10;

        g.drawString(program_set,place,10);
        
        try{
        	len = program_set.getBytes("Shift_JIS").length;
        	if(len == program_set.length()){
        		place = place + 10 + program_set.length() * 5;
        	}
        	else {
        		place = place + 10 + program_set.length() * 10;
        	}
        	/*�m�F�o��*/
        	//System.out.println("len:"+len);
        	//System.out.println("length():"+program_set.length());
        	//place = place + 5 + pri.getBytes("Shift_JIS").length;
        	//System.out.println("len1==length1:"+(len==program_set.length()));
        }catch( Exception e ){
        	System.out.println("Error:");
        }
        /*ID�̒񎦁i��i�j*/
        g.drawString("ID:",place,10);
        place = place + 5 + "ID:".length()*5;
        g.drawString(id,place,10);
        
        /*��蕶���\��(���i�j*/
        g.drawString("��蕶",50,50);
		g.drawString(question,60,70);
		/*�񓚗�*/
		//g.drawString("", x, y)
		g.drawRect(60,100,100,20);
        
		g.drawString("�q���g",50,140);
		g.drawString(hints, 60, 160);
        //g.setColor(Color.blue);
        //g.drawLine(0, 0, r.width, r.height);

        //g.setColor(Color.red);
        //g.drawLine(0, r.height, r.width, 0);
    }
    /*public void paint(Graphics g) {
        Rectangle r = getBounds();

        g.setColor(Color.yellow);
        g.fillRect(0, 0, r.width, r.height);

        g.setColor(Color.blue);
        g.drawLine(0, 0, r.width, r.height);

        g.setColor(Color.red);
        g.drawLine(0, r.height, r.width, 0);
    }*/
}
