/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototype;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author wimukthirajapaksha
 */

public class Prototype {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Person person1=new Person(new String[]{"wimukthi", "rajapaksha"}, new Address("Walasmulla", 247));
        Person person2=(Person) person1.clone();
        System.out.println(person1);
        person2.address.houseNumber=10;
        System.out.println(person2);
        
        
        Foo foo=new Foo(5, "first foo", new String[]{"h", "e", "l", "l", "o"}, new Addre("hi", 2));
        Foo foo2=foo;
        System.out.println(foo);
        foo2.stuff = 22;
        foo2.names = new String[]{"hi"};
        System.out.println(foo2);
    }
}
