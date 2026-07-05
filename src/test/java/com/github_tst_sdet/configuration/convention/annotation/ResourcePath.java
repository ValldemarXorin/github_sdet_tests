package com.github_tst_sdet.configuration.convention.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface ResourcePath {
    String value() default "";
}
