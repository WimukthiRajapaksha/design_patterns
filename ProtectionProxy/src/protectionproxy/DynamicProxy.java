/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protectionproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wimukthirajapaksha
 */

interface Human {
    void walk();
    void talk();
}

class Person implements Human {

    @Override
    public void walk() {
        System.out.println("Walking..");
    }

    @Override
    public void talk() {
        System.out.println("Talking..");
    }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;
    private Map<String, Integer> map=new HashMap<>();

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name=method.getName();
        if(name.contains("toString")) {
            return map.toString();
        }
        map.merge(name, 1, Integer::sum);
        return method.invoke(target, args);
    }
}

public class DynamicProxy {
    public static <T> T withLoging(T target, Class<T> itf) {
        return (T) Proxy.newProxyInstance(itf.getClassLoader(), new Class<?>[]{itf}, new LoggingHandler(target));
    }
    public static void main(String[] args) {
        Person person=new Person();
        Human logged=withLoging(person, Human.class);
        logged.talk();
        logged.walk();
        logged.walk();
        System.out.println(logged);
    }
}
