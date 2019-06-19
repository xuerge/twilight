package com.xuerge.twilight.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class ReactorTest {
    @Test
    public void subscribe1(){
        Flux<Integer> ints = Flux.range(1, 3);
        ints.subscribe(i -> System.out.println(i));
    }

    @Test
    public void subscribe2(){
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error: " + error));
    }
    @Test
    public void subscribe3() {
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 5) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(i -> System.out.println(i),
                error -> System.out.println("Error:" + error),
                () -> System.out.println("Done"));
    }

    @Test
    public void subscribe4() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println("i:"+i),
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");},
                s -> ss.request(10));
        ints.subscribe(ss);
    }
}
