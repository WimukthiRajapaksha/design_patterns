/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author wimukthirajapaksha
 */

interface Element {
    int eval();
}

class Integer implements Element {
    private int value;

    public Integer(int value) {
        this.value = value;
    }
    
    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element {
    
    public enum  Type {
        ADDITION, SUBTRACTION
    }
    
    public Type type;
    public Element left, right;

    @Override
    public int eval() {
        switch(type){
            case ADDITION:
                return left.eval()+right.eval();
            case SUBTRACTION:
                return left.eval()-right.eval();
            default:
                return 0;
        }
    }
}

class Token {
    public enum Type {
        INTEGER, PLUES, MINUS, LPAREN, RPAREN
    }
    
    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

public class Interpreter {

    /**
     * @param args the command line arguments
     */
    
    static List<Token> lex(String input) {
        List<Token> result=new ArrayList<>();
        for(int i=0; i<input.length(); i++) {
            switch(input.charAt(i)) {
            case '+':
                result.add(new Token(Token.Type.PLUES, "+"));
                break;
            case '-':
                result.add(new Token(Token.Type.MINUS, "-"));
                break;
            case '(':
                result.add(new Token(Token.Type.LPAREN, "("));
                break;
            case ')':
                result.add(new Token(Token.Type.RPAREN, ")"));
                break;
            default:
                StringBuilder sb=new StringBuilder();
                for(int j=i; j<input.length(); j++) {
                    if(Character.isDigit(input.charAt(j))) {
                        sb.append(input.charAt(j));
                        i=j;
                    } else {
                        break;
                    }
                }
                result.add(new Token(Token.Type.INTEGER, sb.toString()));
                break;
            }
        }
        return result;
    }
    
    static Element parse(List<Token> tokens) {
        BinaryOperation result=new BinaryOperation();
        boolean haveLHS=false;
        for(int i=0; i<tokens.size(); ++i) {
            Token t=tokens.get(i);
            switch(t.type) {
                case INTEGER:
                    Integer integer=new Integer(java.lang.Integer.parseInt(t.text));
                    if(!haveLHS) {
                        result.left=integer;
                        haveLHS=true;
                    } else result.right=integer;
                    break;
                case PLUES:
                    result.type=BinaryOperation.Type.ADDITION;
                    break;
                case MINUS:
                    result.type=BinaryOperation.Type.SUBTRACTION;
                    break;
                case LPAREN:
                    int j=i;
                    for(; j<tokens.size(); ++j) {
                        if(tokens.get(j).type==Token.Type.RPAREN) {
                            break;
                        }
                    }
                    List<Token> list=tokens.stream().skip(i+1).limit(j-i-1).collect(Collectors.toList());
                    Element element=parse(list);
                    if(!haveLHS) {
                        result.left=element;
                        haveLHS=true;
                    } else {
                        result.right=element;
                    }
                    i=j;
                    break;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        String input = "(13+4)-(12+1)";
        List<Token> tokens=lex(input);
        System.out.println(tokens);
        System.out.println(tokens.stream().map(t->t.toString()).collect(Collectors.joining("\t")));
        
        Element passed=parse(tokens);
        System.out.println(input+" = "+passed.eval());
    }
    
}
