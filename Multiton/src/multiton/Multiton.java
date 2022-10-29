/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiton;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wimukthirajapaksha
 */

enum Subsystem {
    PRIMARY, AUXILIARY, FALLBACK
}

class Printer{

    private static int instanceCount=0;
    private Printer() {
        instanceCount++;
        System.out.println(instanceCount+" instances created.");
    }
    
    private static Map<Subsystem, Printer> instances=new HashMap<>();
    public static Printer get(Subsystem ss) {
        if(instances.containsKey(ss)) {
            return instances.get(ss);
        }
        Printer printer=new Printer();
        instances.put(ss, printer);
        return printer;
    }
}

public class Multiton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Printer pri=Printer.get(Subsystem.PRIMARY);
        Printer aux=Printer.get(Subsystem.AUXILIARY);
        Printer aux2=Printer.get(Subsystem.AUXILIARY);
    }
    
}
