package com.xuerge.twilight.builder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xuerge.twilight.ActionExecutor;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.StateMachineException;
import com.xuerge.twilight.impl.BaseStateMachine;
import com.xuerge.twilight.impl.InternalTransition;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Builder
 */
@Data
public class StateMachineBuilder<S, E, C> {

    private final Class stateMachineImplClazz;
    private final Class<S> stateClazz;
    private final Class<E> eventClazz;
    private final Class<C> contextClazz;
    private Map<S, StateData> states = Maps.newConcurrentMap();
    private Class<?>[] methodCallParamTypes;


    private List<TransitionBuilder<S, E, C>> transitionBuilderList = Lists.newArrayList();


    public StateMachineBuilder(Class stateMachineImplClazz, Class<S> stateClazz, Class<E> eventClazz, Class<C> contextClazz) {
        this.stateMachineImplClazz = stateMachineImplClazz;
        this.stateClazz = stateClazz;
        this.eventClazz = eventClazz;
        this.contextClazz = contextClazz;

    }


    public StateMachine<S, E, C> build(S initialState) throws NoSuchMethodException {
        buildStages();
        buildTransitions();
        return buildMachine(initialState);
    }

    private StateMachine<S, E, C> buildMachine(S initialState) {
        StateMachine<S, E, C> instance = instanceMachine();
        initMachine(instance, initialState);
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

    private void buildStages() {
        S[] states = stateClazz.getEnumConstants();
        Arrays.asList(states).forEach(s -> createAndGetState(s));

        methodCallParamTypes = new Class[]{stateClazz, stateClazz, eventClazz, contextClazz};
    }

    private void buildTransitions() {
        transitionBuilderList.forEach(tb -> {
            StateData state = createAndGetState(tb.getFrom());
            if (null != state) {
                state.addTransition(tb.build(this));
            }
        });
    }

    public ExternalTransitionBuilder<S, E, C> externalTransition() {
        ExternalTransitionBuilder builder = new ExternalTransitionBuilder();
        transitionBuilderList.add(builder);
        return builder;
    }
    public InternalTransitionBuilder<S, E, C> internalTransition() {
        InternalTransitionBuilder<S,E,C> builder = new InternalTransitionBuilder();
        transitionBuilderList.add(builder);
        return builder;
    }

    public StateData<S, E, C> createAndGetState(S stateId) {
        StateData s = states.get(stateId);
        if (null == s) {
            states.put(stateId, new StateData(stateId.toString()));
            s = states.get(stateId);
        }
        return s;

    }

}
