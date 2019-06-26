package com.xuerge.twilight.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface StateMachineDefinition {
    Class stateType();
    Class eventType();
    Class contextType();
}
