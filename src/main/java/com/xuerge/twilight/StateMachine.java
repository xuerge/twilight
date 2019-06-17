package com.xuerge.twilight;

public interface StateMachine<S, E, C> {


    void start(Context context);
    void fire(State state,Context context);
}
