package com.xuerge.twilight;

import java.util.Map;
import java.util.concurrent.Future;

public interface StateMachine<S, E, C> {


    void fire(E event,C context);
    Future fireSyn(E event, C context);

    void start(C context);

    Class<S> getStateClass();
    Class<E> getEventClass();
    Class<C> getContextClass();
    Class<?> getImplClass();
    Map<S, StateData<S, E, C>> getStates();
}
