import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

public class Take_Exam {

	JFrame mainFrame;
	JPanel mainPanel;
	JButton mainButton;
	JLabel mainLabel;

	
	public static void main (String[] args) {
		
		JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();
		JButton mainButton = new JButton("test!!");
	    mainPanel.setLayout(new BorderLayout());
	    mainPanel.setPreferredSize(new Dimension(900,600));
	    //JLabel mainLabel = new JLabel();
		
		String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_if.xml";
		  

	    try {
	      // �h�L�������g�r���_�[�t�@�N�g���𐶐�
	      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	      // �h�L�������g�r���_�[�𐶐�
	      DocumentBuilder builder = dbfactory.newDocumentBuilder();
	      // �p�[�X�����s����Document�I�u�W�F�N�g���擾
	      Document doc = builder.parse(new File(filename));
	      // ���[�g�v�f���擾
	      Element root = doc.getDocumentElement();
	      
	      System.out.println("NodeValue:"+root.getChildNodes().toString());
	      JLabel mainLabel = new JLabel(root.getChildNodes().toString());
	      
	      mainPanel.add(mainLabel);
	      //mainPanel.add(mainButton);
	      mainFrame.add(mainPanel);
	    
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    mainFrame.setVisible(true);
	}
	
}
