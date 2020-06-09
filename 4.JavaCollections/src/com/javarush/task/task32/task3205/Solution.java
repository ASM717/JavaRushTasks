package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static java.lang.reflect.Proxy.newProxyInstance;

/*
Создание прокси-объекта
*/
public class Solution {
    private static Object ClassLoader;

    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethods original = new SomeInterfaceWithMethodsImpl();

        return (SomeInterfaceWithMethods) Proxy.newProxyInstance(
                original.getClass().getClassLoader(),
                original.getClass().getInterfaces(),
                new CustomInvocationHandler(original));
    }
}