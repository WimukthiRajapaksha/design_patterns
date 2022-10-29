/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package open.close.principle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author wimukthirajapaksha
 */
enum Color {
    REG, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    } 

    @Override
    public String toString() {
        return this.name+" : "+this.color+" "+this.size;
    }
}

interface Specification<T extends Product> {
    boolean isSatisfied(T item);
}

interface Filter<T extends Product> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color==color;
    }
}

class SizeSpecification implements Specification<Product> {
    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size==size;
    }
}

class AndSpecification<T extends Product> implements Specification<Product> {
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return first.isSatisfied((T) item) && second.isSatisfied((T) item);
    }
}

class BetterFilter implements Filter<Product> {
    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}

public class OpenClosePrinciple {
    
    public static void main(String[] args) {
        Product glass=new Product("Glass", Color.BLUE, Size.LARGE);
        Product apple=new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree=new Product("Tree", Color.GREEN, Size.LARGE);
        Product house=new Product("House", Color.BLUE, Size.LARGE);
        
        List<Product> products=Arrays.asList(glass, apple, tree, house);
        BetterFilter bf=new BetterFilter();
        bf.filter(products, new ColorSpecification(Color.BLUE)).forEach(System.out::println);
        System.out.println();
        bf.filter(products, new SizeSpecification(Size.LARGE)).forEach(System.out::println);
        System.out.println();
        bf.filter(products, new AndSpecification<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE))).forEach(i->System.out.println(i));
    }
}
