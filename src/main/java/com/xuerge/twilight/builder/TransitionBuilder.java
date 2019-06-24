package com.xuerge.twilight.builder;

import com.xuerge.twilight.Action;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.StateMachine;
import com.xuerge.twilight.Transition;
import com.xuerge.twilight.impl.MethodInvokeAction;
import com.xuerge.twilight.impl.TransitionImpl;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class TransitionBuilder<S, E, C> implements ExternalTransitionBuilder<S, E, C>, From<S, E, C>, To<S, E, C>, Perform<S, E, C> {

    private S from;
    private S to;
    private E event;
    private Action action;
    private String methodName;

    @Override
    public From<S, E, C> from(S stateId) {
        this.from = stateId;
        return this;
    }

    @Override
    public To to(S stateId) {
        this.to = stateId;
        return this;
    }

    @Override
    public Perform on(E event) {
        this.event = event;
        return this;
    }

    public Transition build(StateMachineBuilder sb) {
        if(null == action && !StringUtils.isEmpty(methodName)){
            action = new MethodInvokeAction(sb.getStateMachineImplClazz(),methodName,sb.getMethodCallParamTypes());
        }
        return new TransitionImpl(from, to, event, action);
    }

    @Override
    public void invoke(Action action) {
        this.action = action;
    }

    @Override
    public void invoke(String methodName) {
        this.methodName = methodName;
    }

}
