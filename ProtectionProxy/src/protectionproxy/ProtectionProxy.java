/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protectionproxy;

/**
 *
 * @author wimukthirajapaksha
 */

interface Drivable {
    void drive();
}

class Car implements Drivable {
    protected Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Car being driven.");
    }
}

class Driver {
    public int age;

    public Driver(int age) {
        this.age = age;
    }
    
    
}

class CarProxy extends Car {

    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if(driver.age>=16) {
            super.drive();
        } else {
            System.out.println("Driver too young");
        }
    }
}



public class ProtectionProxy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Car car=new CarProxy(new Driver(12));
        car.drive();
    }
}
