package com.xuerge.twilight.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xuerge.twilight.Context;
import com.xuerge.twilight.State;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.Transition;

import java.util.List;
import java.util.Map;

public class StateMachineImpl<S, E, C> implements StateMachine<S, E, C> {
    public S currentState;
    public Map<S, State<S, E, C>> states = Maps.newHashMap();


    @Override
    public void fire(E event, C context) {
        Transition<S, E, C> t = states.get(currentState).getTransition().get(event);
        t.getAction().execute(currentState,t.getTo(),event, context);
        currentState = t.getTo();
    }
}
