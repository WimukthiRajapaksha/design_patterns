/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteratorPattern;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 *
 * @author wimukthirajapaksha
 */

class Creature implements Iterable<Integer>{
    
    private int[] stats=new int[3]; // pass by a parameter
    
    public int getStrength() {
        return stats[0];
    }
    
    public void setStrength(int strength) {
        stats[0]=strength;
    }
    
    public int getAgility() {
        return stats[1];
    }
    
    public int sum() {
        return IntStream.of(stats).sum();
    }
    
    public int max() {
        return IntStream.of(stats).max().getAsInt();
    }
    
    public double avg() {
        return IntStream.of(stats).average().getAsDouble();
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for(int x: stats) {
            action.accept(x);
        }
    }
}

public class ArrayBackedProperties {
    
}
