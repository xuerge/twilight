package com.xuerge.twilight;

public interface Action<S, E, C, T> {
    void execute(T machine, S from, S to, E event, C context);
}
