package com.xuerge.twilight.builder;

import com.xuerge.twilight.Action;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.Transition;
import com.xuerge.twilight.impl.MethodInvokeAction;
import com.xuerge.twilight.impl.ExternalTransition;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

//@Data
public class ExternalTransitionBuilder<S, E, C> implements From<S, E, C>, To<S, E, C>, Perform<S, E, C>,TransitionBuilder<S, E, C> {

    private S from;
    private S to;
    private E event;
    private Action action;
    private String methodName;

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
        return new ExternalTransition(from, to, event, action);
    }

    @Override
    public void invoke(Action action) {
        this.action = action;
    }

    @Override
    public void invoke(String methodName) {
        this.methodName = methodName;
    }

    public S getFrom() {
        return from;
    }

    @Override
    public void setFrom(S from) {
        this.from = from;
    }

    @Override
    public S  getTo() {
        return to;
    }

    @Override
    public void setTo(S to) {
        this.to = to;
    }

    @Override
    public E getEvent() {
        return event;
    }

    @Override
    public void setEvent(E event) {
        this.event = event;
    }


    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
