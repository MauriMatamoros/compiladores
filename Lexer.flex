import java_cup.runtime.*;
%%

%cup
%class Lexer
%unicode
%standalone
%line
%column
%char
%cupdebug
%ignorecase

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    System.out.println("<" + sym.terminalNames[type] + ">");
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    System.out.println("<" + sym.terminalNames[type] + ", " + (String)value + ">");
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

digit = [0-9]
integer = {digit}+
letter = [a-z]|[A-Z]
id = {letter}({letter}|{digit}|"_")*
typeInteger = "Integer"
typeBoolean = "Boolean"
typeString = "String"
typeChar = "Char"
type = {typeInteger}|{typeBoolean}|{typeString}|{typeChar}
quote ="\'"
doubleQuote = "\""
BooleanTrue = "True"
BooleanFalse = "False"

//Relational Operators
lessThan = "<"
greatherThan = ">"
lessOrEqualThan = "<="
greaterOrEqualThan = ">="
notEqualTo = "<>"
equalTo = "="

relationalOperator = {lessThan} | {greatherThan} | {lessOrEqualThan} | {greaterOrEqualThan} | {notEqualTo} | {equalTo}

//Logical Operators
and = "AND"
or = "OR"
not = "NOT"

//Arithmetical Operators
multiplication = "*"
division = "/"
addition = "+"
substraction = "-"
mod = "Mod"
multiplicationOperators = {multiplication}|{division}
additionOperators = {addition}|{substraction}

//Reserved Words
dim = "dim"
as = "As"
static = "Static"
end = "End"
endFunction = "End Function"
print = "Print"
private = "Private"
public = "Public"
sub = "Sub"
endSub = "End Sub"
function = "Function"
openParenthesis = "("
closeParenthesis = ")"
readLine = "ReadLine"
period = "."
coma = ","


//IF
if = "If"
then = "Then"
else = "Else"
elseif = "ElseIf"
endIf = "End If"

//While
do = "Do"
loopWhile = "Loop While"

//FOR
for = "For"
to = "To"
next = "Next"

endOfLine = (\r|\n|\r\n)+
whiteSpace =  (\t|\f|" ")+

char = {doubleQuote}({letter}|{digit}){doubleQuote}
string = {doubleQuote}({letter}|{digit})*{doubleQuote}
comment = {quote}[^\r\n]*{endOfLine}
Main = "Sub Main()"

%state STRING
%%

<YYINITIAL> {
  {Main}                     { return symbol(sym.MAIN); }
  {BooleanTrue}              { return symbol(sym.TRUE); }
  {BooleanFalse}             { return symbol(sym.FALSE); }
  {coma}                     { return symbol(sym.COMA); }
  {period}                   { return symbol(sym.PERIOD); }
  {not}                      { return symbol(sym.NOT); }
  {readLine}                 { return symbol(sym.READLINE); }
  {endSub}                   { return symbol(sym.ENDSUB); }
  {endIf}                    { return symbol(sym.ENDIF); }
  {endFunction}              { return symbol(sym.ENDFUNCTION); }
  {comment}                  {}
  {type}                     { return symbol(sym.TYPE ,yytext()); }
  {mod}                      { return symbol(sym.MOD); }
  {integer}                  { return symbol(sym.INTEGER ,yytext()); }
  {relationalOperator}       { return symbol(sym.RELATIONALOPERATOR, yytext()); }
  {multiplicationOperators}  { return symbol(sym.MULTIPLICATIONOPERATOR, yytext()); }
  {additionOperators}        { return symbol(sym.ADDITIONOPERATOR, yytext()); }
  {and}                      { return symbol(sym.AND); }
  {or}                       { return symbol(sym.OR); }
  {dim}                      { return symbol(sym.DIM); }
  {as}                       { return symbol(sym.AS); }
  {static}                   { return symbol(sym.STATIC); }
  {print}                    { return symbol(sym.PRINT); }
  {private}                  { return symbol(sym.PRIVATE); }
  {public}                   { return symbol(sym.PUBLIC); }
  {sub}                      { return symbol(sym.SUB); }
  {if}                       { return symbol(sym.IF); }
  {then}                     { return symbol(sym.THEN); }
  {else}                     { return symbol(sym.ELSE); }
  {elseif}                   { return symbol(sym.ELSEIF); }
  {do}                       { return symbol(sym.DO); }
  {loopWhile}                { return symbol(sym.LOOPWHILE); }
  {for}                      { return symbol(sym.FOR); }
  {to}                       { return symbol(sym.TO); }
  {next}                     { return symbol(sym.NEXT); }
  {function}                 { return symbol(sym.FUNCTION); }
  {openParenthesis}          { return symbol(sym.OPENPARENTHESIS); }
  {closeParenthesis}         { return symbol(sym.CLOSEPARENTHESIS); }
  {char}                     { return symbol(sym.CHAR ,yytext()); }
  {doubleQuote}              { string.setLength(0); yybegin(STRING);}
  {whiteSpace}               {}
  {endOfLine}                { return symbol(sym.ENDOFLINE); }
  {id}                       { return symbol(sym.ID ,yytext()); }
}

<STRING> {
  {doubleQuote}              {yybegin(YYINITIAL);
                              return symbol(sym.STRING, string.toString());
                             }
  .                          {string.append(yytext());}
}

[^]                          {System.out.println("Error en la linea: " + yyline+1 + " Col " + yycolumn+1 + " El texto fue " + yytext());}
