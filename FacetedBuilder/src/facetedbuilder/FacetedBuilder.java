/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facetedbuilder;

/**
 *
 * @author wimukthirajapaksha
 */

class Person {
    // address
    public String streetAddress, postcode, city;
    
    // employeement
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "Person{" + "streetAddress=" + streetAddress + ", postcode=" + postcode + ", city=" + city + ", companyName=" + companyName + ", position=" + position + ", annualIncome=" + annualIncome + '}';
    }
}

// builder facade
class PersonBuilder {
    protected Person person=new Person();
    
    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }
    
    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }
    
    public Person build() {
        return person;
    }
}

class PersonJobBuilder extends PersonBuilder {
    public PersonJobBuilder(Person person) {
        this.person=person;
    }
    
    public PersonJobBuilder at(String companyName) {
        this.person.companyName=companyName;
        return this;
    }
    
    public PersonJobBuilder asA(String position) {
        this.person.position=position;
        return this;
    }
    
    public PersonJobBuilder earning(int annualIncome) {
        this.person.annualIncome=annualIncome;
        return this;
    }
}

class PersonAddressBuilder extends PersonBuilder {
    public PersonAddressBuilder(Person person) {
        this.person=person;
    }
    
    public PersonAddressBuilder at(String streetAddress) {
        this.person.streetAddress=streetAddress;
        return this;
    }
    
    public PersonAddressBuilder withPostcode(String postcode) {
        this.person.postcode=postcode;
        return this;
    }
    
    public PersonAddressBuilder in(String city) {
        this.person.city=city;
        return this;
    }
}

public class FacetedBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PersonBuilder personBuilder=new PersonBuilder();
        Person person=personBuilder
                .lives().at("Pallekanda").in("Walasmulla").withPostcode("82450")
                .works().at("PickMe").asA("SE").earning(250000).build();
        System.out.println(person);
    }
    
}
