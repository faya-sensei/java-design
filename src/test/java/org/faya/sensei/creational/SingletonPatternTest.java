package org.faya.sensei.creational;

import org.faya.sensei.creational.singleton.DoubleLockSingleton;
import org.faya.sensei.creational.singleton.LazySingleton;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class SingletonPatternTest {

    @Test
    public void testLazyInitializationSingleton() throws Exception {
        final Class<LazySingleton> singletonClass = LazySingleton.class;
        final Constructor<?> constructor = singletonClass.getDeclaredConstructor();
        constructor.setAccessible(true);

        final LazySingleton firstInstance = LazySingleton.getInstance();
        final LazySingleton secondInstance = LazySingleton.getInstance();

        assertNotSame(firstInstance, constructor.newInstance());
        assertSame(firstInstance, secondInstance);
        assertNotNull(firstInstance);
    }

    @Test
    public void testDoubleCheckedLockingSingleton() throws Exception {
        final List<CompletableFuture<DoubleLockSingleton>> futures = IntStream.range(0, 32)
                .mapToObj(i -> CompletableFuture.supplyAsync(DoubleLockSingleton::getInstance))
                .toList();

        final DoubleLockSingleton firstInstance = futures.get(0).get(5, TimeUnit.SECONDS);

        for (int i = 1; i < 32; i++) {
            final DoubleLockSingleton instance = futures.get(i).get();
            assertSame(firstInstance, instance);
        }
    }
}