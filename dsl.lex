package UncertainOCL;


import java_cup.runtime.Symbol;
%%
%cup
%type Symbol

%%
"--"[^\r\n]*(\r|\n|\r\n) { /* Ignore OCL comments */ }
"[?"        { return new Symbol(sym.BEG_UNCERTAIN_INV); }
"["         { return new Symbol(sym.BEG_UNCERTAIN_ALT); }
"]"         { return new Symbol(sym.END_UNCERTAIN); }
","         { return new Symbol(sym.COMMA); }
"unknown"   { return new Symbol(sym.UNKNOWN); }
"*"[a-zA-Z_]([a-zA-Z0-9_])*"*" {  String label = yytext();
                                  label = label.substring(1, label.length()-1); 
                                  return new Symbol(sym.LABEL, label); }

"context"    { return new Symbol(sym.CONTEXT);}
"inv"        { return new Symbol(sym.INVARIANT); }
"pre"        { return new Symbol(sym.PRECONDITION); }
"post"       { return new Symbol(sym.POSTCONDITION); }
"let"        { return new Symbol(sym.LET); }
"in"         { return new Symbol(sym.IN); }
"body"       { return new Symbol(sym.BODY); }
"and"        { return new Symbol(sym.AND); }
"or"         { return new Symbol(sym.OR); }
"xor"		 { return new Symbol(sym.XOR); }
"not"        { return new Symbol(sym.NOT); }
"implies"    { return new Symbol(sym.IMPLIES); }
"if"         { return new Symbol(sym.IF); }
"then"       { return new Symbol(sym.THEN); }
"else"       { return new Symbol(sym.ELSE); }
"endif"      { return new Symbol(sym.ENDIF); }
":"          { return new Symbol(sym.SEMICOLON); }
"." 		 { return new Symbol(sym.DOT); }
".."         { return new Symbol(sym.RANGE); }
"->"         { return new Symbol(sym.ARROW); }
"|"          { return new Symbol(sym.BAR); }
"("          { return new Symbol(sym.LPAREN); }
")" 		 { return new Symbol(sym.RPAREN); }
"{"          { return new Symbol(sym.BEG_KEY); }
"}"          { return new Symbol(sym.END_KEY); }
"+"          { return new Symbol(sym.PLUS); }
"-"          { return new Symbol(sym.MINUS); }
"/"          { return new Symbol(sym.DIV); }
"*" 		 { return new Symbol(sym.STAR); }
"<"          { return new Symbol(sym.LT); }
">"          { return new Symbol(sym.GT); }
"<="         { return new Symbol(sym.LEQ); }
">="         { return new Symbol(sym.GEQ); }
"="          { return new Symbol(sym.EQ); }
"<>"         { return new Symbol(sym.NEQ); }
\"[^\"]*\"   { return new Symbol(sym.STRING_LIT, yytext()); }
[0-9]+(\.([0-9]+))? { return new Symbol(sym.NUMBER_LIT, yytext()); }

[a-zA-Z_]([a-zA-Z0-9_])* { return new Symbol(sym.IDENT, yytext()); }
[ \t\r\n\f]  { /* ignore white space. */ }
. { System.err.println("Illegal character: " + yytext()); }
