/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wimukthirajapaksha
 */

class Buffer {
    private final char[] characters;
    private final int lineWidth;

    public Buffer(int lineWidth, int lineHeight) {
        this.lineWidth = lineWidth;
        this.characters = new char[lineWidth*lineHeight];
    }
    
    public char charAt(int x, int y) {
        return characters[y*lineWidth+x];
    }
}

class Viewport {
    private final Buffer buffer;
    private final int width;
    private final int height;
    private final int offsetX;
    private final int offsetY;

    public Viewport(Buffer buffer, int width, int height, int offsetX, int offsetY) {
        this.buffer = buffer;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    
    public char charAt(int x, int y) {
        return buffer.charAt(x+offsetX, y+offsetY);
    }
}

class Console {
    private final List<Viewport> viewports=new ArrayList<>();
    int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void addViewport(Viewport viewport) {
        this.viewports.add(viewport);
    }
    
    public void render() {
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                for(Viewport v: viewports) {
                    System.out.print(v.charAt(x, y));
                }
            }
            System.out.println();
        }
    }
    
    public static Console newConsole(int width, int height) {
        Buffer buffer=new Buffer(width, height);
        Viewport viewport=new Viewport(buffer, width, height, 0, 0);
        Console console=new Console(width, height);
        console.addViewport(viewport);
        return console;
    }
}

public class Facade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Console console=Console.newConsole(30, 20);
        console.render();
    }
    
}
