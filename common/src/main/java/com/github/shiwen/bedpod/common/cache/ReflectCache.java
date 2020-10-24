package com.github.shiwen.bedpod.common.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author shiwen.wy
 * @date 2020/10/24 11:56 上午
 */
public class ReflectCache {

    /**
     * Class-->String 缓存
     */
    static final ConcurrentMap<Class, String> TYPE_STR_CACHE = new ConcurrentHashMap<Class, String>();

    /**
     * 得到类描述缓存
     *
     * @param clazz 类
     * @return 类描述
     */
    public static String getTypeStrCache(Class clazz) {
        return TYPE_STR_CACHE.get(clazz);
    }

    /**
     * 放入类描述缓存
     *
     * @param clazz   类
     * @param typeStr 对象描述
     */
    public static void putTypeStrCache(Class clazz, String typeStr) {
        TYPE_STR_CACHE.put(clazz, typeStr);
    }
}
