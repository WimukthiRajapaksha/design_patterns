/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

/**
 *
 * @author wimukthirajapaksha
 */

class HomeAddress {
    public String streetAddress, city, country;

    public HomeAddress(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public HomeAddress(HomeAddress homeAddress) { // copy constructor
        this(homeAddress.streetAddress, homeAddress.city, homeAddress.country);
    }

    @Override
    public String toString() {
        return "HomeAddress{" + "streetAddress=" + streetAddress + ", city=" + city + ", country=" + country + '}';
    }
}

class Employee {
    public String name;
    public HomeAddress address;

    public Employee(String name, HomeAddress address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee employee) { // copy constructor
        this(employee.name, new HomeAddress(employee.address));
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", address=" + address + '}';
    }
    
    
}
