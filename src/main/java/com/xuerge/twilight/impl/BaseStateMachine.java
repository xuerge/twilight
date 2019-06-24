package com.xuerge.twilight.impl;

import com.google.common.collect.Maps;
import com.xuerge.twilight.ActionExecutor;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.Transition;
import lombok.Setter;

import java.util.Map;


@Setter
public class BaseStateMachine<S, E, C> implements StateMachine<S, E, C> {
    private S currentState;
    private Map<S, StateData<S, E, C>> states = Maps.newHashMap();

    private Class stateMachineImplClazz;
    private Class<S> s;
    private Class<E> e;
    private Class<C> c;

    private ActionExecutor actionExecutor;


    @Override
    public void fire(E event, C context) {
        Transition<S, E, C> t = states.get(currentState).getTransition().get(event);
        actionExecutor.execute(t.getAction(),t.getFrom(),t.getTo(),t.getEvent(),context);
        currentState = t.getTo();
    }

    @Override
    public Class<S> getState() {
        return s;
    }

    @Override
    public Class<E> getEvent() {
        return e;
    }

    @Override
    public Class<C> getContext() {
        return c;
    }

    @Override
    public Class getImplClass() {
        return stateMachineImplClazz;
    }

}
