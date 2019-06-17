package com.xuerge.twilight.impl;

import com.xuerge.twilight.Action;
import com.xuerge.twilight.Event;
import com.xuerge.twilight.State;
import com.xuerge.twilight.Transition;

public class TransitionImpl implements Transition {
    private State from;
    private State to;
    private Event event;
    private Action action;

    public State getFrom() {
        return from;
    }

    public State getTo() {
        return to;
    }

    public Event getEvent() {
        return event;
    }

    public Action getAction() {
        return action;
    }
}
