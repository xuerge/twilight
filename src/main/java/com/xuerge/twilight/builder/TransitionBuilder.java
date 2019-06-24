package com.xuerge.twilight.builder;

import com.xuerge.twilight.Action;
import com.xuerge.twilight.Transition;

public interface TransitionBuilder<S, E, C> {
    S getFrom();
    void setFrom(S from);

    S getTo();
    void setTo(S to);

    E getEvent();
    void setEvent(E event);

    Action getAction();
    void setAction(Action action);

    Transition build(StateMachineBuilder sb);
}
