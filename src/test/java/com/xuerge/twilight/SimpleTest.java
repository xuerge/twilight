package com.xuerge.twilight;

import com.xuerge.twilight.builder.StateMachineBuilder;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void t() {

        StateMachineBuilder<State, Event, Object> builder = StateMachineBuilderFactory.getStateMachineBuilder(Simple.class, State.class, Event.class, Object.class);


        builder.transition().from(State.A).to(State.B).on(Event.ToB).invoke("fromAtoB");
        builder.transition().from(State.B).to(State.C).on(Event.ToC).invoke(new Action() {
            @Override
            public void execute(Object from, Object to, Object event, Object context, Object stateMachine) {
                System.out.println("execute method from B to C");
            }
        });
        StateMachine<State, Event, Object> s = builder.build(State.A);

        s.fire(Event.ToB, null);
        s.fire(Event.ToC, null);
    }

    enum State {
        A, B, C
    }

    enum Event {
        ToB, ToC
    }

    class Simple {

    }

}




