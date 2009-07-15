import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  シンボルテーブルを作ったり検索するクラス
  約半分コピペ。
  余分不足分多分に有り。(予想)
*/

public class SymbolTable {
	static final int GLOBALS = 0;
	static final int PARAMS = 1;
	Map		functions = new HashMap();
	List	scopes = new ArrayList();
	Map		globals = new HashMap();
	Map		params = new HashMap();
  Map struct = new HashMap();
  Map structtype = new HashMap();
  Map type = new HashMap();
	
	int		globalVarNum = 1;
	int		paramVarNum = 0;
	int		localVarNum = 0;
	int		LabelNum = 0;

	public SymbolTable() {
		scopes.add(globals);
		scopes.add(params);
	}
	
	public int nextLabelNum() {
		return (LabelNum++);
	}
	
	public void enterFunction() {
		enterBlock();
	}
	
	public void exitFunction() {
		exitBlock();
		params.clear();
	}
	
	public void enterBlock() {
		scopes.add(new HashMap());
	}
  
  public void makeStruct(String name,String id){
    List identity = new ArrayList();
    identity.add(id);  //0番目の要素はid
    identity.add(new HashMap());  //1番目の要素はメンバのマップ
    struct.put(name,identity);  //nameをキーにリストを関連付け
  }
  
  public void declareMember(String structname,String name,String id){  //nameはメンバの名前(メンバの宣言)
    ((HashMap)((ArrayList)struct.get(structname)).get(1)).put(name,id);
  }

  public void declareMember(String structname,String name,String id,String memberstructname){  //nameはメンバの名前,memberstructnameは構造体型(？)メンバの構造体名
    ((HashMap)((ArrayList)struct.get(structname)).get(1)).put(name,id);
    structtype.put(id,memberstructname);  //メンバのidをキーに、メンバの構造体(型)名を関連付け(構造体の構造体)
  }
	public void exitBlock() {
		int lastIndex = scopes.size()-1;
		if (lastIndex > PARAMS) {
			Map scope = (Map)scopes.get(lastIndex);
			localVarNum -= scope.size();
			scopes.remove(lastIndex);
		}		
	}
	
	public void declareFunction(String name, String id) {
		functions.put(name, id);
	}
	
	public void declareGlobalVariable(String name, String id) {
		globals.put(name, id);
	}
	
	public void declareLocalVariable(String name, String id) {
		Map scope = (Map)scopes.get(scopes.size()-1);
		scope.put(name, id);
	}

	public void declareLocalVariable(String name, String id,String stname) {
		Map scope = (Map)scopes.get(scopes.size()-1);
           structtype.put(id,stname);
		scope.put(name,id);
	}

	public void declareParameter(String name, String id) {
		params.put(name, id);
	}

  	public void declareParameter(String name, String id,String stname) {
		Map scope = (Map)scopes.get(scopes.size()-1);
          structtype.put(id,stname);
		scope.put(name,id);
	}

  public void defineType(String name,String id){
    type.put(id,name);
  }

  public String searchSymbol(String name){  //name(変数名)をもらってidを返す
    for(int i=scopes.size()-1 ; i>=0 ; i--){
      Map scope = (Map)scopes.get(i);
      if(scope.containsKey(name))
        return scope.get(name).toString();
    }
    return null;
  }

  public String searchFunction(String name){  //name(関数名)をもらってidを返す
    if(functions.containsKey(name))
      return functions.get(name).toString();
    else
      return null;
  }

  public String searchStructname(String name){  //name(構造体名)をもらってidを返す
    if(struct.containsKey(name)){
      return ((ArrayList)struct.get(name)).get(0).toString(); 
    }else{
      return null;
    }
  }

  public String searchStructmember(String name,String member){  //構造体nameのメンバmemberのidを返す
      return ((Map)((Map)((ArrayList)struct.get(name)).get(1))).get(member).toString();
  }

  public String searchStructType(String name){  //メンバの名前からその構造体型名を返す(構造体の構造体)
    String id;
    id=searchSymbol(name);
    /*if(null == id)
      id = name;*/
    return structtype.get(id).toString();
  }

  public String searchStructTypeById(String id){
    return structtype.get(id).toString();
  }

  public String searchType(String name){
    String typeid = type.get(name).toString();
    return typeid;
  }
}
