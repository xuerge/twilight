package com.xuerge.twilight.builder;

import com.google.common.collect.Lists;
import com.xuerge.twilight.Action;
import com.xuerge.twilight.StateMachine;

import java.util.List;

public class StateMachineBuilder<S, E, C> {
    StateMachine<S, E, C> stateMachine;
    private List<TransitionBuilder<S,E,C>> transitionBuilderList = Lists.newArrayList();



    public StateMachine<S, E, C> build(){
        return stateMachine;
    }

    public TransitionBuilder transition(){
        TransitionBuilder transitionBuilder = new TransitionBuilder();
            transitionBuilderList.add(transitionBuilder);
            return transitionBuilder;
    }




    /* From */
    public static class TransitionBuilder<S, E, C> implements ExternalTransitionBuilder<S, E, C>,From<S, E, C>,To<S, E, C> {
        private S from;
        private S to;
        private E event;
        private Action action;

        @Override
        public From<S, E, C> from(S stateId) {
            return null;
        }

        @Override
        public To to(Object stateId) {
            return null;
        }

        @Override
        public void on(E event) {

        }

    }
}
