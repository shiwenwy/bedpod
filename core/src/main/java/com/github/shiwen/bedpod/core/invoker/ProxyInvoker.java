package com.github.shiwen.bedpod.core.invoker;

/**
 * @author shiwen.wy
 * @date 2020/10/24 4:54 下午
 */
public class ProxyInvoker<T> {

    /**
     * 能力名称
     */
    private String abilityName;

    /**
     * 当前加载的接口类名
     */
    private final Class<T> interfaceClass;

    /**
     * 接口名字
     */
    private final String interfaceName;

    private T proxy;

    public ProxyInvoker(String abilityName, String interfaceName, Class<T> interfaceClass, T proxy) {
        this.abilityName = abilityName;
        this.interfaceName = interfaceName;
        this.interfaceClass = interfaceClass;
        this.proxy = proxy;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public Class<T> getInterfaceClass() {
        return interfaceClass;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public T getProxy() {
        return proxy;
    }

    public void setProxy(T proxy) {
        this.proxy = proxy;
    }
}
