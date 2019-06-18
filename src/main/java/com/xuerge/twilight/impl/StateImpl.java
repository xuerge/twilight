package com.xuerge.twilight.impl;

import com.xuerge.twilight.State;
import com.xuerge.twilight.Transition;

import java.util.List;

public class StateImpl implements State {
    private String name;
    private List<Transition> transitions;
    public String getName() {
        return name;
    }

    public List<Transition> getTransition() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}
