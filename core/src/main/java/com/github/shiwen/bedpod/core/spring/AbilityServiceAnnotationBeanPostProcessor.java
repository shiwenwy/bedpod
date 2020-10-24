package com.github.shiwen.bedpod.core.spring;

import com.github.shiwen.bedpod.common.utils.ClassTypeUtils;
import com.github.shiwen.bedpod.core.annotations.AbilityService;
import com.github.shiwen.bedpod.core.invoker.ProxyInvoker;
import com.github.shiwen.bedpod.core.protocol.BedPodInjvmProtocol;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author shiwen.wy
 * @date 2020/10/24 3:56 下午
 */
@Component public class AbilityServiceAnnotationBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        AbilityService abilityService = bean.getClass().getAnnotation(AbilityService.class);
        //abilityService 只能实现一个接口
        if (abilityService == null || bean.getClass().getInterfaces().length != 0) {
            return bean;
        } else {
            Class<?> interfaceName = bean.getClass().getInterfaces()[0];

            ProxyInvoker proxyInvoker =
                new ProxyInvoker(abilityService.value(), ClassTypeUtils.getTypeStr(interfaceName), interfaceName, bean);

            BedPodInjvmProtocol.getInjvmProtocol().export(proxyInvoker);
        }
        return bean;
    }
}
