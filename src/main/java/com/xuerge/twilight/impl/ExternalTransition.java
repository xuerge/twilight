package com.xuerge.twilight.impl;

import com.xuerge.twilight.Action;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.Transition;

public class ExternalTransition<S, E, C> implements Transition {
    private S from;
    private S to;
    private E event;
    private Action action;

    public ExternalTransition(S from, S to, E event, Action action) {
        this.from = from;
        this.to = to;
        this.event = event;
        this.action = action;
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
        return action;
    }
}
