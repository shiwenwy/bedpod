package com.github.shiwen.bedpod.core.exporter;

import com.github.shiwen.bedpod.core.invoker.ProxyInvoker;

/**
 * @author shiwen.wy
 * @date 2020/10/24 5:04 下午
 */
public class BedpodExporter implements Exporter{

    private ProxyInvoker invoker;

    public BedpodExporter(ProxyInvoker invoker) {
        this.invoker = invoker;
    }
}
