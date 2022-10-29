/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

import java.util.Arrays;

/**
 *
 * @author wimukthirajapaksha
 */
class Address implements Cloneable {
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    protected Object clone() { // deep copy
        return new Address(streetName, houseNumber);
    }

    @Override
    public String toString() {
        return "Address{" + "streetName=" + streetName + ", houseNumber=" + houseNumber + '}';
    }
}

class Person implements Cloneable {
    public String[] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public Object clone() {
        return new Person(names.clone(), (Address) address.clone());
    }

    @Override
    public String toString() {
        return "Person{" + "names=" + Arrays.toString(names) + ", address=" + address + '}';
    }
}
