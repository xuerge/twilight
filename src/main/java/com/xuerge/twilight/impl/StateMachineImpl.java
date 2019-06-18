package com.xuerge.twilight.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xuerge.twilight.Context;
import com.xuerge.twilight.State;
import com.xuerge.twilight.StateMachine;

import java.util.List;
import java.util.Map;

public class StateMachineImpl<S,E,C> implements StateMachine {
    public State currentState;
    public Map<S,State> states = Maps.newHashMap();


    public void start(Context context) {

    }

    public void fire(State state, Context context) {

    }
}
