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

class Property<T> {
    private T value;

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        // set logging here
        this.value = value;
    }
}

class Creature {
    private Property<Integer> agility=new Property<>(10);

    public void setAgility(int value) {
        this.agility.setValue(value);
    }

    public int getAgility() {
        return agility.getValue();
    }
}


public class PropertyProxy {
    public static void main(String[] args) {
        Creature creature=new Creature();
        creature.setAgility(12);
    }
}
