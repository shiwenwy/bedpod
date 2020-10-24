package com.github.shiwen.bedpod.common.utils;

/**
 * 一些通用方法
 * @author shiwen.wy
 * @date 2020/10/24 12:14 下午
 */
public class CommonUtils {

    /**
     * 判断一个Array是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断一个Array是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }
}
