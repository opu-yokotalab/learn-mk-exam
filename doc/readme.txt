
#blank
antlrフォルダ	…ANTLRをDLしてPATHを通せば不要
Check.java	…XML化を行っているところ
CtreeLexer.java	…字句解析をしているところ(ANTLRで生成)
CtreeParser.java	…構文解析をしているところ(同上)
Dom.java	…木の操作をしているところ(シンボルテーブル割り当てと問題作成)
Make.java	…インタフェースとメイン操作を記述しているところ(swingで)。実質main
			…（ＤＢへのアクセス）、ＸＭＬへの書き込み、ソケット通信などはこの関数
			…ソケット通信での接続先（emerald.c.oka-pu.ac.jp,3000)
			…ＤＢへのアクセスでの接続先（ホスト：ローカル,ＤＢ名：problem_DB,テーブル名:question)
QuestionMap.java	…作った問題を保持しているところ
SymbolTable.java	…シンボルテーブルの情報を保持しているところ
XSLT.java	…xslt変換を行っているところ
convert.xsl	…Cソースへの変換を行っているXSLTファイル(インデントはまだ)
section.ini	…章構成を書くところ
Ctree.g		…構文解析用文法ファイル。編集にはANTLRが必要
その他		…ANTLRで自動生成されたり没になったファイルだったりり色々。整理します。

※出力時にエラーが起こるようです。原因解明中。