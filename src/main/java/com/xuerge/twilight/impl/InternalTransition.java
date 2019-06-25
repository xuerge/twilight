package com.xuerge.twilight.impl;

import com.xuerge.twilight.Action;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.Transition;

public class InternalTransition<S, E, C> implements Transition<S, E, C> {
    private S s;
    private E e;
    private Action action;

    public InternalTransition(S s, E event, Action action) {
        this.s = s;
        this.e = event;
        this.action = action;
    }


    @Override
    public S getFrom() {
        return s;
    }

    @Override
    public S getTo() {
        return s;
    }

    @Override
    public E getEvent() {
        return e;
    }

    @Override
    public Action getAction() {
        return action;
    }
}
