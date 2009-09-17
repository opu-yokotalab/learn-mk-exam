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
	      // ドキュメントビルダーファクトリを生成
	      DocumentBuilderFactory dbfactory1 = DocumentBuilderFactory.newInstance();
	      // ドキュメントビルダーを生成
	      DocumentBuilder builder1 = dbfactory1.newDocumentBuilder();
	      // パースを実行してDocumentオブジェクトを取得
	      Document doc1 = builder1.parse(new File(filename1));
	      // ルート要素を取得
	      Element root1 = doc1.getDocumentElement();
	      
	      // ドキュメントビルダーファクトリを生成
	      DocumentBuilderFactory dbfactory2 = DocumentBuilderFactory.newInstance();
	      // ドキュメントビルダーを生成
	      DocumentBuilder builder2 = dbfactory2.newDocumentBuilder();
	      // パースを実行してDocumentオブジェクトを取得
	      Document doc2 = builder2.parse(new File(filename2));
	      // ルート要素を取得
	      Element root2 = doc2.getDocumentElement();	      
	      
	      
	      
	      
	      // 最初の子ノード（テキストノード）の値を表示
	      //System.out.println("filename="+filename1);
	      //System.out.print(root1.getFirstChild().getNodeValue()+"\n");
	      
	      
	      //System.out.println("filename="+filename2);
	      //System.out.println("ルート要素のタグ名：" + root2.getTagName());
	      
	      
	      NodeList list1 = root1.getElementsByTagName("FUNC");
	      NodeList list2 = root2.getElementsByTagName("FUNC");
	      /*for (int i=0; i < list.getLength() ; i++){
	    	  Element elm = (Element)list.item(i);
	    	  NodeList childList = elm.getChildNodes();
	    	  System.out.println("Child Length:"+ childList.getLength());
	      }*/
	      
	      SearchChild(list1,list2);/*再帰呼び出し*/

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }	
	  }
	  
	  public static void SearchChild(NodeList L1,NodeList L2){/*深さ優先探索でvalueを表示していく*/
		  //System.out.println("Length:" + l.getLength());
		  if(L1.getLength() == L2.getLength()){//NodeListの長さ（同位の節目・葉の数）が同じか調べる
			System.out.println("L1 == L2");  
			for (int i=0; i < L1.getLength() ; i++){
		    	  Element elm1 = (Element)L1.item(i);//i個目の要素をエレメントとして定義
		    	  Element elm2 = (Element)L2.item(i);
		    	  System.out.println(elm1.getTagName());

		    	  NodeList childList1 = elm1.getChildNodes();//i個目の要素の子ノードリストを定義
		    	  NodeList childList2 = elm2.getChildNodes();
		    	  
		    	  if (elm1.getTagName() == "NONE"){/*タグ名がNONEだと子要素がないので再帰しない*/  
		    	  }
		    	  
		    	  else if(elm1.getFirstChild().getNodeValue() != null){/*この形「<タグ名>テキスト<タグ名>」だと子要素に部分木を持たない仕様なので再帰しない*/
		    		  System.out.println(elm1.getFirstChild().getNodeValue());  
		    	  }

		    	  else if(childList1.getLength() != 0 ){/*上記2つの条件＋要素数0以外のときは再帰する*/
		    		  
		    		  SearchChild(childList1,childList2);/*子要素のリストを引数に再帰*/
		    	  }
		      }
		  }

		  else {//NodeListの長さ（節目・葉の数）が違った場合
			  if(L1.getLength() > L2.getLength()){/*L1.getLength() > L2.getLength()のとき*/
				  System.out.println("L1 - L2 ="+ (L1.getLength()-L2.getLength()));
			  }
			  else {/*L1.getLength() < L2.getLength()のとき*/
				  System.out.println("L2 - L1　="+ (L1.getLength()-L2.getLength()));
			  }
		  }
		  
	      /*for (int i=0; i < L1.getLength() ; i++){
	    	  Element elm1 = (Element)L1.item(i);
	    	  Element elm2 = (Element)L2.item(i);
	    	  System.out.println(elm1.getTagName());

	    	  NodeList childList1 = elm1.getChildNodes();
	    	  NodeList childList2 = elm2.getChildNodes();
	    	  
	    	  if (elm1.getTagName() == "NONE"){//タグ名がNONEだと子要素がないので再帰しない 
	    	  }
	    	  
	    	  else if(elm1.getFirstChild().getNodeValue() != null){//この形「<タグ名>テキスト<タグ名>」だと子要素に部分木を持たない仕様なので再帰しない
	    		  System.out.println(elm1.getFirstChild().getNodeValue());  
	    	  }

	    	  else if(childList1.getLength() != 0 ){//上記2つの条件＋要素数0以外のときは再帰する
	    		  
	    		  SearchChild(childList1,childList2);//子要素のリストを引数に再帰
	    	  }
	      }*/
	  }
	  
	}

