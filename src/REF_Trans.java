import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class REF_Trans {/*正規フォーマット（Regular Expression Format)への変換（メイン）*/

	/*正規フォーマットへの変換メイン*/
	public static void main (String[] args){
		
		//変換するxml文書の指定
		String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_h.xml";
		//String f_name= filename.split("\\")[(filename.split("\\").length)-1];
		String f_name = new String("output_h");
	    try {
	      // ドキュメントビルダーファクトリを生成
	      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	      // ドキュメントビルダーを生成
	      DocumentBuilder builder = dbfactory.newDocumentBuilder();
	      // パースを実行してDocumentオブジェクトを取得
	      Document doc = builder.parse(new File(filename));
	      
	      /*対応テーブル作成*/
	      doc = Make_Table(doc,f_name);
	      
	      /*正規フォーマットへ変換*/
	      //doc = Transformation(doc,f_name);
	      
	      //最終的な正規フォーマットになった構文木の出力
	      try{
				DOMSource source= new DOMSource(doc); 
				//==============================//
				//  変換先（ファイル）生成      //
				//==============================//
				File f =new File(f_name+"_REF.xml"); 
				FileOutputStream fo = new FileOutputStream(f); 
				StreamResult result = new StreamResult(fo); 
				
				//==============================//
				//	変換		     //
				//==============================//
				TransformerFactory transFactory = TransformerFactory.newInstance(); 
				Transformer transformer = transFactory.newTransformer(); 
				transformer.transform(source, result); 

				//==============================//
				//	あとしまつ	     //
				//==============================//
				fo.close(); 
			}catch (Exception e)
			{
				e.printStackTrace();
			}
	      
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
	}
	
	/*変数の対応テーブル作成*/
	public static Document Make_Table(Document doc,String f_name){
	//public static Document Make_Table(document doc){
		/*documentを引数にDOMを用いて変数の対応テーブルの作成と
		 * 各変数ノードへID付を行う。返り値はdocument */
		
		String[][] v_table = new String[50][4];//変数の対応テーブル
		int table_c = 0;//対応テーブル用のカウンタ
		int node_c = 0;//ノードカウンタ
		String type;
		
		// ルート要素を取得
		Element root = doc.getDocumentElement();
		//変数の定義を行っているDECLARATIONノードのリストを得る
		NodeList list1 = root.getElementsByTagName("DECLARATION");
		for(int i=0;i<list1.getLength();i++){
			Element element1 = (Element)list1.item(i);
			
			//DECLARATIONの子ノードのTYPEを探す（変数の型）
			NodeList element_list1 = element1.getElementsByTagName("TYPE");
			Element type_elm = (Element)element_list1.item(0);
			type = type_elm.getFirstChild().getNodeValue();
			
			//DECLARATIONの子ノードにあるVARIABLEを探す（変数名）
			element_list1 = element1.getElementsByTagName("VARIABLE");
			for(int j=0;j<element_list1.getLength();j++){
				
				//変数のリストが得られる
				Element elm1 = (Element)element_list1.item(j);
				
				//変数の対応テーブルにIDと名前を格納
				v_table[table_c][0] = "$"+table_c;
				v_table[table_c][1] = elm1.getFirstChild().getNodeValue();
				v_table[table_c][2] = type;
				v_table[table_c][3] = "N"+node_c+",";
				
				//IDをふる
				elm1.setAttribute("id",v_table[table_c][0]);
				elm1.setAttribute("n_id","N"+node_c);
				
				//カウンタを1つすすめる
				table_c++;
				node_c++;
			}
		}
		//処理の部分にある変数を対応テーブルに対応させる
		
		//処理内容を記述しているSTATEMENTノードのリストを得る
		NodeList list2 = root.getElementsByTagName("STATEMENT");
		
		for(int k=0;k<list2.getLength();k++){
			
			Element element2 = (Element)list2.item(k);
			//STATEMENTの子ノードにあるVARIABLEを探す(処理中の変数にあたる）
			NodeList variable_list = element2.getElementsByTagName("VARIABLE");
			
			for(int l=0;l<variable_list.getLength();l++){
				Element elm2 = (Element)variable_list.item(l);
				for(int m=0;m<table_c;m++){

					//対応テーブルとの対応付けを行う（変数名が等しければ変数のID[id="$X"](とノードのID[n_id="NX"])をふる)(Xは変数）
					if(elm2.getFirstChild().getNodeValue().equals(v_table[m][1])){
						elm2.setAttribute("id",v_table[m][0]);
						elm2.setAttribute("n_id","N"+node_c);

						//対応テーブルへの書き込み
						v_table[m][3]=v_table[m][3]+"N"+node_c+",";
						node_c++;
					}
				}
			}
		}
		
		/*//変数の対応を書き込み（デバック用）
		try{
			DOMSource source= new DOMSource(doc); 
			//==============================//
			//  変換先（ファイル）生成      //
			//==============================//
			File f =new File(f_name+"_table.xml"); 
			FileOutputStream fo = new FileOutputStream(f); 
			StreamResult result = new StreamResult(fo); 
			
			//==============================//
			//	変換		     //
			//==============================//
			TransformerFactory transFactory = TransformerFactory.newInstance(); 
			Transformer transformer = transFactory.newTransformer(); 
			transformer.transform(source, result); 

			//==============================//
			//	あとしまつ	     //
			//==============================//
			fo.close(); 
		}catch (Exception e)
		{
			e.printStackTrace();
		}*/

		//テーブルの表示
		System.out.println("ID | name | type | node_location");
		for(int a=0;a<table_c;a++){
			System.out.println(v_table[a][0]+" |  "+v_table[a][1]+"   | "+v_table[a][2]+" | "+v_table[a][3]);
		}

		//返り値
		return doc;

	}
	
	/*正規フォーマットへ変換*/
	public static Document Transformation(Document doc,String f_name){
		
		// ルート要素を取得
		Element root = doc.getDocumentElement();
		
		//変数の定義の並び替え
		
		//変数の定義を行っているDECLARATIONノードのリストを得る
		NodeList list1 = root.getElementsByTagName("DECLARATION");
		for(int i=0;i<list1.getLength();i++){
			Element element1 = (Element)list1.item(i);
			
			//DECLARATIONの子ノードのTYPEを探す（変数の型）
			NodeList element_list1 = element1.getElementsByTagName("TYPE");
			Element type_elm = (Element)element_list1.item(0);
			//type = type_elm.getFirstChild().getNodeValue();
			
			//DECLARATIONの子ノードにあるVARIABLEを探す（変数名）
			element_list1 = element1.getElementsByTagName("VARIABLE");
			for(int j=0;j<element_list1.getLength();j++){
				
				//変数のリストが得られる
				Element elm1 = (Element)element_list1.item(j);
				
				//変数の対応テーブルにIDと名前を格納
				//v_table[table_c][0] = "$"+table_c;
				//v_table[table_c][1] = elm1.getFirstChild().getNodeValue();
				//v_table[table_c][2] = type;
				//v_table[table_c][3] = "N"+node_c+",";
				
				//IDをふる
				//elm1.setAttribute("id",v_table[table_c][0]);
				//elm1.setAttribute("n_id","N"+node_c);
				
				//カウンタを1つすすめる
				//table_c++;
				//node_c++;
			}
		}
		
		
		
		return doc;
		
		/*//xmlの構文木を読み込んでxsltで正規フォーマットへ変換し出力 
		DOMSource dom = new DOMSource(doc);
		//適応するxslt文書名の指定
		String xsltdoc = new String("C:\\Users\\ariyasu\\workspace\\mkexam2\\src\\trans.xsl");
		//String xsltdoc = new String("trans.xsl");
		try {
		      // TransformerFactoryインスタンスを取得
		      TransformerFactory factory = TransformerFactory.newInstance();
		      // XSLファイルからtranceformerを取得
		      Transformer transformer = 
		        factory.newTransformer(new StreamSource(xsltdoc));
		      // 出力するエンコーディングを設定
		      transformer.setOutputProperty("encoding","Shift_JIS");
		      //出力xml名：Out(元のファイル名).xml
		      StreamResult result = new StreamResult(new FileOutputStream("Out_"+f_name+".xml"));
		      // XMLファイルをXSLTで変換して出力
		      transformer.transform(dom,result);
		    } catch(Exception e) {
		      e.printStackTrace();
		    }
		    */
		    
	}
					
}
