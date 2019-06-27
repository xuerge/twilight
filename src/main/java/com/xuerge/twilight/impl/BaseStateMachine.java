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
    private Map<String, StateData<S, E, C>> states = Maps.newHashMap();

    private Class stateMachineImplClazz;
    private Class s;
    private Class e;
    private Class c;

    private ActionExecutor actionExecutor;


    @Override
    public void start(C context) {
        StateData stateData = states.get(currentState.toString());
        actionExecutor.execute(stateData.getEntryAction(), currentState, currentState, context);
    }

    @Override
    public void fire(E event, C context) {
        Transition<S, E, C> t = states.get(currentState.toString()).getTransition().get(event);
        actionExecutor.execute(t, context);
        currentState = t.getTo();
    }

    @Override
    public Future fireSyn(E event, C context) {
        Transition<S, E, C> t = states.get(currentState).getTransition().get(event);
        return actionExecutor.executeSyn(t, context);
    }


    @Override
    public Class getStateClass() {
        return s;
    }

    @Override
    public Class getEventClass() {
        return e;
    }

    @Override
    public Class getContextClass() {
        return c;
    }

    @Override
    public Class getImplClass() {
        return stateMachineImplClazz;
    }

    @Override
    public Map<String, StateData<S, E, C>> getStates() {
        return states;
    }

}
