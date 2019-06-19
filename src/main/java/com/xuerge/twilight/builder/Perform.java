package com.xuerge.twilight.builder;

import com.xuerge.twilight.Action;

public interface Perform<S, E, C> {
    void perform(Action<S, E, C> action);
}
