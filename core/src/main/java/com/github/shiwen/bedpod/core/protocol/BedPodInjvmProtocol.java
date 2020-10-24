package com.github.shiwen.bedpod.core.protocol;

import com.github.shiwen.bedpod.common.utils.ClassTypeUtils;
import com.github.shiwen.bedpod.common.utils.ClassUtils;
import com.github.shiwen.bedpod.common.utils.StringUtils;
import com.github.shiwen.bedpod.core.annotations.AbilityReference;
import com.github.shiwen.bedpod.core.exporter.BedpodExporter;
import com.github.shiwen.bedpod.core.exporter.Exporter;
import com.github.shiwen.bedpod.core.invoker.ProxyInvoker;
import net.minidev.json.JSONUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    public Exporter export(ProxyInvoker invoker) {
        return exporterMap.putIfAbsent(getServiceKey(invoker), new BedpodExporter(invoker));
    }

    private String getServiceKey(ProxyInvoker invoker) {
        return invoker.getAbilityName() + StringUtils.DOT + invoker.getInterfaceName();
    }

    public ProxyInvoker getExporterByAbilityReference(AbilityReference annotation, Class<?> type) {
        String serviceKey = annotation.value() + StringUtils.DOT + ClassTypeUtils.getTypeStr(type);
        Exporter exporter = exporterMap.get(serviceKey);
        return exporter.getInvoker();
    }
}