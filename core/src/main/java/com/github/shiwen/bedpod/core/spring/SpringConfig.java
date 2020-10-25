package com.github.shiwen.bedpod.core.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shiwen.wy
 * @date 2020/10/24 9:56 下午
 */
@Configuration
public class SpringConfig {

    @Bean
    public AbilityServiceAnnotationBeanPostProcessor abilityServiceAnnotationBeanPostProcessor(){
        return new AbilityServiceAnnotationBeanPostProcessor();
    }
}
