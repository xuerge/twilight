package com.xuerge.twilight.builder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xuerge.twilight.Action;
import com.xuerge.twilight.State;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.Transition;
import com.xuerge.twilight.impl.StateImpl;
import com.xuerge.twilight.impl.StateMachineImpl;
import com.xuerge.twilight.impl.TransitionImpl;

import java.util.List;
import java.util.Map;

public class StateMachineBuilder<S, E, C> {

    private final Class stateMachineImplClazz;
    private final Class<S> stateClazz;
    private final Class<E> eventClazz;
    private final Class<C> contextClazz;
    private Map<S, State> states = Maps.newConcurrentMap();


    private List<TransitionBuilder<S, E, C>> transitionBuilderList = Lists.newArrayList();


    public StateMachineBuilder(Class stateMachineImplClazz, Class<S> stateClazz, Class<E> eventClazz, Class<C> contextClazz) {
        this.stateMachineImplClazz = stateMachineImplClazz;
        this.stateClazz = stateClazz;
        this.eventClazz = eventClazz;
        this.contextClazz = contextClazz;
    }


    public StateMachine<S, E, C> build(S initialState) {
        transitionBuilderList.forEach(t -> {
            State state = states.get(t.from);
            if (null != state) {
                state.addTransition(t.event, t.build());
            }
        });
        StateMachine<S, E, C> stateMachine = new StateMachineImpl<>();
        ((StateMachineImpl) stateMachine).states = states;
        ((StateMachineImpl) stateMachine).currentState = initialState;
        return stateMachine;
    }

    public TransitionBuilder transition() {
        TransitionBuilder transitionBuilder = new TransitionBuilder(states);
        transitionBuilderList.add(transitionBuilder);
        return transitionBuilder;
    }


    /* From */
    public static class TransitionBuilder<S, E, C> implements ExternalTransitionBuilder<S, E, C>, From<S, E, C>, To<S, E, C> ,Perform<S,E,C>{

        private S from;
        private S to;
        private E event;
        private Action action;
        private Map<S, State> states;

        public TransitionBuilder(Map<S, State> states) {
            this.states = states;
        }

        @Override
        public From<S, E, C> from(S stateId) {
            State s = states.get(stateId);
            if (null == s) {
                states.put(stateId, new StateImpl(stateId.toString()));
            }
            this.from = stateId;
            return this;
        }

        @Override
        public To to(S stateId) {
            State s = states.get(stateId);
            if (null == s) {
                states.put(stateId, new StateImpl(stateId.toString()));
            }
            this.to = stateId;
            return this;
        }

        @Override
        public Perform on(E event) {
            this.event = event;
            return this;
        }

        public Transition build() {
            // TODO check transition data
            return new TransitionImpl(from, to, event, action);
        }

        @Override
        public void perform(Action<S, E, C> action) {
         this.action = action;
        }
    }
}
