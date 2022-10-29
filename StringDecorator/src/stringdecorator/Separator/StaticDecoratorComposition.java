/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringdecorator.Separator;

import java.util.function.Supplier;

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
class ColoredShape<T extends Shape> implements Shape {
    private Shape shape;
    private String color;

    public ColoredShape(Supplier<? extends T> supplier, String color) {
        this.shape = supplier.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info()+" has the color "+color;
    }
}

class TransparentShape<T extends Shape> implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape(Supplier<? extends T> supplier, int transparency) {
        this.shape = supplier.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info()+" has "+transparency+"% transparency";
    }
}
public class StaticDecoratorComposition {
    public static void main(String[] args) {
        ColoredShape<Square> blueSquare=new ColoredShape<>(()->new Square(20), "blue");
        System.out.println(blueSquare.info());
        
        TransparentShape<ColoredShape<Circle>> myCircle=new TransparentShape<>(
                ()->new ColoredShape<>(
                        ()->new Circle(5), "green"
                ), 50);
        System.out.println(myCircle.info());
    }
}
