package com.xuerge.twilight.builder;

public interface WithIn<S, E, C> {
    Perform<S, E, C> on(E event);
}
