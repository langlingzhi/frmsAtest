package com.nuanshui.frms.test.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringJobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法
        Object jobInstance=super.createJobInstance(bundle);
        // 进行注入
        autowireCapableBeanFactory.autowireBean(jobInstance);

        //此处重写jobfactory是为了让后面继承job的类中可以注入其他service或者其他
        return jobInstance;

    }
}
