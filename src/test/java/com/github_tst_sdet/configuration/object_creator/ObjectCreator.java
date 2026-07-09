package com.github_tst_sdet.configuration.object_creator;

import com.github_tst_sdet.configuration.annotation.FieldName;
import com.github_tst_sdet.configuration.handler.Interpolator;
import com.github_tst_sdet.configuration.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectCreator {
    private Interpolator interpolator = new Interpolator();

    public Object createObject(Map<String, Object> data, Class<?> clazz) {
        try {
            Object object = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                Object value = field.getAnnotation(FieldName.class) == null ? data.get(field.getName())
                        : data.get(field.getAnnotation(FieldName.class).value());

                if (value instanceof String str
                        && str.startsWith("${")
                        && str.endsWith("}")) {
                    value = interpolator.interpolate(str);
                }

                if (value instanceof Map<?, ?> map) {
                    value = createObject((Map<String, Object>) map, field.getType());
                }

                if (value instanceof List<?> list
                        && !list.isEmpty()
                        && list.getFirst() instanceof Map<?, ?>) {

                    Class<?> elementType = ReflectionUtils.getListGenericType(field);

                    value = list.stream()
                            .map(item -> createObject((Map<String, Object>) item, elementType))
                            .collect(Collectors.toList());
                }

                field.set(object, convert(value, field.getType()));
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object convert(Object value, Class<?> targetType) {
        if (value == null) {
            return null;
        }

        if (targetType.isAssignableFrom(value.getClass())) {
            return value;
        }

        String str = value.toString();

        if (targetType == String.class) {
            return str;
        }

        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(str);
        }

        if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(str);
        }

        if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(str);
        }

        if (targetType == float.class || targetType == Float.class) {
            return Float.parseFloat(str);
        }

        if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(str);
        }

        if (targetType == short.class || targetType == Short.class) {
            return Short.parseShort(str);
        }

        if (targetType == byte.class || targetType == Byte.class) {
            return Byte.parseByte(str);
        }

        if (targetType == char.class || targetType == Character.class) {
            if (str.length() != 1) {
                throw new IllegalArgumentException("Cannot convert \"" + str + "\" to char.");
            }
            return str.charAt(0);
        }

        if (targetType.isEnum()) {
            return Enum.valueOf((Class<Enum>) targetType, str);
        }

        throw new IllegalArgumentException("Unsupported type: " + targetType.getName());
    }
}
