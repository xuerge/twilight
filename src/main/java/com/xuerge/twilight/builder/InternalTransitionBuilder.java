package com.xuerge.twilight.builder;

import com.xuerge.twilight.Action;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.Transition;
import com.xuerge.twilight.impl.InternalTransition;
import com.xuerge.twilight.impl.MethodInvokeAction;
import com.xuerge.twilight.impl.ExternalTransition;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Data
public class InternalTransitionBuilder<S, E, C> implements WithIn<S, E, C>, Perform<S, E, C> ,TransitionBuilder<S, E, C> {

    private S s;
    private E event;
    private Action action;
    private String methodName;

    public WithIn<S, E, C> withIn(S stateId) {
        this.s = stateId;
        return this;
    }


    @Override
    public Perform on(E event) {
        this.event = event;
        return this;
    }

    @Override
    public S getFrom() {
        return s;
    }

    @Override
    public void setFrom(S from) {
        this.setS(from);
    }

    @Override
    public S getTo() {
        return s;
    }

    @Override
    public void setTo(S to) {
        this.setS(to);
    }

    public Transition build(StateMachineBuilder sb) {
        if (null == action && !StringUtils.isEmpty(methodName)) {
            action = new MethodInvokeAction(sb.getStateMachineImplClazz(), methodName, sb.getMethodCallParamTypes());
        }
        return new InternalTransition(s, event, action);
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
