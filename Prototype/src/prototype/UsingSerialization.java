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

class Addre {
    public String name;
    public int num;

    public Addre(String name, int num) {
        this.name = name;
        this.num = num;
    }
    
    
}

class Foo implements Serializable {
    public int stuff;
    public String whatever;
    public String[] names;
    public Addre ad;

    public Foo(int stuff, String whatever, String[] names, Addre add) {
        this.stuff = stuff;
        this.whatever = whatever;
        this.names = names;
        this.ad=add;
    }

    @Override
    public String toString() {
        return "Foo{" + "stuff=" + stuff + ", whatever=" + whatever + ", names=" + Arrays.toString(names)+", address="+ this.ad + '}';
    }
}
