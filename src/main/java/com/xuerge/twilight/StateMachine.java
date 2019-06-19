package com.xuerge.twilight;

public interface StateMachine<S, E, C> {


    void fire(E event,C context);
}
