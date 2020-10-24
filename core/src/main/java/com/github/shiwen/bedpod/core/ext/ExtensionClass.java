package com.github.shiwen.bedpod.core.ext;

import com.github.shiwen.bedpod.common.base.Sortable;
import com.github.shiwen.bedpod.common.exception.BedpodRuntimeException;
import com.github.shiwen.bedpod.common.utils.ClassUtils;

import java.util.Arrays;

/**
 * @author shiwen.wy
 * @date 2020/10/24 11:26 上午
 */
public class ExtensionClass<T> implements Sortable {
    /**
     * 扩展接口实现类名
     */
    protected final Class<? extends T> clazz;
    /**
     * 扩展别名,不是provider uniqueId
     */
    protected final String             alias;
    /**
     * 扩展编码，必须唯一
     */
    protected byte                     code;
    /**
     * 是否单例
     */
    protected boolean                  singleton;

    /**
     * 扩展点排序值，大的优先级高
     */
    protected int                      order;

    /**
     * 是否覆盖其它低{@link #order}的同名扩展
     */
    protected boolean                  override;

    /**
     * 排斥其它扩展，可以排斥掉其它低{@link #order}的扩展
     */
    protected String[]                 rejection;

    /**
     * 服务端实例对象（只在是单例的时候保留）
     */
    private volatile transient T       instance;

    /**
     * 构造函数
     *
     * @param clazz 扩展实现类名
     * @param alias 扩展别名
     */
    public ExtensionClass(Class<? extends T> clazz, String alias) {
        this.clazz = clazz;
        this.alias = alias;
    }

    /**
     * 得到服务端实例对象，如果是单例则返回单例对象，如果不是则返回新创建的实例对象
     *
     * @return 扩展点对象实例
     */
    public T getExtInstance() {
        return getExtInstance(null, null);
    }

    /**
     * 得到服务端实例对象，如果是单例则返回单例对象，如果不是则返回新创建的实例对象
     *
     * @param argTypes 构造函数参数类型
     * @param args     构造函数参数值
     * @return 扩展点对象实例 ext instance
     */
    public T getExtInstance(Class[] argTypes, Object[] args) {
        if (clazz != null) {
            try {
                if (singleton) { // 如果是单例
                    if (instance == null) {
                        synchronized (this) {
                            if (instance == null) {
                                instance = ClassUtils.newInstanceWithArgs(clazz, argTypes, args);
                            }
                        }
                    }
                    return instance; // 保留单例
                } else {
                    return ClassUtils.newInstanceWithArgs(clazz, argTypes, args);
                }
            } catch (BedpodRuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new BedpodRuntimeException(clazz.getCanonicalName()+"创建扩展失败", e);
            }
        }
        throw new BedpodRuntimeException("ERROR_EXTENSION_CLASS_NULL");
    }

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public byte getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     * @return the code
     */
    public ExtensionClass setCode(byte code) {
        this.code = code;
        return this;
    }

    /**
     * Is singleton boolean.
     *
     * @return the boolean
     */
    public boolean isSingleton() {
        return singleton;
    }

    /**
     * Sets singleton.
     *
     * @param singleton the singleton
     */
    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    public Class<? extends T> getClazz() {
        return clazz;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     * @return the order
     */
    public ExtensionClass setOrder(int order) {
        this.order = order;
        return this;
    }

    /**
     * Is override boolean.
     *
     * @return the boolean
     */
    public boolean isOverride() {
        return override;
    }

    /**
     * Sets override.
     *
     * @param override the override
     * @return the override
     */
    public ExtensionClass setOverride(boolean override) {
        this.override = override;
        return this;
    }

    /**
     * Get rejection string [ ].
     *
     * @return the string [ ]
     */
    public String[] getRejection() {
        return rejection;
    }

    /**
     * Sets rejection.
     *
     * @param rejection the rejection
     * @return the rejection
     */
    public ExtensionClass setRejection(String[] rejection) {
        this.rejection = rejection;
        return this;
    }

    @Override
    public String toString() {
        return "ExtensionClass{" +
            "clazz=" + clazz +
            ", alias='" + alias + '\'' +
            ", code=" + code +
            ", singleton=" + singleton +
            ", order=" + order +
            ", override=" + override +
            ", rejection=" + Arrays.toString(rejection) +
            ", instance=" + instance +
            '}';
    }
}
