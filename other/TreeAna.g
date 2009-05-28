tree grammar TreeAna;


options {
  tokenVocab = Ctree;
  ASTLabelType = CommonTree;
}

@header {

import java.util.HashMap;
import java.util.Map;
import org.antlr.runtime.tree.*;
}

@members {
private java.util.Map<String, Double> environment;
}

translation_unit
@init{
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
}
	:(external_declaration
	//{ if (input != null) System.out.println(input.toString());}
	)+
	
	;
	
external_declaration
	:
	//function_definition
	//|
	^(DECLARATION declaration)
	;
	
function_definition
	:
	/*^(FUNC ^(declaration_specifiers declarator declaration+) compound_statement)// K&R style
	|
	^(FUNC ^(declaration_specifiers declarator) compound_statement)// ANSI style
	|
	^(FUNC declarator declaration+ compound_statement)// K&R style
	|
	^(FUNC declarator compound_statement)// ANSI style
	*/
	;
	
declaration
	:
	IDENTIFIER
	//^(declaration_specifiers init_declarator_list?)
	;
	
declaration_specifiers
	:
	;
	
init_declarator_list
	:
	//init_declarator+
	;
	
init_declarator
	:
	/*^(ASSIGNMENT ^(LASSIGNMENT declarator) ^(RASSIGNMENT initializer))
	|
	declarator*/
	;
	
storage_class_specifier
	:
	;
	
type_specifier
	:
	;
	
type_id
	:
	;
	
struct_or_union_specifier
	:
	;	

struct_or_union
	:
	;
	
struct_declaration_list
	:
	;
	
struct_declaration
	:
	;
	
specifier_qualifier_list
	:
	;
	
struct_declarator_list
	:
	;
	
struct_declarator
	:
	;
	
enum_specifier
	:
	;
	
enumerator_list
	:
	;
	
enumerator
	:
	;
	
type_qualifier
	: 
	;

declarator
	: 
	;

direct_declarator
	:
	;

declarator_suffix
	:
	;

pointer
	:
	;

parameter_type_list
	:
	;

parameter_list
	:
	;

parameter_declaration
	:
	;

identifier_list
	: 
	;

type_name
	:
	;

abstract_declarator
	: 
	;

direct_abstract_declarator
	:
	;

abstract_declarator_suffix
	:
	;
	
initializer
	:
	;

initializer_list
	:
	;

// E x p r e s s i o n s

argument_expression_list
	: 
	;

additive_expression
//	: (multiplicative_expression) ('+' multiplicative_expression | '-' multiplicative_expression)*
	: 
	;

multiplicative_expression
	:
	;

cast_expression
	:
	;

unary_expression
	:
	;

postfix_expression
	:
	;

unary_operator
	:
	;

primary_expression
	:
	;

constant
    :
    ;

/////

expression
	:
	;

constant_expression
	: 
	;

assignment_expression
	:
	;
	
lvalue
	:
	;

assignment_operator
	: 
	;

conditional_expression
	:
	;

logical_or_expression
	:
	;

logical_and_expression
//	: inclusive_or_expression ('&&' inclusive_or_expression)*
	:
	;

inclusive_or_expression
//	: exclusive_or_expression ('|' exclusive_or_expression)*
	:
	;

exclusive_or_expression
//	: and_expression ('^' and_expression)*
	:
	;

and_expression
//	: equality_expression ('&' equality_expression)*
	:
	;
equality_expression
//	: relational_expression (('=='|'!=') relational_expression)*
	:
	;

relational_expression
//	: shift_expression (('<'|'>'|'<='|'>=') shift_expression)*
	:
	;

shift_expression
//	: additive_expression (('<<'|'>>') additive_expression)*
	:
	;

// S t a t e m e n t s

statement
	:
	;

labeled_statement
	:
	;

compound_statement
	:
	;

statement_list
	:
	;

expression_statement
	:
	;

selection_statement
	: 
	;

iteration_statement
	: 
	;

jump_statement
	: 
	;
