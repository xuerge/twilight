package com.xuerge.twilight.annotation;

import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.builder.AnnotationStateMachineBuilder;
import org.junit.Test;

public class TestRlsStateMachine {
    @Test
    public void t(){
        AnnotationStateMachineBuilder stateMachineBuilder = new AnnotationStateMachineBuilder(RlsStateMachine.class);
        StateMachine machine = stateMachineBuilder.build(RlsState.A);
        machine.fire(RlsEvent.AtoB,new RlsContext());
    }
}
