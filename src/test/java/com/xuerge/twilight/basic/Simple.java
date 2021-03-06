package com.xuerge.twilight.basic;

import com.xuerge.twilight.annotation.StateMachineTypeParameter;
import com.xuerge.twilight.annotation.Transition;
import com.xuerge.twilight.annotation.Transitions;
import com.xuerge.twilight.impl.BaseStateMachine;

@StateMachineTypeParameter(stateType = Event.class, eventType = Event.class, contextType = Object.class)
@Transitions({@Transition(from = "", to = "", on = "")})
public class Simple extends BaseStateMachine {

    public Simple() {
    }

    public void entryA(State from, State to, Event event, Object context) {
        System.out.println("entryA with context:" + context);
    }

    public void fromAtoB(State from, State to, Event event, Object context) {
        System.out.println("run method fromAtoB");
    }

    public void leaveA(State from, State to, Event event, Object context) {
        System.out.println("run method leaveA, context is " + context);
    }

    public void entryB(State from, State to, Event event, Object context) {
        System.out.println("run method entryB, context is " + context);
    }

    public void leaveB(State from, State to, Event event, Object context) {
        System.out.println("run method leaveB");
    }

    public void fromBtoC(State from, State to, Event event, Object context) {
        System.out.println("run method fromBtoC");
    }

    public void inC(State from, State to, Event event, Object context) {
        System.out.println("run method inC");
    }
}