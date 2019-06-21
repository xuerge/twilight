package com.xuerge.twilight;

public interface Action<S, E, C, T> {
    void execute(S from, S to, E event, C context, T stateMachine);
}
