/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

/**
 *
 * @author wimukthirajapaksha
 */

class Person {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", position=" + position + '}';
    }
}

class PersonBuilder<T extends PersonBuilder<T>> {
    protected Person person=new Person();
    
    public T withName(String name) {
        person.name=name;
        return self();
    }
    
    public Person build() {
        return person;
    }
    
    protected T self() {
        return (T) this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    public EmployeeBuilder worksAt(String position) {
        person.position=position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

public class Builder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EmployeeBuilder pb=new EmployeeBuilder();
        Person wimu=pb.withName("wimukthi").worksAt("pickme").build();
        System.out.println(wimu);
    }
}
