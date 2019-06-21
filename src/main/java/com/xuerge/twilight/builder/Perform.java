package com.xuerge.twilight.builder;

import com.xuerge.twilight.Action;

public interface Perform<S, E, C> {
    void invoke(Action action);
    void invoke(String methodName);
}
