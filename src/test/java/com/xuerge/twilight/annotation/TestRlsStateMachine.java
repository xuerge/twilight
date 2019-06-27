package com.xuerge.twilight.annotation;

import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.builder.AnnotationStateMachineBuilder;
import org.junit.Before;
import org.junit.Test;

public class TestRlsStateMachine {
    StateMachine machine;
    @Before
    public void setup(){
        AnnotationStateMachineBuilder stateMachineBuilder = new AnnotationStateMachineBuilder(RlsStateMachine.class);
        machine = stateMachineBuilder.build(RlsState.A);
    }
    @Test
    public void t(){
        machine.fire(RlsEvent.AtoB,new RlsContext());
    }

    @Test
    public void testStart(){
        machine.start(new RlsContext());
    }
}
