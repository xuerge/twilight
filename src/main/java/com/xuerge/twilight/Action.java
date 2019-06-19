package com.xuerge.twilight;

public interface Action<S, E, C> {
    void execute(S from, S to, E event, C context);
}
