package com.xuerge.twilight.impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.xuerge.twilight.Action;
import com.xuerge.twilight.StateMachine;

import java.lang.reflect.Method;

public class MethodInvokeAction<S, E, C> implements Action<S, E, C> {
    private Method method;
    private StateMachine stateMachine;
    private String methodName;

    public MethodInvokeAction(StateMachine stateMachine, String methodName) {
        this.stateMachine = stateMachine;
        this.methodName = methodName;
        initMethod();
    }

    private void initMethod() {
        Preconditions.checkArgument(stateMachine.getClass().isAssignableFrom(StateMachine.class));
        stateMachine.getClass().getMethod(methodName,stateMachine.)
    }

    @Override
    public void execute(Object from, Object to, Object event, Object context) {

    }
}
