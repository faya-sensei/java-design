package org.faya.sensei.creational.singleton;

public class DoubleLockSingleton {

    public static DoubleLockSingleton getInstance() {
        return new DoubleLockSingleton();
    }
}
