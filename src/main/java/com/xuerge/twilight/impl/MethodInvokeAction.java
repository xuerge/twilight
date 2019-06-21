package com.xuerge.twilight.impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.xuerge.twilight.Action;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.StateMachineException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvokeAction<S, E, C, T> implements Action<S, E, C, T> {
    private Method method;
    private Class stateMachineClazz;
    private String methodName;
    Class<?>[] parameterTypes;

    public MethodInvokeAction(Class stateMachineClazz, String methodName, Class<?>... parameterTypes) {
        this.stateMachineClazz = stateMachineClazz;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        initMethod();
    }

    private void initMethod() {
        Preconditions.checkArgument(stateMachineClazz.isAssignableFrom(StateMachine.class));
        try {
            method = stateMachineClazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new StateMachineException(e);
        }
    }

    @Override
    public void execute(S from, S to, E event, C context, T machine) {
        try {
            method.invoke(machine, from, to, event, context);
        } catch (IllegalAccessException e) {
            throw new StateMachineException(e);
        } catch (InvocationTargetException e) {
            throw new StateMachineException(e);
        }
    }
}
