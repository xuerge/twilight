package com.xuerge.twilight;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * {@code StateData} is an interface representing possible state in a state machine.
 */
public class StateData<S, E, C>{
    private S stateId;
    private Map<E, Transition> transitions = Maps.newHashMap();

    public StateData(S stateId) {
        this.stateId = stateId;
    }

    public S getStateId() {
        return stateId;
    }

    public Map<E, Transition> getTransition() {
        return transitions;
    }

    public void addTransition(E event, Transition t) {
        transitions.put(event, t);
    }

    public void addTransition(Transition<S, E, C> t) {
        transitions.put(t.getEvent(), t);
    }
}
