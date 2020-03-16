package com.paic.cost.util;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/28 0:03
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static  Object getBean(String name){
        return  applicationContext.getBean(name);
    }

    public static  <T> T getBean(Class<T> tClass){
        return  applicationContext.getBean(tClass);
    }
}
