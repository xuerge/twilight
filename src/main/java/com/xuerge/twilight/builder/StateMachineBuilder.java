package com.xuerge.twilight.builder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xuerge.twilight.*;
import com.xuerge.twilight.annotation.StateMachineDefinition;
import com.xuerge.twilight.annotation.Transition;
import com.xuerge.twilight.annotation.Transitions;
import com.xuerge.twilight.impl.BaseStateMachine;
import com.xuerge.twilight.impl.MethodInvokeAction;
import com.xuerge.twilight.util.ReflectionUtil;
import com.xuerge.twilight.util.StopWatch;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.AnnotationUtils;
import org.apache.commons.lang3.EnumUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Builder
 */
@Data
@Slf4j
public class StateMachineBuilder<S, E, C> {

    protected Class stateMachineImplClazz;
    protected Class<S> stateClazz;
    protected Class<E> eventClazz;
    protected Class<C> contextClazz;
    protected Map<S, StateData<S, E, C>> states;
    protected Class<?>[] methodCallParamTypes;


    protected List<TransitionBuilder<S, E, C>> transitionBuilderList = Lists.newArrayList();


    public StateMachineBuilder(Class stateMachineImplClazz, Class<S> stateClazz, Class<E> eventClazz, Class<C> contextClazz) {
        init(stateMachineImplClazz, stateClazz, eventClazz, contextClazz);
    }

    private void init(Class stateMachineImplClazz, Class<S> stateClazz, Class<E> eventClazz, Class<C> contextClazz) {
        this.stateMachineImplClazz = stateMachineImplClazz;
        this.stateClazz = stateClazz;
        this.eventClazz = eventClazz;
        this.contextClazz = contextClazz;
        methodCallParamTypes = new Class[]{stateClazz, stateClazz, eventClazz, contextClazz};
    }


    public StateMachine<S, E, C> build(S initialState) {
        createStages();
        buildTransitions();
        return buildMachine(initialState);
    }

    private StateMachine<S, E, C> buildMachine(S initialState) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        StateMachine<S, E, C> instance = instanceMachine();
        initMachine(instance, initialState);

        stopWatch.stop();
        log.info("Build stateMachine in " + stopWatch.getTotalTimeSeconds() + " seconds");
        return instance;
    }

    private void initMachine(StateMachine<S, E, C> machineInstance, S initialState) {
        ((BaseStateMachine) machineInstance).setStates(states);
        ((BaseStateMachine) machineInstance).setCurrentState(initialState);
        ((BaseStateMachine) machineInstance).setS(stateClazz);
        ((BaseStateMachine) machineInstance).setE(eventClazz);
        ((BaseStateMachine) machineInstance).setC(contextClazz);
        ((BaseStateMachine) machineInstance).setActionExecutor(new ActionExecutor(machineInstance));
        ((BaseStateMachine<S, E, C>) machineInstance).setStateMachineImplClazz(stateMachineImplClazz);
    }

    private StateMachine<S, E, C> instanceMachine() {
        Constructor<StateMachine<S, E, C>> constructor = null;
        try {
            constructor = stateMachineImplClazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new StateMachineException("can't instance statemachine");
    }

    private void createStages() {
        // new states collection
        states = Maps.newConcurrentMap();
        // create stateData and put into collection
        S[] allStates = stateClazz.getEnumConstants();
        Arrays.asList(allStates).forEach(s -> {
            states.put(s,new StateData(s));
        });

        // create state entry & leave action
        states.forEach((k,s)->{
            s.setEntryAction(buildMethodInvokeAction("entry" + s.getStateId()));
            s.setLeaveAction(buildMethodInvokeAction("leave" + s.getStateId()));
        });
    }

    private Action buildMethodInvokeAction(String methodName) {
        Method method = ReflectionUtil.getMethod(stateMachineImplClazz, methodName, methodCallParamTypes);
        if (null != method)
            return new MethodInvokeAction(stateMachineImplClazz, methodName, methodCallParamTypes);
        return null;
    }

    private void buildTransitions() {
        transitionBuilderList.forEach(tb -> {
            StateData data = states.get(tb.getFrom());
            if (null != data) {
                data.addTransition(tb.build(this));
            }
        });
    }

    public ExternalTransitionBuilder<S, E, C> externalTransition() {
        ExternalTransitionBuilder builder = new ExternalTransitionBuilder();
        transitionBuilderList.add(builder);
        return builder;
    }

    public InternalTransitionBuilder<S, E, C> internalTransition() {
        InternalTransitionBuilder<S, E, C> builder = new InternalTransitionBuilder();
        transitionBuilderList.add(builder);
        return builder;
    }

}
