package com.xuerge.twilight;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

public class ExecutorTest {
    ExecutorService executorPool;
    @Before
    public void prepare(){
        executorPool = Executors.newFixedThreadPool(1);
    }
    @Test
    public void t(){
        Future f = executorPool.submit(()->{
            TimeUnit.SECONDS.sleep(2);
            return 1;
        });


        try {
            System.out.println("future result is " + f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
