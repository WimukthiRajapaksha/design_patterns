/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author wimukthirajapaksha
 */

class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}

class Line {
    public Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
}

class VectorObject extends ArrayList<Line> {
    
}

class VectorRectangle extends VectorObject {

    public VectorRectangle(int x, int y, int width, int height) {
        add(new Line(new Point(x, y), new Point(x+width, y)));
        add(new Line(new Point(x+width, y), new Point(x+width, y+height)));
        add(new Line(new Point(x, y), new Point(x, y+height)));
        add(new Line(new Point(x, y+height), new Point(x+width, y+height)));
    }
}

class LineToPointAdapter extends ArrayList<Point> {
    private static int count=0;
    
    public LineToPointAdapter(Line line) {
        System.out.println(line.start.x+" "+line.start.y+" "+line.end.x+" "+line.end.y);
        add(new Point(line.start.x, line.start.y));
        add(new Point(line.end.x, line.end.y));
    }
}

public class Adapter {
    private final static List<VectorObject> vectorObject=new ArrayList<>(
        Arrays.asList(
            new VectorRectangle(1, 1, 10, 10), 
            new VectorRectangle(3, 3, 6, 6)
        )
    );
    
    private static void draw() {
        for(VectorObject v: vectorObject) {
            for(Line l: v) {
                LineToPointAdapter adapter = new LineToPointAdapter(l);
                adapter.forEach(Adapter::drawPoint);
            }
        }
    }
    
    public static void drawPoint(Point p) {
        System.out.println("..");
    }
    
    public static void main(String[] args) {
        draw();
    }
}
