package com.xuerge.twilight.util;

import java.lang.reflect.Method;

public class ReflectionUtil {

    public static Method getMethod(Class clazz, String methodName, Class[] parameterTypes) {
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
