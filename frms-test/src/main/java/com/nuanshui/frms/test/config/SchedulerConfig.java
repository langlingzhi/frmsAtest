package com.nuanshui.frms.test.config;


import com.nuanshui.frms.test.csservice.QuartzService;
import com.nuanshui.frms.test.quartz.QuartzScheduler;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

@Configuration
@EnableScheduling
public class SchedulerConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private SpringJobFactory myJobFactory;

    @Autowired
    QuartzService quartzService;
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setStartupDelay(1);
        factoryBean.setJobFactory(this.myJobFactory);
        return factoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            quartzService.initVopVos().start();
            System.out.println("定时任务已经启动...");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
