package com.github.shiwen.bedpod.core.annotations;

import java.lang.annotation.*;

/**
 * <p>扩展点</p>
 * @author shiwen.wy
 * @date 2020/10/24
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Extension {

    /**
     * 扩展点名字
     * @return
     */
    String value();

    /**
     * 扩展点编码，默认不需要，当接口需要编码的时候需要
     *
     * @return 扩展点编码
     */
    byte code() default -1;

    /**
     * 优先级排序，默认不需要
     *
     * @return 排序
     */
    int order() default 0;

    /**
     * 是否覆盖其它低{@link #order()}的同名扩展
     *
     * @return 是否覆盖其它低排序的同名扩展
     * @since 5.2.0
     */
    boolean override() default false;

    /**
     * 排斥其它扩展，可以排斥掉其它低{@link #order()}的扩展
     *
     * @return 排斥其它扩展
     * @since 5.2.0
     */
    String[] rejection() default {};
}
