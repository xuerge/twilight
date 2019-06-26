package com.xuerge.twilight.generic;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

public class GenericTest {
    @Test
    public void t(){
        Collection<?> collection = Lists.newArrayList();
//        collection.add();
//        collection.add(State.A);

        Iterator it = collection.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }


    }
}
