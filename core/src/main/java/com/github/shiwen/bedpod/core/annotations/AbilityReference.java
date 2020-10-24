package com.github.shiwen.bedpod.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 能力引用
 * @author shiwen.wy
 * @date 2020/10/24 3:48 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD})
public @interface AbilityReference {

    String value() default "";
}
