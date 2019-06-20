package com.xuerge.twilight;

import java.util.List;
import java.util.Map;

/**
 * {@code State} is an interface representing possible state in a state machine.
 */
public interface State<State, E, C>{
    String getName();
    Map<E,Transition> getTransition();
    void addTransition(E event, Transition t);
}
