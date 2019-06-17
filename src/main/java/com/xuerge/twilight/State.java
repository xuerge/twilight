package com.xuerge.twilight;

import java.util.List;

/**
 * {@code State} is an interface representing possible state in a state machine.
 */
public interface State<S, E, C> {
    String getName();
    List<Transition> getTransition();
}
