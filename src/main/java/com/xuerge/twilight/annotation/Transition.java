package com.xuerge.twilight.annotation;

import com.xuerge.twilight.TransitionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Transition {
    String from();



    String to();

    String on();

    TransitionType type() default TransitionType.EXTERNAL;

    String callMethod() default "";

}
