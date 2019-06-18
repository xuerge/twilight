package com.xuerge.twilight.impl;

import com.xuerge.twilight.Action;
import com.xuerge.twilight.Event;
import com.xuerge.twilight.State;
import com.xuerge.twilight.Transition;

public class TransitionImpl<S, E, C> implements Transition {
    private S from;
    private S to;
    private E event;
    private Action action;

    public TransitionImpl(S from, S to, E event) {
        this.from = from;
        this.to = to;
        this.event = event;
    }


    @Override
    public S getFrom() {
        return from;
    }

    @Override
    public S getTo() {
        return to;
    }

    @Override
    public E getEvent() {
        return event;
    }

    @Override
    public Action getAction() {
        return null;
    }
}
