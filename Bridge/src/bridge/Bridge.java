/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

/**
 *
 * @author wimukthirajapaksha
 */

interface Renderer {
    void renderCircle(float radius);
}

class VectorRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Vector rendering... "+radius);
    }
}

class RasterRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Raster rendering... "+radius);
    }
}

abstract class Shape {
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }
    
    public abstract void draw();
    public abstract void resize(float factor);
}

class Circle extends Shape {
    public float radius;

    public Circle(Renderer renderer) {
        super(renderer);
    }

    public Circle(float radius, Renderer renderer) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.renderCircle(radius);
    }

    @Override
    public void resize(float factor) {
        radius*=factor;
    }
}


public class Bridge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RasterRenderer rasterRenderer=new RasterRenderer();
        VectorRenderer vectorRenderer=new VectorRenderer();
        Circle circle=new Circle(5, vectorRenderer);
        circle.resize(2);
        circle.draw();
    }
    
}
