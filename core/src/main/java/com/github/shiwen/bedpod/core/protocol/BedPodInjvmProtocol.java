package com.github.shiwen.bedpod.core.protocol;

import com.github.shiwen.bedpod.common.annotations.AbilityReference;
import com.github.shiwen.bedpod.common.utils.ClassTypeUtils;
import com.github.shiwen.bedpod.common.utils.StringUtils;
import com.github.shiwen.bedpod.core.exporter.BedpodExporter;
import com.github.shiwen.bedpod.core.exporter.Exporter;
import com.github.shiwen.bedpod.core.invoker.ProxyInvoker;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.shiwen.bedpod.common.utils.ClassLoaderUtils.getCurrentClassLoader;

/**
 * @author shiwen.wy
 * @date 2020/10/24 5:14 下午
 */
public class BedPodInjvmProtocol {

    private static BedPodInjvmProtocol INSTANCE;

    private final Map<String, Exporter> exporterMap = new ConcurrentHashMap<String, Exporter>();

    public BedPodInjvmProtocol() {
        INSTANCE = this;
    }

    public static BedPodInjvmProtocol getInjvmProtocol() {
        if (INSTANCE == null) {
            synchronized (BedPodInjvmProtocol.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BedPodInjvmProtocol();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 导出exporter
     * @param invoker
     * @return
     */
    public Exporter export(ProxyInvoker invoker) {
        return exporterMap.putIfAbsent(getServiceKey(invoker), new BedpodExporter(invoker));
    }

    /**
     * 获得代理
     * @param annotation
     * @param type
     * @return
     */
    public Object refer(AbilityReference annotation, Class<?> type) {

        return Proxy.newProxyInstance(getCurrentClassLoader(), new Class[]{type},
                getExporterByAbilityReference(annotation, type));
    }

    private String getServiceKey(ProxyInvoker invoker) {
        return invoker.getAbilityName() + StringUtils.DOT + invoker.getInterfaceName();
    }

    private ProxyInvoker getExporterByAbilityReference(AbilityReference annotation, Class<?> type) {
        String serviceKey = annotation.value() + StringUtils.DOT + ClassTypeUtils.getTypeStr(type);
        Exporter exporter = exporterMap.get(serviceKey);
        return exporter.getInvoker();
    }
}
