package com.github.shiwen.bedpod.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 能力服务
 * @author shiwen.wy
 * @date 2020/10/24 3:45 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface AbilityService {

    /**
     * service名称
     * @return
     */
    String value() default "";
}
