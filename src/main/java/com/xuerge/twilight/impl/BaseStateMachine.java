package com.xuerge.twilight.impl;

import com.google.common.collect.Maps;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.Transition;

import java.util.Map;

public class BaseStateMachine<S, E, C> implements StateMachine<S, E, C> {
    private final Class stateMachineImplClass;
    private final Class contextClazz;
    private final Class stateClazz;
    private final Class eventClazz;
    private final Class<?>[] methodCallParamTypes;
    public S currentState;
    public Map<S, StateData<S, E, C>> states = Maps.newHashMap();

    public BaseStateMachine(Class<? extends StateMachine> stateMachineImplClass, Class stateClazz, Class eventClazz, Class contextClazz) {
        this.stateMachineImplClass = stateMachineImplClass;
        this.stateClazz = stateClazz;
        this.eventClazz = eventClazz;
        this.contextClazz = contextClazz;

        methodCallParamTypes = new Class[]{stateClazz, stateClazz, contextClazz, stateMachineImplClass};
    }


    @Override
    public void fire(E event, C context) {
        Transition<S, E, C> t = states.get(currentState).getTransition().get(event);
        t.getAction().execute(currentState, t.getTo(), event, context,this);
        currentState = t.getTo();
    }

    public Class getStateMachineImplClass() {
        return stateMachineImplClass;
    }

    public Class getContextClazz() {
        return contextClazz;
    }

    public Class getStateClazz() {
        return stateClazz;
    }

    public Class getEventClazz() {
        return eventClazz;
    }

    public Class<?>[] getMethodCallParamTypes() {
        return methodCallParamTypes;
    }
}
