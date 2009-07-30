/***
  WDBServer : DB書き込みサーバ
  ---
  演習問題生成システムから送られてきたデータを
  データベースに書き込むサーバ（予定）です。
  標準出力にログを出力します。
  */
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class WDBServer extends Thread {

  Socket soc;		// ソケット

  /**
    生成
    */
  public WDBServer(Socket s){
    soc=s;
  }

  /**
    実行
    */
  public void run(){
    try{

      // 入出力ストリームの作成

      DataInputStream is=new DataInputStream(soc.getInputStream());
      DataOutputStream os=new DataOutputStream(soc.getOutputStream());

      // データを受信

      String id = is.readUTF();
      String program_set = is.readUTF();
      String source = is.readUTF();

      // 演算

      //入力確認
      System.out.println("input: "+id+" | "+program_set+" | "+source);
      
      //DBへ書き込み
      try{	
    	  //DBへの記述（ローカル）
    	  /*ドライバクラスをロード*/
    	  Class.forName("org.postgresql.Driver");
    	  //DBへ接続
    	  Connection con = DriverManager.getConnection("jdbc:postgresql:problem_DB","postgres","postgres");
    	  //System.out.println("DB conect OK");//DB接続チェック
    	  //ステートメントオブジェクトを生成
    	  Statement stmt = con.createStatement();
    	  //SQL文を作成
    	  String sql = "INSERT INTO question VALUES('" + id + "','" + program_set + "','" + source + "');";
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
            
      // 演算結果を送出

      //os.writeDouble(r);

      // ソケットを閉じる

      soc.close();
    }
    catch(IOException e){
      System.out.println("Error: "+e);
    }
  }

  /**
    メイン
    */
  public static void main(String[] args){
    /*if(args.length!=1){
      System.err.println("Usage: java SQLServer port");
      System.exit(1);
    }*/

    System.err.println("*** WDB Server ***");

    //int port=Integer.parseInt(args[0]);
    //ポートを指定
    int port = 3000;
    try{
      ServerSocket ss=new ServerSocket(port);
      for(;;){
	Socket s=ss.accept();
	//データ受信のログをコンソールに出力
	System.out.println((new Date())+": accept: "+s);
		(new WDBServer(s)).start();
      }
    }
    //例外処理
    catch(IOException e){
      System.out.println("Fatal: "+e);
    }
  }
}
