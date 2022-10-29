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
public class Factory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Point pointCartesian=Point.newCartesianPoint(2, 4);
//        Point pointPolar=Point.newPolarPoint(4, 29);


        Point pointCartesian=Point.Factory.newCartesianPoint(2, 4);
        Point pointPolar=Point.Factory.newPolarPoint(4, 29);
    }
    
}
