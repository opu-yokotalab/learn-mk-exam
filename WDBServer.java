/***
  WDBServer : DB�������݃T�[�o
  ---
  ���K��萶���V�X�e�����瑗���Ă����f�[�^��
  �f�[�^�x�[�X�ɏ������ރT�[�o�i�\��j�ł��B
  �W���o�͂Ƀ��O���o�͂��܂��B
  */
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class WDBServer extends Thread {

  Socket soc;		// �\�P�b�g

  /**
    ����
    */
  public WDBServer(Socket s){
    soc=s;
  }

  /**
    ���s
    */
  public void run(){
    try{

      // ���o�̓X�g���[���̍쐬

      DataInputStream is=new DataInputStream(soc.getInputStream());
      DataOutputStream os=new DataOutputStream(soc.getOutputStream());

      // �f�[�^����M

      String id = is.readUTF();
      String program_set = is.readUTF();
      String source = is.readUTF();

      // ���Z

      //���͊m�F
      System.out.println("input: "+id+" | "+program_set+" | "+source);
      
      //DB�֏�������
      try{	
    	  //DB�ւ̋L�q�i���[�J���j
    	  /*�h���C�o�N���X�����[�h*/
    	  Class.forName("org.postgresql.Driver");
    	  //DB�֐ڑ�
    	  Connection con = DriverManager.getConnection("jdbc:postgresql:problem_DB","postgres","postgres");
    	  //System.out.println("DB conect OK");//DB�ڑ��`�F�b�N
    	  //�X�e�[�g�����g�I�u�W�F�N�g�𐶐�
    	  Statement stmt = con.createStatement();
    	  //SQL�����쐬
    	  String sql = "INSERT INTO question VALUES('" + id + "','" + program_set + "','" + source + "');";
    	  //System.out.println(sql);//�N�G���`�F�b�N�o��
    	  //�N�G�������s
    	  int result = stmt.executeUpdate(sql);
    	  //DB����̐ؒf
    	  stmt.close();
    	  con.close();
    	  //	DB�A�N�Z�X���̗�O����
      }catch (Exception e){
    	  e.printStackTrace();
      }	
            
      // ���Z���ʂ𑗏o

      //os.writeDouble(r);

      // �\�P�b�g�����

      soc.close();
    }
    catch(IOException e){
      System.out.println("Error: "+e);
    }
  }

  /**
    ���C��
    */
  public static void main(String[] args){
    /*if(args.length!=1){
      System.err.println("Usage: java SQLServer port");
      System.exit(1);
    }*/

    System.err.println("*** WDB Server ***");

    //int port=Integer.parseInt(args[0]);
    //�|�[�g���w��
    int port = 3000;
    try{
      ServerSocket ss=new ServerSocket(port);
      for(;;){
	Socket s=ss.accept();
	//�f�[�^��M�̃��O���R���\�[���ɏo��
	System.out.println((new Date())+": accept: "+s);
		(new WDBServer(s)).start();
      }
    }
    //��O����
    catch(IOException e){
      System.out.println("Fatal: "+e);
    }
  }
}
