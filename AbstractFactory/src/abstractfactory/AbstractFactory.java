/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractfactory;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author wimukthirajapaksha
 */

interface HotDrink {
    void consume();
}

class Tea implements HotDrink {
    @Override
    public void consume() {
        System.out.println("Tea is delicious..");
    }
}

class Coffee implements HotDrink {
    @Override
    public void consume() {
        System.out.println("Coffee is delicious..");
    }
}

interface HotDrinkFactory {
    HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory {
    @Override
    public HotDrink prepare(int amount) {
        System.out.println("preparing tea..");
        return new Tea();
    }
}

class CoffeeFactory implements HotDrinkFactory {
    @Override
    public HotDrink prepare(int amount) {
        System.out.println("preparing coffee");
        return new Coffee();
    }    
}

class HotDrinkMachine {
    private List<Pair<String, HotDrinkFactory>> namedFactories=new ArrayList<>();

    public HotDrinkMachine() {
        getClass()
    }
    
}

public class AbstractFactory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
