package com.github.shiwen.bedpod.core.ext;

import com.github.shiwen.bedpod.common.base.Sortable;

/**
 * @author shiwen.wy
 * @date 2020/10/24 11:26 上午
 */
public class ExtensionClass<T> implements Sortable {
    public int getOrder() {
        return 0;
    }
}
