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
import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AnnotationStateMachineBuilder extends StateMachineBuilder<Object,Object,Object> {

    public AnnotationStateMachineBuilder(Class<? extends BaseStateMachine> implClazz) {
        super(implClazz, implClazz.getAnnotation(StateMachineDefinition.class).stateType(),
                implClazz.getAnnotation(StateMachineDefinition.class).eventType(),
                implClazz.getAnnotation(StateMachineDefinition.class).contextType());
        transitionFromImplClazz();
    }

    private void transitionFromImplClazz() {
        Transitions trans = (Transitions) stateMachineImplClazz.getAnnotation(Transitions.class);
        Transition[] tranArr = trans.value();
        for(Transition t : tranArr){
            if (TransitionType.EXTERNAL.equals(t.type())) {
                ExternalTransitionBuilder<Object, Object, Object> s = new ExternalTransitionBuilder<>();
                Object from = getEnumObject(stateClazz,t.from());
                Object to = getEnumObject(stateClazz,t.to());
                Object on = getEnumObject(eventClazz,t.on());
                s.from(from).to(to).on(on).invoke(t.callMethod());
                transitionBuilderList.add(s);
            }else if (TransitionType.INTERNAL.equals(t.type())){
                InternalTransitionBuilder<Object, Object, Object> s = new InternalTransitionBuilder<>();
                Object from = getEnumObject(stateClazz,t.from());
                Object on = getEnumObject(eventClazz,t.on());
                s.withIn(from).on(on).invoke(t.callMethod());
                this.transitionBuilderList.add(s);
            }
        }
    }

    private Object getEnumObject(Class enumClazz, String enumName){
        Object[]  enums = enumClazz.getEnumConstants();
        return  Arrays.asList(enums).stream().filter(e->{
            return ((Enum)e).name().equals(enumName);
        }).findFirst().orElse(null);
    }


}
