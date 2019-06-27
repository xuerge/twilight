package com.xuerge.twilight.basic;

import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.StateMachineBuilderFactory;
import com.xuerge.twilight.builder.StateMachineBuilder;
import org.junit.Before;
import org.junit.Test;

public class SimpleTest {
    StateMachine<State, Event, Object> machine;

    @Before
    public void buildStateMachine() {
        StateMachineBuilder<State, Event, Object> builder = StateMachineBuilderFactory.getStateMachineBuilder(Simple.class, State.class, Event.class, Object.class);
        builder.externalTransition().from(State.A).to(State.B).on(Event.ToB).invoke("fromAtoB");
        builder.externalTransition().from(State.B).to(State.C).on(Event.ToC).invoke("fromBtoC");
        builder.internalTransition().withIn(State.C).on(Event.InC).invoke("inC");
        machine = builder.build(State.A);
    }

    @Test
    public void testStart() {
        machine.start("hello");
    }

    @Test
    public void t() throws NoSuchMethodException {
        machine.fire(Event.ToB, "c1");
        machine.fire(Event.ToC, "c2");
        machine.fire(Event.InC, "c3");
        machine.fire(Event.InC, "c3");
    }


}




