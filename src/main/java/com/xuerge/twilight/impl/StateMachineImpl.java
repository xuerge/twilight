package com.xuerge.twilight.impl;

import com.google.common.collect.Maps;
import com.xuerge.twilight.State;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.Transition;

import java.util.Map;

public class StateMachineImpl<S, E, C> implements StateMachine<S, E, C> {
    private final Class stateMachineImplClass;
    private final Class contextClazz;
    private final Class stateClazz;
    private final Class eventClazz;
    private final Class<?>[] methodCallParamTypes;
    public S currentState;
    public Map<S, State<S, E, C>> states = Maps.newHashMap();

    public StateMachineImpl(Class<? extends StateMachine> stateMachineImplClass, Class stateClazz, Class eventClazz,Class contextClazz) {
        this.stateMachineImplClass = stateMachineImplClass;
        this.stateClazz = stateClazz;
        this.eventClazz = eventClazz;
        this.contextClazz = contextClazz;

        methodCallParamTypes = new Class[]{stateClazz, stateClazz, contextClazz, stateMachineImplClass};
    }


    @Override
    public void fire(E event, C context) {
        Transition<S, E, C> t = states.get(currentState).getTransition().get(event);
        t.getAction().execute(currentState, t.getTo(), event, context);
        currentState = t.getTo();
    }
}
