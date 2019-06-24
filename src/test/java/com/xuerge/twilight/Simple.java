package com.xuerge.twilight;

import com.xuerge.twilight.impl.BaseStateMachine;

public class Simple extends BaseStateMachine {

    public Simple() {
    }


    public void fromAtoB(State from, State to,Event event, Object context){
        System.out.println("run method fromAtoB");
    }

    public void leaveA(State from, State to, Event event,Object context){
        System.out.println("run method leaveA, context is " + context);
    }
    public void entryB(State from, State to, Event event,Object context){
        System.out.println("run method entryB, context is " + context);
    }

    public void leaveB(State from, State to,Event event, Object context){
        System.out.println("run method leaveB");
    }

    public void fromBtoC(State from, State to, Event event,Object context){
        System.out.println("run method fromBtoC");
    }
}