package com.xuerge.twilight;

import java.util.Map;
import java.util.concurrent.Future;

public interface StateMachine<S, E, C> {

    void start(C context);

    void fire(E event, C context);

    Future fireSyn(E event, C context);


    Class getStateClass();

    Class getEventClass();

    Class getContextClass();

    Class<?> getImplClass();

    Map<String, StateData<S, E, C>> getStates();
}
