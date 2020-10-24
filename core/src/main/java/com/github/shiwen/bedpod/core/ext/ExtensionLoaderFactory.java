package com.github.shiwen.bedpod.core.ext;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author shiwen.wy
 * @date 2020/10/24 12:20 下午
 */
public class ExtensionLoaderFactory {
    private ExtensionLoaderFactory() {
    }

    /**
     * All extension loader {Class : ExtensionLoader}
     */
    private static final ConcurrentMap<Class, ExtensionLoader> LOADER_MAP =
        new ConcurrentHashMap<Class, ExtensionLoader>();

    /**
     * Get extension loader by extensible class with listener
     * <p>
     * This method is deprecated, use com.alipay.sofa.rpc.ext.ExtensionLoaderFactory#getExtensionLoader(java.lang.Class) instead.
     * Use com.alipay.sofa.rpc.ext.ExtensionLoader#addListener(com.alipay.sofa.rpc.ext.ExtensionLoaderListener) to add listener.
     *
     * @param clazz Extensible class
     * @param <T>   Class
     *
     * @return ExtensionLoader of this class
     *
     * @deprecated
     */
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> clazz) {
        ExtensionLoader<T> loader = LOADER_MAP.get(clazz);
        if (loader == null) {
            synchronized (ExtensionLoaderFactory.class) {
                loader = LOADER_MAP.get(clazz);
                if (loader == null) {
                    loader = new ExtensionLoader<T>(clazz);
                    LOADER_MAP.put(clazz, loader);
                }
            }
        }
        return loader;
    }
}
