package com.github_tst_sdet.configuration.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {
    public static Class<?> getListGenericType(Field field) {
        Type genericType = field.getGenericType();

        if (!(genericType instanceof ParameterizedType parameterizedType)) {
            throw new IllegalArgumentException(
                    "Field " + field.getName() + " is not parameterized."
            );
        }

        return (Class<?>) parameterizedType.getActualTypeArguments()[0];
    }
}
