/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wimukthirajapaksha
 */

class MyStringBuilder {
    private StringBuilder sb;

    public MyStringBuilder() {
        sb=new StringBuilder();
    }

    public MyStringBuilder(String str) {
        this.sb = new StringBuilder(str);
    }
    
    // additional decorator method
    public MyStringBuilder concat(String s) {
        return new MyStringBuilder(sb.toString().concat(s));
    }
    
    public MyStringBuilder appendLine(String s) {
        sb.append(s).append(System.lineSeparator());
        return this;
    }

    public String toString() {
        return sb.toString();
    }

    // decorator, problem here
//    public StringBuilder append(Object obj) {
//        return sb.append(obj);
//    }
    
    public MyStringBuilder append(Object obj) {
        sb.append(obj);
        return this;
    }

//    public StringBuilder append(String str) {
//        return sb.append(str);
//    }
    
    public MyStringBuilder append(String str) {
        sb.append(str);
        return this;
    }

    public StringBuilder append(StringBuffer sb) {
        return this.sb.append(sb);
    }

    public StringBuilder append(CharSequence s) {
        return sb.append(s);
    }

    public StringBuilder append(CharSequence s, int start, int end) {
        return sb.append(s, start, end);
    }

    public StringBuilder append(char[] str) {
        return sb.append(str);
    }

    public StringBuilder append(char[] str, int offset, int len) {
        return sb.append(str, offset, len);
    }

    public StringBuilder append(boolean b) {
        return sb.append(b);
    }

    public StringBuilder append(char c) {
        return sb.append(c);
    }

    public StringBuilder append(int i) {
        return sb.append(i);
    }

    public StringBuilder append(long lng) {
        return sb.append(lng);
    }

    public StringBuilder append(float f) {
        return sb.append(f);
    }

    public StringBuilder append(double d) {
        return sb.append(d);
    }

    public StringBuilder appendCodePoint(int codePoint) {
        return sb.appendCodePoint(codePoint);
    }

    public StringBuilder delete(int start, int end) {
        return sb.delete(start, end);
    }

    public StringBuilder deleteCharAt(int index) {
        return sb.deleteCharAt(index);
    }

    public StringBuilder replace(int start, int end, String str) {
        return sb.replace(start, end, str);
    }

    public StringBuilder insert(int index, char[] str, int offset, int len) {
        return sb.insert(index, str, offset, len);
    }

    public StringBuilder insert(int offset, Object obj) {
        return sb.insert(offset, obj);
    }

    public StringBuilder insert(int offset, String str) {
        return sb.insert(offset, str);
    }

    public StringBuilder insert(int offset, char[] str) {
        return sb.insert(offset, str);
    }

    public StringBuilder insert(int dstOffset, CharSequence s) {
        return sb.insert(dstOffset, s);
    }

    public StringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        return sb.insert(dstOffset, s, start, end);
    }

    public StringBuilder insert(int offset, boolean b) {
        return sb.insert(offset, b);
    }

    public StringBuilder insert(int offset, char c) {
        return sb.insert(offset, c);
    }

    public StringBuilder insert(int offset, int i) {
        return sb.insert(offset, i);
    }

    public StringBuilder insert(int offset, long l) {
        return sb.insert(offset, l);
    }

    public StringBuilder insert(int offset, float f) {
        return sb.insert(offset, f);
    }

    public StringBuilder insert(int offset, double d) {
        return sb.insert(offset, d);
    }

    public int indexOf(String str) {
        return sb.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return sb.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return sb.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return sb.lastIndexOf(str, fromIndex);
    }

    public StringBuilder reverse() {
        return sb.reverse();
    }
}

public class AdapterDecorator {
    public static void main(String[] args) {
        MyStringBuilder sb=new MyStringBuilder();
        sb.append("hello").appendLine(" world").append("New");
        System.out.println(sb.concat(" and this in the same line"));
    }
}
