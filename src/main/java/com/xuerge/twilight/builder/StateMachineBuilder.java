package com.xuerge.twilight.builder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.impl.BaseStateMachine;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class StateMachineBuilder<S, E, C> {

    private final Class stateMachineImplClazz;
    private final Class<S> stateClazz;
    private final Class<E> eventClazz;
    private final Class<C> contextClazz;
    private Map<S, StateData> states = Maps.newConcurrentMap();
    private final Class<?>[] methodCallParamTypes;


    private List<TransitionBuilder<S, E, C>> transitionBuilderList = Lists.newArrayList();


    public StateMachineBuilder(Class stateMachineImplClazz, Class<S> stateClazz, Class<E> eventClazz, Class<C> contextClazz) {
        this.stateMachineImplClazz = stateMachineImplClazz;
        this.stateClazz = stateClazz;
        this.eventClazz = eventClazz;
        this.contextClazz = contextClazz;
        methodCallParamTypes = new Class[]{stateClazz, stateClazz, contextClazz, stateMachineImplClazz};
    }


    public StateMachine<S, E, C> build(S initialState) {
        transitionBuilderList.forEach(tb -> {
            StateData state = getOrCreateState(tb.getFrom());
            if (null != state) {
                state.addTransition(tb.build(this));
            }
        });
        StateMachine<S, E, C> stateMachine = new BaseStateMachine<>(stateMachineImplClazz, stateClazz, eventClazz, contextClazz);
        ((BaseStateMachine) stateMachine).states = states;
        ((BaseStateMachine) stateMachine).currentState = initialState;
        return stateMachine;
    }

    public TransitionBuilder<S, E, C> transition() {
        TransitionBuilder transitionBuilder = new TransitionBuilder();
        transitionBuilderList.add(transitionBuilder);
        return transitionBuilder;
    }

    public StateData<S, E, C> getOrCreateState(S stateId) {
        StateData s = states.get(stateId);
        if (null == s) {
            states.put(stateId, new StateData(stateId.toString()));
            s = states.get(stateId);
        }
        return s;

    }

}
