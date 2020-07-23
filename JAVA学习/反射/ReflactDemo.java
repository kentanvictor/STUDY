package com.dell.example.reflactDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * Created by JohnnyTan on 2017/10/31.
 */
public class ReflactDemo {
    public static void main(String[] args) {
        Person p = new Person();
        Test(p);
        Test();
    }

    private static void Test(Object obj) {
        //获得一个类的类对象
        Class<? extends Object> clazz = obj.getClass();
        //获得类传递过来的所有的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println("---------------------------------------");
        //获得所有的属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        System.out.println("---------------------------------------");
        //获得所有的构造器
        Constructor<?>[] cs = clazz.getDeclaredConstructors();

        for (Constructor c : cs) {
            System.out.println(c);
        }
        System.out.println("---------------------------------------");
    }

    private static void Test() {
        //获得一个类的类对象
        Class<?> clazz = Person.class;
        //获得类传递过来的所有的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println("---------------------------------------");
        //获得所有的属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        System.out.println("---------------------------------------");
        //获得所有的构造器
        Constructor<?>[] cs = clazz.getDeclaredConstructors();

        for (Constructor c : cs) {
            System.out.println(c);
        }
        System.out.println("---------------------------------------");
    }
}
