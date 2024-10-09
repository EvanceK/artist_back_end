package com.artist.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanLogger implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        System.out.println("Beans in the application context:");
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}