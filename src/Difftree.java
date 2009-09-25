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
	      
	      	      
	      //引数で渡すためにFUNC以下をノードリストに
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
		  int leng_l,leng_s;
		  NodeList Long_L,Short_L;
		  
		  
		  if(L1.getLength() == L2.getLength()){//NodeListの長さ（同位の節目・葉の数）が同じか調べる
			leng_l = L1.getLength();
			leng_s = L2.getLength();
			Long_L = L1;
			Short_L = L2;
		  }

		  else {//NodeListの長さ（節目・葉の数）が違った場合
			  //if(L1.getLength() > L2.getLength()){/*L1.getLength() > L2.getLength()のとき*/
				  leng_l = L1.getLength();
				  leng_s = L2.getLength();
				  Long_L = L1;
				  Short_L = L2;
			  //}
			  //else {/*L1.getLength() < L2.getLength()のとき*/
				  //leng_l = L2.getLength();
				  //leng_s = L1.getLength();
				  //Long_L = L2;
				  //Short_L = L1;
			  //}
		  }
		  //各節の対応表を作る
		  
		  int[][] E_list= new int[leng_l*leng_s*2][2];//対応表
		  int p=0;//対応表のどの位置まで値が入っているか
		  int flag;//対応するものがあったかどうかのフラグ
		  int some;//１つのノードに対して複数対応しているかどうかのフラグ
		  //int ch;
		  
		  //System.out.println("leng_l="+leng_l + " ,Leng_s="+leng_s);
		  
		  for (int i=0; i< leng_l ; i++){
			  Element elm1 = (Element)Long_L.item(i);//リストL1のi個目の要素をエレメントとして定義
			  flag = 0;
			  some = 0;
			  for (int j=0; j< leng_s ; j++){
				  //ch = 0;
				  Element elm2 = (Element)Short_L.item(j);//リストL2のｊ個目の要素をエレメントとして定義
				  //System.out.println("elm1.TN:"+elm1.getTagName()+" elm2.TN:"+elm2.getTagName());
				  if(elm1.getTagName() == elm2.getTagName()){//タグ名が一緒だったら
					  //System.out.println("elm1.TN:"+elm1.getTagName()+" elm2.TN:"+elm2.getTagName());
					  if(some < 1){
						  if (elm1.getTagName().equals("NONE")){//<NONE/>についての処理(子要素がないため扱いが特殊）
							  E_list[p][0]=i;//対応表に追加
							  E_list[p][1]=j;
							  p++;//ポジションをずらす
						  }
						  else if (elm1.getFirstChild().getNodeValue() != null ){//各子要素があるかどうか
							  //System.out.println("t/f:"+(elm1.getFirstChild().getNodeValue()).equals(elm2.getFirstChild().getNodeValue()));
							  //System.out.println("elm1.child:"+elm1.getFirstChild().getNodeValue() +",elm2.child:"+elm2.getFirstChild().getNodeValue());
							  if((elm1.getFirstChild().getNodeValue()).equals(elm2.getFirstChild().getNodeValue())){//さらに子のテキストが同一かどうか
								  E_list[p][0]=i;//対応表に追加
								  E_list[p][1]=j;
								  p++;//ポジションをずらす
							  }
							  else {/*ここで何かする*/
								  //System.out.println("elm1.child:"+elm1.getFirstChild().getNodeValue() +",elm2.child:"+elm2.getFirstChild().getNodeValue());
								  E_list[p][0]=i;//対応表に追加
								  E_list[p][1]=j;
								  p++;//ポジションをずらす
								  
								  E_list[p][0]=i;//子ノードをを対応表に追加
								  E_list[p][1]=-2;//子ノードフラグ(-2)
								  p++;
								  //ch++;
								  //System.out.println("p:"+p+",E_list[p][0]:"+E_list[p-1][0]+",E_list[p][1]:"+E_list[p-1][1]);
								  //System.out.println("ch:"+ch);
							  }
						  }
						  else{
							  E_list[p][0]=i;//対応表に追加
							  E_list[p][1]=j;
							  p++;//ポジションをずらす
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
					  flag = 1;//対応するものがあったかどうかのフラグ
					  some++;
					  //System.out.println("some:"+some);
				  }
				  else{
					  //System.out.println("el1:"+elm1.getTagName()+" ,el2:"+elm2.getTagName());
				  }
			  }
			  if(flag == 0){//対応するものがなければ
				  E_list[p][0]=i;
				  E_list[p][1]=-1;
				  p++;//ポジションをずらす
			  }
			  /*if(some <1){//複数対応するものがあった場合
				  System.out.println("Check!!");
				  for(int a=0;a<some;a++){
					  if(E_list[p-a][0]==E_list[p-a][1]){//同じ順番で出現する可能性が高いので、そこを対応するノードとする
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
	    		  System.out.println("差分1："+((Element)Long_L.item(E_list[i][0])).getTagName());
	    	  }
	    	  else if(E_list[i][1] == -1){
	    		  System.out.println("差分2："+((Element)Long_L.item(E_list[i][0])).getTagName());
	    	  }
	    	  else if(E_list[i][0] == -1 && E_list[i][1] == -1){
	    		  System.out.println("差分3："+((Element)Long_L.item(E_list[i][0])).getTagName());  
	    	  }*/
	    	  //System.out.println("E_list["+i+"][0]:"+E_list[i][0]+" ,E_list["+i+"][1]:"+E_list[i][1]);
	    	  if(E_list[i][0] == -1 ||E_list[i][1] == -1){
	    		  //System.out.println("差分："+((Element)Long_L.item(E_list[i][0])).getTagName());
	    		  DiffPrint((Element)Long_L.item(E_list[i][0]),0);
	    	  }
	    	  else if(E_list[i][0] == -2 || E_list[i][1]== -2){
	    		  System.out.println(((Element)Long_L.item(E_list[i][0])).getFirstChild().getNodeValue());
	    	  }
	    	  else{
	    		  
	    		  Element elm1 = (Element)Long_L.item(E_list[i][0]);//i個目の要素をエレメントとして定義
	    		  Element elm2 = (Element)Short_L.item(E_list[i][1]);
	    		  //System.out.println(elm1.getTagName());
	    		  
	    		  NodeList childList1 = elm1.getChildNodes();//i個目の要素の子ノードリストを定義
	    		  NodeList childList2 = elm2.getChildNodes();
	    	  
	    		  if (elm1.getTagName() == "NONE"){//タグ名がNONEだと子要素がないので再帰しない 
	    		  }
	    		  
	    		  else if(elm1.getFirstChild().getNodeValue() != null){
	    			  //この形「<タグ名>テキスト<タグ名>」だと子要素に部分木を持たない仕様なので再帰しない
	    			  //そうでもないらしい<STRING></STRING>の中にはテキストと同時に要素も持っていた…（未対応）
	    			  //System.out.println(elm1.getFirstChild().getNodeValue());  
	    		  }

	    		  else if(childList1.getLength() != 0 ){//上記2つの条件＋要素数0以外のときは再帰する
	    			  
	    			  SearchChild(childList1,childList2);//子要素のリストを引数に再帰
	    		  }
	    	  }
	      }
	  }
	  
	  public static void DiffPrint(Element e,int a){//差分を表示する
		  NodeList cList = e.getChildNodes();
		  a++;
		  //for(int j = 0;j<a;j++)System.out.println("-");
		  
		  System.out.println(e.getTagName());
		  if (e.getTagName() == "NONE"){//タグ名がNONEだと子要素がないので再帰しない 
		  }
		  
		  else if(e.getFirstChild().getNodeValue() != null){//この形「<タグ名>テキスト<タグ名>」だと子要素に部分木を持たない仕様なので再帰しない
			  System.out.println(e.getFirstChild().getNodeValue());  
		  }

		  else if(cList.getLength() != 0 ){//上記2つの条件＋要素数0以外のときは再帰する
			  for(int i=0;i<cList.getLength();i++){
				  DiffPrint((Element)cList.item(i),a);
			  }
		  }
	  }
	  
	  
	}

