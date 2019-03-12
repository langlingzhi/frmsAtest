package com.nuanshui.frms.test.quartz;

import com.nuanshui.frms.test.csservice.QuartzService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzScheduler {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    QuartzService quartzService;
    public void startJob() throws SchedulerException {
        quartzService.initVopVos().start();

    }
}
