package com.github.shiwen.bedpod.core.exporter;

import com.github.shiwen.bedpod.core.invoker.ProxyInvoker;

/**
 * @author shiwen.wy
 * @date 2020/10/24 5:23 下午
 */
public interface Exporter {

    ProxyInvoker getInvoker();
}
