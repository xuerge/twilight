package com.xuerge.twilight.impl;

import com.google.common.collect.Maps;
import com.xuerge.twilight.ActionExecutor;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.Transition;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.Future;


@Setter
public class BaseStateMachine<S, E, C> implements StateMachine<S, E, C> {
    private S currentState;
    private StateData startState;
    private Map<S, StateData<S, E, C>> states = Maps.newHashMap();

    private Class stateMachineImplClazz;
    private Class<S> s;
    private Class<E> e;
    private Class<C> c;

    private ActionExecutor actionExecutor;


    @Override
    public void fire(E event, C context) {
        Transition<S, E, C> t = states.get(currentState).getTransition().get(event);
        actionExecutor.execute(t, context);
        currentState = t.getTo();
    }

    @Override
    public Future fireSyn(E event, C context) {
        Transition<S, E, C> t = states.get(currentState).getTransition().get(event);
        return actionExecutor.executeSyn(t, context);
    }

    @Override
    public void start(C context) {
        String methodName = "entry" + startState.getStateId();
    }

    @Override
    public Class<S> getStateClass() {
        return s;
    }

    @Override
    public Class<E> getEventClass() {
        return e;
    }

    @Override
    public Class<C> getContextClass() {
        return c;
    }

    @Override
    public Class getImplClass() {
        return stateMachineImplClazz;
    }

    @Override
    public Map<S, StateData<S, E, C>> getStates() {
        return states;
    }

}
