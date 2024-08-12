package org.faya.sensei.creational.singleton;

public class LazySingleton {

    public static LazySingleton getInstance() {
        return new LazySingleton();
    }
}
