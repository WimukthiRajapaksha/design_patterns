/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringdecorator;

/**
 *
 * @author wimukthirajapaksha
 */

interface Shape {
    String info();
}

class Circle implements Shape {
    private float radius;

    public Circle() {
    }

    public Circle(float radius) {
        this.radius = radius;
    }
    
    public void resize(float factor) {
        this.radius*=factor;
    }

    @Override
    public String info() {
        return "A circle of radius "+radius;
    }
}

class Square implements Shape {
    private float size;

    public Square() {
    }

    public Square(float size) {
        this.size = size;
    }

    @Override
    public String info() {
        return "A square of size "+this.size;
    }
}



// -----------------------real decorator implementations------------------------------
class ColoredShape implements Shape {
    private Shape shape;
    private String color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info()+" has the color "+color;
    }
}

class TransparentShape implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info()+" has "+transparency+"% transparency";
    }
}



public class ProperDecorator {
    public static void main(String[] args) {
        Circle circle=new Circle(10);
        System.out.println(circle.info());
        ColoredShape blueSquare=new ColoredShape(new Square(20), "blue");
        System.out.println(blueSquare.info());
        
        Shape c=new TransparentShape(new ColoredShape(new Circle(5), "green"), 40);
        System.out.println(c.info());
    }
}
