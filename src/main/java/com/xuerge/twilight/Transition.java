package com.xuerge.twilight;

public interface Transition<S, E, C> {
    S getFrom();
    S getTo();
    E getEvent();
    Action getAction();
}
