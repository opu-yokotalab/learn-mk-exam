// $ANTLR 3.1.1 C:\\antlr\\C\\TreeAna.g 2008-11-17 12:58:29


import java.util.HashMap;
import java.util.Map;
import org.antlr.runtime.tree.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TreeAna extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SOURCE", "FUNC", "BODY", "DECLARATION", "EXPRESSION", "STATEMENT", "BLOCK", "ARGUMENTS", "RVALUE", "FOR", "ASSIGNMENT", "LVALUE", "LASSIGNMENT", "RASSIGNMENT", "ARRAY", "STRUCT", "WHILE", "DO", "SWITCH", "IF", "DOWHILE", "THEN", "ELSE", "BREAK", "CONTINUE", "NONE", "MEMBER", "CASE", "DEFAULT", "UNARYPLUS", "UNARYMINUS", "POSTFIXPLUS", "POSTFIXMINUS", "TYPE", "DECLARATOR", "CAST", "IDENTIFIER", "HEX_LITERAL", "OCTAL_LITERAL", "DECIMAL_LITERAL", "CHARACTER_LITERAL", "STRING_LITERAL", "FLOATING_POINT_LITERAL", "LETTER", "EscapeSequence", "HexDigit", "IntegerTypeSuffix", "Exponent", "FloatTypeSuffix", "OctalEscape", "UnicodeEscape", "WS", "COMMENT", "LINE_COMMENT", "LINE_COMMAND", "'typedef'", "';'", "','", "'='", "'extern'", "'static'", "'auto'", "'register'", "'void'", "'char'", "'short'", "'int'", "'long'", "'float'", "'double'", "'signed'", "'unsigned'", "'{'", "'}'", "'struct'", "'union'", "':'", "'enum'", "'const'", "'volatile'", "'('", "')'", "'['", "']'", "'*'", "'...'", "'+'", "'-'", "'/'", "'%'", "'++'", "'--'", "'sizeof'", "'.'", "'->'", "'&'", "'~'", "'!'", "'*='", "'/='", "'%='", "'+='", "'-='", "'<<='", "'>>='", "'&='", "'^='", "'|='", "'?'", "'||'", "'&&'", "'|'", "'^'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'<<'", "'>>'", "'case'", "'default'", "'if'", "'else'", "'switch'", "'while'", "'do'", "'for'", "'goto'", "'continue'", "'break'", "'return'"
    };
    public static final int CAST=39;
    public static final int WHILE=20;
    public static final int FloatTypeSuffix=52;
    public static final int LETTER=47;
    public static final int UNARYMINUS=34;
    public static final int CASE=31;
    public static final int DO=21;
    public static final int UNARYPLUS=33;
    public static final int EOF=-1;
    public static final int BREAK=27;
    public static final int STATEMENT=9;
    public static final int TYPE=37;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int STRING_LITERAL=45;
    public static final int T__90=90;
    public static final int FLOATING_POINT_LITERAL=46;
    public static final int RVALUE=12;
    public static final int BODY=6;
    public static final int COMMENT=56;
    public static final int T__99=99;
    public static final int T__98=98;
    public static final int ARRAY=18;
    public static final int T__97=97;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int T__136=136;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int ARGUMENTS=11;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int LINE_COMMENT=57;
    public static final int IntegerTypeSuffix=50;
    public static final int SWITCH=22;
    public static final int ELSE=26;
    public static final int CHARACTER_LITERAL=44;
    public static final int RASSIGNMENT=17;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int POSTFIXPLUS=35;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int DECLARATOR=38;
    public static final int T__126=126;
    public static final int T__125=125;
    public static final int T__128=128;
    public static final int T__127=127;
    public static final int WS=55;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__129=129;
    public static final int T__70=70;
    public static final int FUNC=5;
    public static final int NONE=29;
    public static final int LINE_COMMAND=58;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__130=130;
    public static final int EscapeSequence=48;
    public static final int DECIMAL_LITERAL=43;
    public static final int T__73=73;
    public static final int T__131=131;
    public static final int T__132=132;
    public static final int T__79=79;
    public static final int T__133=133;
    public static final int T__78=78;
    public static final int T__134=134;
    public static final int T__77=77;
    public static final int T__135=135;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int MEMBER=30;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__124=124;
    public static final int T__123=123;
    public static final int FOR=13;
    public static final int SOURCE=4;
    public static final int T__122=122;
    public static final int Exponent=51;
    public static final int T__121=121;
    public static final int T__120=120;
    public static final int T__61=61;
    public static final int T__60=60;
    public static final int DECLARATION=7;
    public static final int HexDigit=49;
    public static final int IF=23;
    public static final int THEN=25;
    public static final int T__107=107;
    public static final int CONTINUE=28;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int IDENTIFIER=40;
    public static final int T__103=103;
    public static final int T__59=59;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int T__113=113;
    public static final int T__112=112;
    public static final int HEX_LITERAL=41;
    public static final int EXPRESSION=8;
    public static final int POSTFIXMINUS=36;
    public static final int DEFAULT=32;
    public static final int OCTAL_LITERAL=42;
    public static final int STRUCT=19;
    public static final int T__102=102;
    public static final int T__101=101;
    public static final int T__100=100;
    public static final int LVALUE=15;
    public static final int UnicodeEscape=54;
    public static final int BLOCK=10;
    public static final int ASSIGNMENT=14;
    public static final int OctalEscape=53;
    public static final int LASSIGNMENT=16;
    public static final int DOWHILE=24;

    // delegates
    // delegators


        public TreeAna(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public TreeAna(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TreeAna.tokenNames; }
    public String getGrammarFileName() { return "C:\\antlr\\C\\TreeAna.g"; }


    private java.util.Map<String, Double> environment;



    // $ANTLR start "translation_unit"
    // C:\\antlr\\C\\TreeAna.g:20:1: translation_unit : ( external_declaration )+ ;
    public final void translation_unit() throws RecognitionException {

        	 if (input != null) System.out.println(input.toString());
        	 //System.out.println(input.toString(input.get(2),input.get(4)));
        	 System.out.println(input.get(6).toString());
        	 //input.getTreeAdaptor().addChild(input.getTreeAdaptor(),new ParseTree("Test"));
        	 //System.out.println(input.toString());
        	 //System.out.println(input.getCurrentSymbol().toString());
        	 Object a= new Object();
        	 input.replaceChildren(a = input.get(2),0,0,input.get(6));	 
        	 System.out.println(input.toString());
        	 System.out.println(a.toString());
        	 //input.replaceChildren(input.tree,0,0,new ParseTree(1));
        	 //if (input != null) System.out.println(input.toString());
        	 //ParseTree a= new ParseTree("$1");
        	 //System.out.println(a.toString());

        try {
            // C:\\antlr\\C\\TreeAna.g:37:2: ( ( external_declaration )+ )
            // C:\\antlr\\C\\TreeAna.g:37:3: ( external_declaration )+
            {
            // C:\\antlr\\C\\TreeAna.g:37:3: ( external_declaration )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==DECLARATION) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\antlr\\C\\TreeAna.g:37:4: external_declaration
            	    {
            	    pushFollow(FOLLOW_external_declaration_in_translation_unit53);
            	    external_declaration();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "translation_unit"


    // $ANTLR start "external_declaration"
    // C:\\antlr\\C\\TreeAna.g:43:1: external_declaration : ^( DECLARATION declaration ) ;
    public final void external_declaration() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:44:2: ( ^( DECLARATION declaration ) )
            // C:\\antlr\\C\\TreeAna.g:47:2: ^( DECLARATION declaration )
            {
            match(input,DECLARATION,FOLLOW_DECLARATION_in_external_declaration79); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_declaration_in_external_declaration81);
            declaration();

            state._fsp--;


            match(input, Token.UP, null); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "external_declaration"


    // $ANTLR start "function_definition"
    // C:\\antlr\\C\\TreeAna.g:50:1: function_definition : ;
    public final void function_definition() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:51:2: ()
            // C:\\antlr\\C\\TreeAna.g:60:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "function_definition"


    // $ANTLR start "declaration"
    // C:\\antlr\\C\\TreeAna.g:62:1: declaration : IDENTIFIER ;
    public final void declaration() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:63:2: ( IDENTIFIER )
            // C:\\antlr\\C\\TreeAna.g:64:2: IDENTIFIER
            {
            match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_declaration108); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declaration"


    // $ANTLR start "declaration_specifiers"
    // C:\\antlr\\C\\TreeAna.g:68:1: declaration_specifiers : ;
    public final void declaration_specifiers() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:69:2: ()
            // C:\\antlr\\C\\TreeAna.g:70:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declaration_specifiers"


    // $ANTLR start "init_declarator_list"
    // C:\\antlr\\C\\TreeAna.g:72:1: init_declarator_list : ;
    public final void init_declarator_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:73:2: ()
            // C:\\antlr\\C\\TreeAna.g:75:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "init_declarator_list"


    // $ANTLR start "init_declarator"
    // C:\\antlr\\C\\TreeAna.g:77:1: init_declarator : ;
    public final void init_declarator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:78:2: ()
            // C:\\antlr\\C\\TreeAna.g:82:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "init_declarator"


    // $ANTLR start "storage_class_specifier"
    // C:\\antlr\\C\\TreeAna.g:84:1: storage_class_specifier : ;
    public final void storage_class_specifier() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:85:2: ()
            // C:\\antlr\\C\\TreeAna.g:86:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "storage_class_specifier"


    // $ANTLR start "type_specifier"
    // C:\\antlr\\C\\TreeAna.g:88:1: type_specifier : ;
    public final void type_specifier() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:89:2: ()
            // C:\\antlr\\C\\TreeAna.g:90:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type_specifier"


    // $ANTLR start "type_id"
    // C:\\antlr\\C\\TreeAna.g:92:1: type_id : ;
    public final void type_id() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:93:2: ()
            // C:\\antlr\\C\\TreeAna.g:94:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type_id"


    // $ANTLR start "struct_or_union_specifier"
    // C:\\antlr\\C\\TreeAna.g:96:1: struct_or_union_specifier : ;
    public final void struct_or_union_specifier() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:97:2: ()
            // C:\\antlr\\C\\TreeAna.g:98:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "struct_or_union_specifier"


    // $ANTLR start "struct_or_union"
    // C:\\antlr\\C\\TreeAna.g:100:1: struct_or_union : ;
    public final void struct_or_union() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:101:2: ()
            // C:\\antlr\\C\\TreeAna.g:102:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "struct_or_union"


    // $ANTLR start "struct_declaration_list"
    // C:\\antlr\\C\\TreeAna.g:104:1: struct_declaration_list : ;
    public final void struct_declaration_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:105:2: ()
            // C:\\antlr\\C\\TreeAna.g:106:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "struct_declaration_list"


    // $ANTLR start "struct_declaration"
    // C:\\antlr\\C\\TreeAna.g:108:1: struct_declaration : ;
    public final void struct_declaration() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:109:2: ()
            // C:\\antlr\\C\\TreeAna.g:110:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "struct_declaration"


    // $ANTLR start "specifier_qualifier_list"
    // C:\\antlr\\C\\TreeAna.g:112:1: specifier_qualifier_list : ;
    public final void specifier_qualifier_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:113:2: ()
            // C:\\antlr\\C\\TreeAna.g:114:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "specifier_qualifier_list"


    // $ANTLR start "struct_declarator_list"
    // C:\\antlr\\C\\TreeAna.g:116:1: struct_declarator_list : ;
    public final void struct_declarator_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:117:2: ()
            // C:\\antlr\\C\\TreeAna.g:118:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "struct_declarator_list"


    // $ANTLR start "struct_declarator"
    // C:\\antlr\\C\\TreeAna.g:120:1: struct_declarator : ;
    public final void struct_declarator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:121:2: ()
            // C:\\antlr\\C\\TreeAna.g:122:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "struct_declarator"


    // $ANTLR start "enum_specifier"
    // C:\\antlr\\C\\TreeAna.g:124:1: enum_specifier : ;
    public final void enum_specifier() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:125:2: ()
            // C:\\antlr\\C\\TreeAna.g:126:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "enum_specifier"


    // $ANTLR start "enumerator_list"
    // C:\\antlr\\C\\TreeAna.g:128:1: enumerator_list : ;
    public final void enumerator_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:129:2: ()
            // C:\\antlr\\C\\TreeAna.g:130:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "enumerator_list"


    // $ANTLR start "enumerator"
    // C:\\antlr\\C\\TreeAna.g:132:1: enumerator : ;
    public final void enumerator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:133:2: ()
            // C:\\antlr\\C\\TreeAna.g:134:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "enumerator"


    // $ANTLR start "type_qualifier"
    // C:\\antlr\\C\\TreeAna.g:136:1: type_qualifier : ;
    public final void type_qualifier() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:137:2: ()
            // C:\\antlr\\C\\TreeAna.g:138:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type_qualifier"


    // $ANTLR start "declarator"
    // C:\\antlr\\C\\TreeAna.g:140:1: declarator : ;
    public final void declarator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:141:2: ()
            // C:\\antlr\\C\\TreeAna.g:142:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declarator"


    // $ANTLR start "direct_declarator"
    // C:\\antlr\\C\\TreeAna.g:144:1: direct_declarator : ;
    public final void direct_declarator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:145:2: ()
            // C:\\antlr\\C\\TreeAna.g:146:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "direct_declarator"


    // $ANTLR start "declarator_suffix"
    // C:\\antlr\\C\\TreeAna.g:148:1: declarator_suffix : ;
    public final void declarator_suffix() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:149:2: ()
            // C:\\antlr\\C\\TreeAna.g:150:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "declarator_suffix"


    // $ANTLR start "pointer"
    // C:\\antlr\\C\\TreeAna.g:152:1: pointer : ;
    public final void pointer() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:153:2: ()
            // C:\\antlr\\C\\TreeAna.g:154:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "pointer"


    // $ANTLR start "parameter_type_list"
    // C:\\antlr\\C\\TreeAna.g:156:1: parameter_type_list : ;
    public final void parameter_type_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:157:2: ()
            // C:\\antlr\\C\\TreeAna.g:158:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parameter_type_list"


    // $ANTLR start "parameter_list"
    // C:\\antlr\\C\\TreeAna.g:160:1: parameter_list : ;
    public final void parameter_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:161:2: ()
            // C:\\antlr\\C\\TreeAna.g:162:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parameter_list"


    // $ANTLR start "parameter_declaration"
    // C:\\antlr\\C\\TreeAna.g:164:1: parameter_declaration : ;
    public final void parameter_declaration() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:165:2: ()
            // C:\\antlr\\C\\TreeAna.g:166:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parameter_declaration"


    // $ANTLR start "identifier_list"
    // C:\\antlr\\C\\TreeAna.g:168:1: identifier_list : ;
    public final void identifier_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:169:2: ()
            // C:\\antlr\\C\\TreeAna.g:170:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "identifier_list"


    // $ANTLR start "type_name"
    // C:\\antlr\\C\\TreeAna.g:172:1: type_name : ;
    public final void type_name() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:173:2: ()
            // C:\\antlr\\C\\TreeAna.g:174:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "type_name"


    // $ANTLR start "abstract_declarator"
    // C:\\antlr\\C\\TreeAna.g:176:1: abstract_declarator : ;
    public final void abstract_declarator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:177:2: ()
            // C:\\antlr\\C\\TreeAna.g:178:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "abstract_declarator"


    // $ANTLR start "direct_abstract_declarator"
    // C:\\antlr\\C\\TreeAna.g:180:1: direct_abstract_declarator : ;
    public final void direct_abstract_declarator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:181:2: ()
            // C:\\antlr\\C\\TreeAna.g:182:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "direct_abstract_declarator"


    // $ANTLR start "abstract_declarator_suffix"
    // C:\\antlr\\C\\TreeAna.g:184:1: abstract_declarator_suffix : ;
    public final void abstract_declarator_suffix() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:185:2: ()
            // C:\\antlr\\C\\TreeAna.g:186:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "abstract_declarator_suffix"


    // $ANTLR start "initializer"
    // C:\\antlr\\C\\TreeAna.g:188:1: initializer : ;
    public final void initializer() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:189:2: ()
            // C:\\antlr\\C\\TreeAna.g:190:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "initializer"


    // $ANTLR start "initializer_list"
    // C:\\antlr\\C\\TreeAna.g:192:1: initializer_list : ;
    public final void initializer_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:193:2: ()
            // C:\\antlr\\C\\TreeAna.g:194:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "initializer_list"


    // $ANTLR start "argument_expression_list"
    // C:\\antlr\\C\\TreeAna.g:198:1: argument_expression_list : ;
    public final void argument_expression_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:199:2: ()
            // C:\\antlr\\C\\TreeAna.g:200:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "argument_expression_list"


    // $ANTLR start "additive_expression"
    // C:\\antlr\\C\\TreeAna.g:202:1: additive_expression : ;
    public final void additive_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:204:2: ()
            // C:\\antlr\\C\\TreeAna.g:205:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "additive_expression"


    // $ANTLR start "multiplicative_expression"
    // C:\\antlr\\C\\TreeAna.g:207:1: multiplicative_expression : ;
    public final void multiplicative_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:208:2: ()
            // C:\\antlr\\C\\TreeAna.g:209:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "multiplicative_expression"


    // $ANTLR start "cast_expression"
    // C:\\antlr\\C\\TreeAna.g:211:1: cast_expression : ;
    public final void cast_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:212:2: ()
            // C:\\antlr\\C\\TreeAna.g:213:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "cast_expression"


    // $ANTLR start "unary_expression"
    // C:\\antlr\\C\\TreeAna.g:215:1: unary_expression : ;
    public final void unary_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:216:2: ()
            // C:\\antlr\\C\\TreeAna.g:217:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "unary_expression"


    // $ANTLR start "postfix_expression"
    // C:\\antlr\\C\\TreeAna.g:219:1: postfix_expression : ;
    public final void postfix_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:220:2: ()
            // C:\\antlr\\C\\TreeAna.g:221:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "postfix_expression"


    // $ANTLR start "unary_operator"
    // C:\\antlr\\C\\TreeAna.g:223:1: unary_operator : ;
    public final void unary_operator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:224:2: ()
            // C:\\antlr\\C\\TreeAna.g:225:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "unary_operator"


    // $ANTLR start "primary_expression"
    // C:\\antlr\\C\\TreeAna.g:227:1: primary_expression : ;
    public final void primary_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:228:2: ()
            // C:\\antlr\\C\\TreeAna.g:229:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "primary_expression"


    // $ANTLR start "constant"
    // C:\\antlr\\C\\TreeAna.g:231:1: constant : ;
    public final void constant() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:232:5: ()
            // C:\\antlr\\C\\TreeAna.g:233:5: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "constant"


    // $ANTLR start "expression"
    // C:\\antlr\\C\\TreeAna.g:237:1: expression : ;
    public final void expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:238:2: ()
            // C:\\antlr\\C\\TreeAna.g:239:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expression"


    // $ANTLR start "constant_expression"
    // C:\\antlr\\C\\TreeAna.g:241:1: constant_expression : ;
    public final void constant_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:242:2: ()
            // C:\\antlr\\C\\TreeAna.g:243:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "constant_expression"


    // $ANTLR start "assignment_expression"
    // C:\\antlr\\C\\TreeAna.g:245:1: assignment_expression : ;
    public final void assignment_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:246:2: ()
            // C:\\antlr\\C\\TreeAna.g:247:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "assignment_expression"


    // $ANTLR start "lvalue"
    // C:\\antlr\\C\\TreeAna.g:249:1: lvalue : ;
    public final void lvalue() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:250:2: ()
            // C:\\antlr\\C\\TreeAna.g:251:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "lvalue"


    // $ANTLR start "assignment_operator"
    // C:\\antlr\\C\\TreeAna.g:253:1: assignment_operator : ;
    public final void assignment_operator() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:254:2: ()
            // C:\\antlr\\C\\TreeAna.g:255:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "assignment_operator"


    // $ANTLR start "conditional_expression"
    // C:\\antlr\\C\\TreeAna.g:257:1: conditional_expression : ;
    public final void conditional_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:258:2: ()
            // C:\\antlr\\C\\TreeAna.g:259:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "conditional_expression"


    // $ANTLR start "logical_or_expression"
    // C:\\antlr\\C\\TreeAna.g:261:1: logical_or_expression : ;
    public final void logical_or_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:262:2: ()
            // C:\\antlr\\C\\TreeAna.g:263:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "logical_or_expression"


    // $ANTLR start "logical_and_expression"
    // C:\\antlr\\C\\TreeAna.g:265:1: logical_and_expression : ;
    public final void logical_and_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:267:2: ()
            // C:\\antlr\\C\\TreeAna.g:268:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "logical_and_expression"


    // $ANTLR start "inclusive_or_expression"
    // C:\\antlr\\C\\TreeAna.g:270:1: inclusive_or_expression : ;
    public final void inclusive_or_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:272:2: ()
            // C:\\antlr\\C\\TreeAna.g:273:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "inclusive_or_expression"


    // $ANTLR start "exclusive_or_expression"
    // C:\\antlr\\C\\TreeAna.g:275:1: exclusive_or_expression : ;
    public final void exclusive_or_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:277:2: ()
            // C:\\antlr\\C\\TreeAna.g:278:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "exclusive_or_expression"


    // $ANTLR start "and_expression"
    // C:\\antlr\\C\\TreeAna.g:280:1: and_expression : ;
    public final void and_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:282:2: ()
            // C:\\antlr\\C\\TreeAna.g:283:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "and_expression"


    // $ANTLR start "equality_expression"
    // C:\\antlr\\C\\TreeAna.g:284:1: equality_expression : ;
    public final void equality_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:286:2: ()
            // C:\\antlr\\C\\TreeAna.g:287:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "equality_expression"


    // $ANTLR start "relational_expression"
    // C:\\antlr\\C\\TreeAna.g:289:1: relational_expression : ;
    public final void relational_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:291:2: ()
            // C:\\antlr\\C\\TreeAna.g:292:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "relational_expression"


    // $ANTLR start "shift_expression"
    // C:\\antlr\\C\\TreeAna.g:294:1: shift_expression : ;
    public final void shift_expression() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:296:2: ()
            // C:\\antlr\\C\\TreeAna.g:297:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "shift_expression"


    // $ANTLR start "statement"
    // C:\\antlr\\C\\TreeAna.g:301:1: statement : ;
    public final void statement() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:302:2: ()
            // C:\\antlr\\C\\TreeAna.g:303:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "labeled_statement"
    // C:\\antlr\\C\\TreeAna.g:305:1: labeled_statement : ;
    public final void labeled_statement() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:306:2: ()
            // C:\\antlr\\C\\TreeAna.g:307:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "labeled_statement"


    // $ANTLR start "compound_statement"
    // C:\\antlr\\C\\TreeAna.g:309:1: compound_statement : ;
    public final void compound_statement() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:310:2: ()
            // C:\\antlr\\C\\TreeAna.g:311:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "compound_statement"


    // $ANTLR start "statement_list"
    // C:\\antlr\\C\\TreeAna.g:313:1: statement_list : ;
    public final void statement_list() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:314:2: ()
            // C:\\antlr\\C\\TreeAna.g:315:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statement_list"


    // $ANTLR start "expression_statement"
    // C:\\antlr\\C\\TreeAna.g:317:1: expression_statement : ;
    public final void expression_statement() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:318:2: ()
            // C:\\antlr\\C\\TreeAna.g:319:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expression_statement"


    // $ANTLR start "selection_statement"
    // C:\\antlr\\C\\TreeAna.g:321:1: selection_statement : ;
    public final void selection_statement() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:322:2: ()
            // C:\\antlr\\C\\TreeAna.g:323:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "selection_statement"


    // $ANTLR start "iteration_statement"
    // C:\\antlr\\C\\TreeAna.g:325:1: iteration_statement : ;
    public final void iteration_statement() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:326:2: ()
            // C:\\antlr\\C\\TreeAna.g:327:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "iteration_statement"


    // $ANTLR start "jump_statement"
    // C:\\antlr\\C\\TreeAna.g:329:1: jump_statement : ;
    public final void jump_statement() throws RecognitionException {
        try {
            // C:\\antlr\\C\\TreeAna.g:330:2: ()
            // C:\\antlr\\C\\TreeAna.g:331:2: 
            {
            }

        }
        finally {
        }
        return ;
    }
    // $ANTLR end "jump_statement"

    // Delegated rules


 

    public static final BitSet FOLLOW_external_declaration_in_translation_unit53 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_DECLARATION_in_external_declaration79 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_declaration_in_external_declaration81 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENTIFIER_in_declaration108 = new BitSet(new long[]{0x0000000000000002L});

}