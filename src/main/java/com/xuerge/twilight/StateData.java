package com.xuerge.twilight;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * {@code StateData} is an interface representing possible state in a state machine.
 */
public class StateData<S, E, C>{
    private S stateId;
    private Map<E, Transition<S, E, C>> transitions = Maps.newHashMap();
    private Action entryAction;
    private Action leaveAction;

    public StateData(S stateId) {
        this.stateId = stateId;
    }

    public S getStateId() {
        return stateId;
    }

    public Map<E, Transition<S, E, C>> getTransition() {
        return transitions;
    }

    public void addTransition(E event, Transition t) {
        transitions.put(event, t);
    }

    public void addTransition(Transition<S, E, C> t) {
        transitions.put(t.getEvent(), t);
    }

    public Action getEntryAction() {
        return entryAction;
    }

    public void setEntryAction(Action entryAction) {
        this.entryAction = entryAction;
    }

    public Action getLeaveAction() {
        return leaveAction;
    }

    public void setLeaveAction(Action leaveAction) {
        this.leaveAction = leaveAction;
    }
}
