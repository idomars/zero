package me.zhongmin.zero.springboot.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文 获取bean
 */
@Component
public class SpringContext implements ApplicationContextAware {
    /**
     * 上下文对象实例
     */
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String name){
        return applicationContext.getBean(name);
    }
}
