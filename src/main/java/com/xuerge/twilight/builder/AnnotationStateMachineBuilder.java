package com.xuerge.twilight.builder;


import com.google.common.collect.Maps;
import com.xuerge.twilight.StateData;
import com.xuerge.twilight.TransitionType;
import com.xuerge.twilight.annotation.*;
import com.xuerge.twilight.impl.BaseStateMachine;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class AnnotationStateMachineBuilder extends StateMachineBuilder<Object, Object, Object> {

    public AnnotationStateMachineBuilder(Class<? extends BaseStateMachine> implClazz) {
        super(implClazz, implClazz.getAnnotation(StateMachineTypeParameter.class).stateType(),
                implClazz.getAnnotation(StateMachineTypeParameter.class).eventType(),
                implClazz.getAnnotation(StateMachineTypeParameter.class).contextType());
        transitionFromImplClazz();
        statesFromImplClazz();
    }

    private void statesFromImplClazz() {
        Annotation statesAnno = stateMachineImplClazz.getAnnotation(States.class);
        if (null == statesAnno)
            return;

        if (null == states) {
            states = Maps.newConcurrentMap();
        }
        State[] stateArr = ((States) statesAnno).value();
        for (State s : stateArr) {
            StateData stateData = states.get(s.name());
            if (stateData == null) {
                stateData = new StateData(s.name());
                states.put(s.name(), stateData);
            }
            if (StringUtils.isNotBlank(s.entryCallMethod()))
                stateData.setEntryAction(buildMethodInvokeAction(s.entryCallMethod()));
            if (StringUtils.isNotBlank(s.exitCallMethod()))
                stateData.setLeaveAction(buildMethodInvokeAction(s.exitCallMethod()));
        }
    }

    private void transitionFromImplClazz() {
        Transitions trans = (Transitions) stateMachineImplClazz.getAnnotation(Transitions.class);
        Transition[] tranArr = trans.value();
        for (Transition t : tranArr) {
            if (TransitionType.EXTERNAL.equals(t.type())) {
                ExternalTransitionBuilder<Object, Object, Object> s = new ExternalTransitionBuilder<>();
                Object from = getEnumObject(stateClazz, t.from());
                Object to = getEnumObject(stateClazz, t.to());
                Object on = getEnumObject(eventClazz, t.on());
                s.from(from).to(to).on(on).invoke(t.callMethod());
                transitionBuilderList.add(s);
            } else if (TransitionType.INTERNAL.equals(t.type())) {
                InternalTransitionBuilder<Object, Object, Object> s = new InternalTransitionBuilder<>();
                Object from = getEnumObject(stateClazz, t.from());
                Object on = getEnumObject(eventClazz, t.on());
                s.withIn(from).on(on).invoke(t.callMethod());
                this.transitionBuilderList.add(s);
            }
        }
    }

    private Object getEnumObject(Class enumClazz, String enumName) {
        Object[] enums = enumClazz.getEnumConstants();
        return Arrays.asList(enums).stream().filter(e -> {
            return ((Enum) e).name().equals(enumName);
        }).findFirst().orElse(null);
    }


}
