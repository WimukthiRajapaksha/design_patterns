/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liskovsubstitutionprinciple;

/**
 *
 * @author wimukthirajapaksha
 */

class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return this.width*this.height;
    }

    @Override
    public String toString() {
        return "Rectangle {" + "width=" + width + ", height=" + height + '}';
    }
}

class Square extends Rectangle {
    public Square() {
    }
    
    public Square(int size) {
        width=height=size;
    }

    @Override
    public void setWidth(int width) {
        System.out.println("1111111");
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        System.out.println("2222222");
        super.setHeight(height);
        super.setWidth(height);
    }
    
    
}


class RectangleFactory {
    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }
    
    public static Rectangle newSquare(int width) {
        return new Rectangle(width, width);
    }
}


public class LiskovSubstitutionPrinciple {

    /**
     * @param args the command line arguments
     */
    
    static void useIt(Rectangle r) {
        int width=r.getWidth();
        r.setHeight(10);
        System.out.println(r);
        System.out.println("expected = "+(width*10)+", got = "+r.getArea());
    }
    
    public static void main(String[] args) {        
        Rectangle rec=RectangleFactory.newRectangle(2, 3);
        useIt(rec);
        
        Rectangle sq=RectangleFactory.newSquare(0);
        sq.setWidth(5);
        useIt(sq);
    }
    
}
