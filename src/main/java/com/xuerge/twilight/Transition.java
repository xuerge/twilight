package com.xuerge.twilight;

public interface Transition<S, E, C> {
    State getFrom();
    State getTo();
    Event getEvent();
    Action getAction();
}
