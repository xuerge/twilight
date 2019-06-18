package com.xuerge.twilight.impl;

import com.google.common.collect.Maps;
import com.xuerge.twilight.State;
import com.xuerge.twilight.Transition;

import java.util.Map;

public class StateImpl<S, E, C> implements State<S, E, C> {
    private String name;
    private Map<E, Transition> transitions = Maps.newHashMap();

    public StateImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<E, Transition> getTransition() {
        return transitions;
    }

    @Override
    public void addTransition(E event, Transition t) {
        transitions.put(event, t);
    }


}
