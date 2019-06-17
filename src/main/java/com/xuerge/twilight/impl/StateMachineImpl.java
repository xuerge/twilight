package com.xuerge.twilight.impl;

import com.xuerge.twilight.Context;
import com.xuerge.twilight.State;
import com.xuerge.twilight.StateMachine;

import java.util.List;

public class StateMachineImpl implements StateMachine {
    private State currentState;
    private List<State> allState;

    public void start(Context context) {

    }

    public void fire(State state, Context context) {

    }
}
