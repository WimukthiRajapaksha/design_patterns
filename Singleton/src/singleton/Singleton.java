/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

/**
 *
 * @author wimukthirajapaksha
 */

////having an when accessing multiple threads the same singleton object; can create multiple instances.
//class LazySingleton {
//    private static LazySingleton instance;
//
//    private LazySingleton() {
//        System.out.println("Initializing a lazy singleton.");
//    }
//    
//    public static LazySingleton getInstance() { // to avoid that drawback we can use synchronized here, but performance degrades
//        if(instance==null) {
//            instance=new LazySingleton();
//        }
//        return instance;
//    }
//}



//// double checked locking
//class LazySingleton {
//    private static LazySingleton instance;
//
//    private LazySingleton() {
//        System.out.println("Initializing a lazy singleton.");
//    }
//    
//    public static LazySingleton getInstance() { // lazy and thread safe
//        if(instance==null) {
//            synchronized(LazySingleton.class) {
//                if(instance==null) {
//                    instance=new LazySingleton();
//                }
//            }
//        }
//        return instance;
//    }
//}


////another thread safe implementation
class InnerStaticSingleton {
    private InnerStaticSingleton(){
    }
    
    private static class Impl{
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }
    
    public static InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}

public class Singleton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InnerStaticSingleton inner=InnerStaticSingleton.getInstance();
        System.out.println(inner);
    }
}
