package com.xuerge.twilight.generic;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Person {
    public static void printList(List list) {
        for (Object elem: list) {
            System.out.println(elem.getClass());
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    public   void printInfo2(List<?> t){
        Object a = t.get(1);
    }

    public  <T> void printInfo(T t){

        System.out.println(t);
    }

    public static void main(String[] args) {
//        Person o = new Person();
//        o.printInfo("123");
        List<Integer> li = Arrays.asList(1, 2, 3);
        List<String>  ls = Arrays.asList("one", "two", "three");
        printList(li);
        printList(ls);
    }

}
