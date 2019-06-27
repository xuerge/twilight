package com.xuerge.twilight.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface StateMachineTypeParameter {
    Class stateType();
    Class eventType();
    Class contextType();
}
