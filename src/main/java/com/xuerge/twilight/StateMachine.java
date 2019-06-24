package com.xuerge.twilight;

public interface StateMachine<S, E, C> {


    void fire(E event,C context);

    Class<S> getState();
    Class<E> getEvent();
    Class<C> getContext();
    Class<?> getImplClass();
}
