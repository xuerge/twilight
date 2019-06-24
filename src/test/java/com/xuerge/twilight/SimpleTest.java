package com.xuerge.twilight;

import com.xuerge.twilight.builder.StateMachineBuilder;
import org.junit.Test;

public class SimpleTest {
    @Test
    public void t() throws NoSuchMethodException {

        StateMachineBuilder<State, Event, Object> builder = StateMachineBuilderFactory.getStateMachineBuilder(Simple.class, State.class, Event.class, Object.class);


        builder.externalTransition().from(State.A).to(State.B).on(Event.ToB).invoke("fromAtoB");
        builder.externalTransition().from(State.B).to(State.C).on(Event.ToC).invoke("fromBtoC");
        builder.externalTransition().from(State.B).to(State.C).on(Event.ToC).invoke("fromBtoC");
        StateMachine<State, Event, Object> s = builder.build(State.A);

        s.fire(Event.ToB, "c1");
        s.fire(Event.ToC, "c2");
    }



}




