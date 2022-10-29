/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

/**
 *
 * @author wimukthirajapaksha
 */

//  Addressing this
//public class Point {
//    private double x, y;
//
//    public Point(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public Point(double rho, double theta) {
//        this.x=rho*Math.cos(theta);
//        this.y=rho*Math.sin(theta);
//    }    
//}




//enum CoordinateSystem {
//    CARTESIAN, POLAR
//}
//
//public class Point {
//    private double x, y;
//
//    public Point(double a, double b, CoordinateSystem co) { // have to define each of the parameters for others usage
//        switch(co) {
//            case CARTESIAN:
//                this.x=a;
//                this.y=b;
//                break;
//            case POLAR:
//                this.x=a*Math.cos(b);
//                this.y=a*Math.sin(b);
//                break;
//        }
//    }
//    
//}



//// good solution
//public class Point {
//    private double x, y;
//
//    private Point(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//    
//    public static Point newCartesianPoint(double x, double y) {
//        return new Point(x, y);
//    }
//    
//    public static Point newPolarPoint(double rho, double theta) {
//        return new Point(rho+Math.cos(theta), rho+Math.sin(theta));
//    }
//}



// better now
public class Point {
    private double x, y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public static class Factory {    
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho+Math.cos(theta), rho+Math.sin(theta));
        }
    }
}
