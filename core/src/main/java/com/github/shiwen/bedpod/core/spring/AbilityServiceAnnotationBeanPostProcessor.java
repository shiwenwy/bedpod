package com.github.shiwen.bedpod.core.spring;

import com.github.shiwen.bedpod.common.annotations.AbilityService;
import com.github.shiwen.bedpod.common.utils.ClassTypeUtils;
import com.github.shiwen.bedpod.core.invoker.ProxyInvoker;
import com.github.shiwen.bedpod.core.protocol.BedPodInjvmProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 能力服务后处理器
 * @author shiwen.wy
 * @date 2020/10/24 3:56 下午
 */
public class AbilityServiceAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbilityServiceAnnotationBeanPostProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        AbilityService abilityService = bean.getClass().getAnnotation(AbilityService.class);
        //abilityService 只能实现一个接口
        if (abilityService == null || bean.getClass().getInterfaces().length != 1) {
            return bean;
        } else {
            try {
                Class<?> interfaceName = bean.getClass().getInterfaces()[0];

                ProxyInvoker proxyInvoker =
                        new ProxyInvoker(abilityService.value(), ClassTypeUtils.getTypeStr(interfaceName), interfaceName, bean);

                BedPodInjvmProtocol.getInjvmProtocol().export(proxyInvoker);
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("AbilityServiceAnnotationBeanPostProcessor error: {}", e);
                }
            }

        }
        return bean;
    }
}
