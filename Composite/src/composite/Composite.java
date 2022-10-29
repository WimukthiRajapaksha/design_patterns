/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author wimukthirajapaksha
 */

class GraphicObject {
    protected String name = "Group";
    
    public String color;
    public List<GraphicObject> children=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphicObject() {
    }
    
    public void print(StringBuilder sb, int depth) {
        sb.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth>0 ? " " : "")
                .append(color==null || color.isEmpty() ? "" : color+" ")
                .append(getName())
                .append(System.lineSeparator());
        for(GraphicObject c: children) {
            c.print(sb, depth+1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
}

class Circle extends GraphicObject {
    public Circle(String color) {
        this.name="circle";
        this.color=color;
    }
}

class Square extends GraphicObject {
    public Square(String color) {
        this.name="square";
        this.color=color;
    }
}


public class Composite {
    public static void main(String[] args) {
        GraphicObject graphicObject=new GraphicObject();
        graphicObject.setName("My object");
        graphicObject.children.add(new Square("Red"));
        graphicObject.children.add(new Circle("Yellow"));
        
        GraphicObject group=new GraphicObject();
        group.children.add(new Circle("Blue"));
        group.children.add(new Square("Blue"));
        
        graphicObject.children.add(group);
        
        System.out.println(graphicObject);
    }
}
