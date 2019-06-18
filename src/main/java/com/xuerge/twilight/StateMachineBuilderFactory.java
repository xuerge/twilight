package com.xuerge.twilight;

import com.xuerge.twilight.builder.StateMachineBuilder;

public class StateMachineBuilderFactory<S, E, C> {

    public static <S,E,C> StateMachineBuilder<S, E, C> getStateMachineBuilder(Class stateMachineImplClazz, Class<S> stateClazz, Class<E> eventClazz, Class<C> contextClazz){
        return new StateMachineBuilder<>(stateMachineImplClazz, stateClazz, eventClazz, contextClazz);
    }
}
