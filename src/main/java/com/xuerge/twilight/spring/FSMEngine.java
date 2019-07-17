package com.xuerge.twilight.spring;

import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.StateMachineBuilderFactory;
import com.xuerge.twilight.builder.AnnotationStateMachineBuilder;
import com.xuerge.twilight.builder.StateMachineBuilder;
import com.xuerge.twilight.impl.BaseStateMachine;
import lombok.extern.apachecommons.CommonsLog;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

public class FSMEngine<T extends BaseStateMachine> implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private StateMachineBuilder builder;

    public FSMEngine() {
        // Get the class extends FSMEngine
        Class clzz = getClass();
        Class<T> genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(clzz, FSMEngine.class);
        builder = new AnnotationStateMachineBuilder(genericType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //delegate fire
    public void fire(Object initialState, Object trigger, Object context) {
        StateMachine stateMachine = newStageMachine(initialState);
        stateMachine.fire(trigger, context);
    }

    protected StateMachine newStageMachine(Object initialState) {
        return builder.build(initialState);

    }
}
