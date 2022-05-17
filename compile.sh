#!/bin/zsh

JFLEX="./jars/JFlex.jar"
CUP="./jars/java-cup-11a.jar"
CLASSPATH=".:./jars/JFlex.jar:./jars/java-cup-11a.jar"

java -classpath $JFLEX  JFlex.Main dsl.lex
java -classpath $CUP java_cup.Main dsl.cup
cp Yylex.java  UncertainOCL 
cp sym.java    UncertainOCL
cp parser.java UncertainOCL 
javac -classpath $CLASSPATH   UncertainOCL/sym.java 
javac -classpath $CUP:.       UncertainOCL/parser.java
javac -classpath $CLASSPATH:. UncertainOCL/Yylex.java

javac -d . -classpath $CLASSPATH UncertainOCL/Alternative.java
javac -d . -classpath $CLASSPATH UncertainOCL/Concretization.java
