package com.xuerge.twilight;

import com.xuerge.twilight.builder.StateMachineBuilder;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void t() {

        StateMachineBuilder<State, Event,Object> builder = StateMachineBuilderFactory.getStateMachineBuilder(Simple.class,State.class,Event.class,Object.class);


        builder.transition().from(State.A).to(State.B).on(Event.ToB);
        builder.transition().from(State.B).to(State.C).on(Event.ToC);
        StateMachine<State, Event,Object> s = builder.build();

        s.start(null);
    }

    class Simple{

    }

    enum State {
        A, B, C
    }

    enum Event {
        ToB, ToC
    }

}



