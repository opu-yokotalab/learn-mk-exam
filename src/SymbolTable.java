import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  �V���{���e�[�u����������茟������N���X
  �񔼕��R�s�y�B
  �]���s���������ɗL��B(�\�z)
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
    identity.add(id);  //0�Ԗڂ̗v�f��id
    identity.add(new HashMap());  //1�Ԗڂ̗v�f�̓����o�̃}�b�v
    struct.put(name,identity);  //name���L�[�Ƀ��X�g���֘A�t��
  }
  
  public void declareMember(String structname,String name,String id){  //name�̓����o�̖��O(�����o�̐錾)
    ((HashMap)((ArrayList)struct.get(structname)).get(1)).put(name,id);
  }

  public void declareMember(String structname,String name,String id,String memberstructname){  //name�̓����o�̖��O,memberstructname�͍\���̌^(�H)�����o�̍\���̖�
    ((HashMap)((ArrayList)struct.get(structname)).get(1)).put(name,id);
    structtype.put(id,memberstructname);  //�����o��id���L�[�ɁA�����o�̍\����(�^)�����֘A�t��(�\���̂̍\����)
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

  public String searchSymbol(String name){  //name(�ϐ���)���������id��Ԃ�
    for(int i=scopes.size()-1 ; i>=0 ; i--){
      Map scope = (Map)scopes.get(i);
      if(scope.containsKey(name))
        return scope.get(name).toString();
    }
    return null;
  }

  public String searchFunction(String name){  //name(�֐���)���������id��Ԃ�
    if(functions.containsKey(name))
      return functions.get(name).toString();
    else
      return null;
  }

  public String searchStructname(String name){  //name(�\���̖�)���������id��Ԃ�
    if(struct.containsKey(name)){
      return ((ArrayList)struct.get(name)).get(0).toString(); 
    }else{
      return null;
    }
  }

  public String searchStructmember(String name,String member){  //�\����name�̃����omember��id��Ԃ�
      return ((Map)((Map)((ArrayList)struct.get(name)).get(1))).get(member).toString();
  }

  public String searchStructType(String name){  //�����o�̖��O���炻�̍\���̌^����Ԃ�(�\���̂̍\����)
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
