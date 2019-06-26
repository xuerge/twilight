package com.xuerge.twilight.basic;

import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.StateMachineBuilderFactory;
import com.xuerge.twilight.builder.StateMachineBuilder;
import org.junit.Test;

import java.util.concurrent.Future;

public class SimpleTest {
    @Test
    public void t() throws NoSuchMethodException {

        StateMachineBuilder<State, Event, Object> builder = StateMachineBuilderFactory.getStateMachineBuilder(Simple.class, State.class, Event.class, Object.class);


        builder.externalTransition().from(State.A).to(State.B).on(Event.ToB).invoke("fromAtoB");

        builder.externalTransition().from(State.B).to(State.C).on(Event.ToC).invoke("fromBtoC");

        builder.internalTransition().withIn(State.C).on(Event.InC).invoke("inC");
        StateMachine<State, Event, Object> s = builder.build(State.A);

        s.fire(Event.ToB, "c1");
        s.fire(Event.ToC, "c2");
        s.fire(Event.InC, "c3");
        s.fire(Event.InC, "c3");

        Future<Integer> result = s.fireSyn(Event.InC, "c3");
    }



}




