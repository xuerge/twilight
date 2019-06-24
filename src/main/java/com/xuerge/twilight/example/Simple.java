package com.xuerge.twilight.example;

import com.xuerge.twilight.StateMachine;

public class Simple implements StateMachine {

    @Override
    public void fire(Object event, Object context) {

    }

    public void fromAtoB(State from, State to, Object context){
        System.out.println("fromAtoB");
    }
}