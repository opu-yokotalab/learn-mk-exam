import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class REF_Trans {/*正規フォーマット（Regular Expression Format)への変換（メイン）*/
	
	//フィールド
	private static Node state1;
	private static Node state2;
	private static Node state3;
	private static Node exe;
	private static String num_id;
	
	/*正規フォーマットへの変換メイン*/
	public static void main (String[] args){
		
		//変換するxml文書の指定
		String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\output_h2.xml";
		//String f_name= filename.split("\\")[(filename.split("\\").length)-1];
		String f_name = new String("output_h2");
	    try {
	      // ドキュメントビルダーファクトリを生成
	      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	      // ドキュメントビルダーを生成
	      DocumentBuilder builder = dbfactory.newDocumentBuilder();
	      // パースを実行してDocumentオブジェクトを取得
	      Document doc = builder.parse(new File(filename));
	      
	      //変数に識別ＩＤを付加
	      doc = add_ID(doc);
	      
	      /*正規フォーマットへ変換*/
	      doc = Transformation(doc,f_name);
	      
	      /*対応テーブル作成*/
	      doc = Make_Table(doc,f_name);
	      
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
	public static Document add_ID(Document doc){
		//変数にＩＤを付加
		
		int table_c = 0;//対応テーブル用のカウンタ
		String[][] v_table = new String[50][2];//変数の対応テーブル 
		
		// ルート要素を取得
		Element root = doc.getDocumentElement();
		//変数の定義を行っているDECLARATIONノードのリストを得る
		NodeList list1 = root.getElementsByTagName("DECLARATION");
		for(int i=0;i<list1.getLength();i++){
			Element element1 = (Element)list1.item(i);
			
			//DECLARATIONの子ノードのTYPEを探す（変数の型）
			//NodeList element_list1 = element1.getElementsByTagName("TYPE");
			//Element type_elm = (Element)element_list1.item(0);
			//type = type_elm.getFirstChild().getNodeValue();
			
			//DECLARATIONの子ノードにあるVARIABLEを探す（変数名）
			NodeList element_list1 = element1.getElementsByTagName("VARIABLE");
			for(int j=0;j<element_list1.getLength();j++){
				
				//変数のリストが得られる
				Element elm1 = (Element)element_list1.item(j);
				
				//変数の対応テーブルにIDと名前を格納
				v_table[table_c][0] = "$"+table_c;
				v_table[table_c][1] = elm1.getFirstChild().getNodeValue();
				//v_table[table_c][2] = type;
				//v_table[table_c][3] = "N"+node_c+",";
				
				//IDをふる
				elm1.setAttribute("id","$"+table_c);
				//elm1.setAttribute("n_id","N"+node_c);
				
				//カウンタを1つすすめる
				table_c++;
				//node_c++;
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
						//elm2.setAttribute("n_id","N"+node_c);

						//対応テーブルへの書き込み
						//v_table[m][3]=v_table[m][3]+"N"+node_c+",";
						//node_c++;
					}
				}
			}
		}
		
		return doc;
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
				
				//IDのふりなおし
				elm1.removeAttribute("id");
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
						elm2.removeAttribute("id");
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
		
		// 元文書のルート要素を取得
		Element root = doc.getDocumentElement();

		
		//----------始：WHILEをFORに置き換え--------------------------
		//
		//FORのテンプレートの読み込み
		/*String filename = "C:\\Users\\ariyasu\\workspace\\mkexam2\\temp_for.xml";
	    try {
	      // ドキュメントビルダーファクトリを生成
	      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
	      // ドキュメントビルダーを生成
	      DocumentBuilder builder = dbfactory.newDocumentBuilder();
	      // パースを実行してDocumentオブジェクトを取得
	      Document temp = builder.parse(new File(filename));
	    */  

		//
		//元文書の解析
		//									
		//WHILEを探す
		NodeList while_list = root.getElementsByTagName("WHILE");
		if(while_list.getLength()!=0){
			for(int i=0;i<while_list.getLength();i++){
				Element elm1 = (Element)while_list.item(i);
			
				//第2引数を探す
				NodeList state_list = elm1.getElementsByTagName("PARENTHESIS");
				for(int j=0;j<state_list.getLength();j++){
					Element state = (Element)state_list.item(j);
					//第2引数(EXPRESSIONの子を取得）	
					state2 = state.getFirstChild();
				}
			
				//第3引数を探す
				state_list = elm1.getElementsByTagName("STATEMENT");
				for (int k=0;k<state_list.getLength();k++){
					Element state = (Element)state_list.item(k);
					//old_n = state_list.item(k);
					//第3引数(whileの処理部のブロックの一番最後にFORの第3引数があることが前提）
					state3 = state.getLastChild();
					//処理内容(現在1行の場合しか取れない：再考の余地あり)
					exe = state.getFirstChild();
					//引数のIDを取得する
					num_id = state3.getFirstChild().getFirstChild().getAttributes().item(0).getNodeValue();
					//System.out.println("num_id:"+num_id);
					//System.out.println("i:"+state3.getFirstChild().getFirstChild().getFirstChild().getNodeValue());
				}
				
			}
			//}
			//第1引数を探す
			NodeList dec_list = root.getElementsByTagName("DECLARATION");
			for(int i=0;i<dec_list.getLength();i++){
			
				int flag = 0;//IDが等しいものがあるかどうかのフラグ
			
				Element val = (Element)dec_list.item(i);
				NodeList val_list = val.getElementsByTagName("VARIABLE");
				for(int j=0;j<val_list.getLength();j++){
					//System.out.println("num_id:"+num_id);
					//System.out.println("value:"+val_list.item(j).getAttributes().item(0).getNodeValue());
					//System.out.println("true/false:"+num_id.equals(val_list.item(j).getAttributes().item(0).getNodeValue()));
					if(num_id.equals(val_list.item(j).getAttributes().item(0).getNodeValue())){
						flag=1;
					}
					//System.out.println("flag:"+flag);
				}
				if(flag==1){
					NodeList ass = val.getElementsByTagName("DECLARATOR");
					for(int k=0;k<ass.getLength();k++){
						Element state = (Element)ass.item(k);
						state1 = state.getFirstChild().cloneNode(true);
					}
					//System.out.println("state1:"+state1.getFirstChild().getFirstChild().getFirstChild().getNodeValue());
					flag=0;
				}
			}
			
			//System.out.println("state1:"+state1.getNodeName());
			//System.out.println("state2:"+state2.getNodeName());
			//System.out.println("state3:"+state3.getNodeName());
			//
			//テンプレート作成
			//
			Element temp = doc.createElement("template");
			//<FOR>作成
			Element fo = doc.createElement("FOR");
			//テンプレートに<FOR>を
			temp.appendChild(fo);
			Element pare = doc.createElement("PARENTHESIS");
			//FORの子ノードにPARENTHESIS
			fo.appendChild(pare);
			Element exp = doc.createElement("EXPRESSION");
			//PARENTHESISの子ノードにEXPRESSION(第1引数）
			pare.appendChild(exp);
			exp.appendChild(state1);
			//第2引数
			pare.appendChild(state2);
			//第3引数
			pare.appendChild(state3);
			//FORの子にBLOCK
			Element block = doc.createElement("BLOCK");
			fo.appendChild(block);
			//BLOCKの子にSTATEMENT
			Element state = doc.createElement("STATEMENT");
			block.appendChild(state);
			state.appendChild(exe);
		
			NodeList ch = root.getElementsByTagName("STATEMENT");
			//文章のSTATEMENT
			Element st = (Element)ch.item(0);
			st.insertBefore(fo,st.getElementsByTagName("WHILE").item(0));
			st.removeChild(st.getElementsByTagName("WHILE").item(0));
		}
		
		//---------------終：while置き換え-------------------------------
		
		//---------------始：交換法則置き換え----------------------------
		
		//交換法則が成り立つものの置き換え(加算）
		NodeList plus_list = root.getElementsByTagName("PLUS");
		for(int i=0;i<plus_list.getLength();i++){
			Element plus = (Element)plus_list.item(i);
			NodeList hen_list = plus.getChildNodes();
			//System.out.println("hen_list:"+hen_list.getLength());
			for(int j=0;j<hen_list.getLength();j++){
				//System.out.println("j1:"+j);
				Element L_hen =(Element)hen_list.item(j);
				Element R_hen =(Element)hen_list.item(j+1);
				Node l = L_hen.getFirstChild().getFirstChild();
				Node r = R_hen.getFirstChild().getFirstChild();
				//String r = R_hen.getFirstChild().getFirstChild().getNodeValue();
				if(l.getNodeValue()!= null && r.getNodeValue()!= null){
					if((l.getNodeValue()).compareTo(r.getNodeValue())>0){//入れ替えを行う
						String tmp;
						
						tmp = l.getNodeValue();
						l.setNodeValue(r.getNodeValue());
						r.setNodeValue(tmp);
					
					}
				
					//System.out.println("compareTo:"+(l.getNodeValue()).compareTo(r.getNodeValue()));
				}
				//System.out.println("hen:"+l.getNodeValue());
				//System.out.println("hen:"+r.getNodeValue());
				//System.out.println("---------------------");
				j++;
				//System.out.println("j2:"+j);
			}
		}
		
		//分配法則が成り立つものの置き換え（乗算）
		NodeList multi_list = root.getElementsByTagName("MULTI");
		for(int i=0;i<multi_list.getLength();i++){
			Element multi = (Element)multi_list.item(i);
			Element LNode = (Element)multi.getFirstChild();//MULTIの左辺
			Element RNode = (Element)multi.getLastChild();//MULTIの右辺
			
			if(LNode.getElementsByTagName("PARENTHESIS").getLength() > 0){//左辺にカッコがある場合
				System.out.println("LNode:"+LNode.getElementsByTagName("PARENTHESIS").getLength());
			}
			if(RNode.getElementsByTagName("PARENTHESIS").getLength() > 0){//右辺にカッコがある場合
				System.out.println("RNode:"+RNode.getElementsByTagName("PARENTHESIS").getLength());
			}			
			//NodeList hen_list = multi.getElementsByTagName("PARENTHESIS");//カッコにつき１つ
			
		
		}
		//交換法則が成り立つものの置き換え（乗算）
		multi_list = root.getElementsByTagName("MULTI");
		for(int i=0;i<multi_list.getLength();i++){
			Element plus = (Element)multi_list.item(i);
			NodeList hen_list = plus.getChildNodes();
			//System.out.println("hen_list:"+hen_list.getLength());
			for(int j=0;j<hen_list.getLength();j++){
				//System.out.println("j1:"+j);
				Element L_hen =(Element)hen_list.item(j);
				Element R_hen =(Element)hen_list.item(j+1);
				Node l = L_hen.getFirstChild().getFirstChild();
				Node r = R_hen.getFirstChild().getFirstChild();
				//String r = R_hen.getFirstChild().getFirstChild().getNodeValue();
				if(l.getNodeValue()!= null && r.getNodeValue()!= null){
					if((l.getNodeValue()).compareTo(r.getNodeValue())>0){//入れ替えを行う
						String tmp;
						
						tmp = l.getNodeValue();
						l.setNodeValue(r.getNodeValue());
						r.setNodeValue(tmp);
					
					}
				
					//System.out.println("compareTo:"+(l.getNodeValue()).compareTo(r.getNodeValue()));
				}
				//System.out.println("hen:"+l.getNodeValue());
				//System.out.println("hen:"+r.getNodeValue());
				//System.out.println("---------------------");
				j++;
				//System.out.println("j2:"+j);
			}
		}
		
		//------------終：交換法則置き換え-----------------------------
		
		//doc.insertBefore(temp,old_n.getChildNodes());
		//引数部分の置換
		/*//forのテンプレートのルート要素を取得
	    Element temp_root = temp.getDocumentElement();
		NodeList par = temp_root.getElementsByTagName("PARENTHESIS");
		for(int i=0;i<par.getLength();i++){
			Element parent = (Element)par.item(i);
			NodeList exp_list = parent.getElementsByTagName("EXPRESSION");
			for(int j=0;j<exp_list.getLength();j++){
				Element express = (Element)exp_list.item(j);
				if(express.getAttribute("No").equals("1")){
					express.replaceChild(state1,express.getFirstChild());
				}
				if(express.getAttribute("No").equals("2")){
					//express.replaceChild(state2,express.getFirstChild());
				}
				if(express.getAttribute("No").equals("3")){
					//express.replaceChild(state3,express.getFirstChild());
				}
				//System.out.println("aa:"+express.getAttribute("No"));
			}
		}*/
		
		
		
	    /*} catch (Exception e) {
	    	e.printStackTrace();
	    }*/
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
		    }*/
		    
			
			return doc;
			    
	}
					
}
