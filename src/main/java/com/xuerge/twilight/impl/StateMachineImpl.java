package com.xuerge.twilight.impl;

import com.google.common.collect.Lists;
import com.xuerge.twilight.Context;
import com.xuerge.twilight.State;
import com.xuerge.twilight.StateMachine;

import java.util.List;

public class StateMachineImpl implements StateMachine {
    public State currentState;
    public List<State> allState = Lists.newArrayList();

    public void start(Context context) {

    }

    public void fire(State state, Context context) {

    }
}
