package com.nuanshui.frms.test.testcase.apitest;

import com.nuanshui.frms.test.TestApplication;
import com.nuanshui.frms.test.api.listeners.AutoTestListener;
import com.nuanshui.frms.test.api.listeners.RetryListener;
import com.nuanshui.frms.test.csservice.QuartzService;
import com.nuanshui.frms.test.utils.CronDateUtils;
import com.nuanshui.frms.test.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Listeners;

@Listeners({ AutoTestListener.class, RetryListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(TestApplication.class)
public class sheduleTest {
    @Autowired
    QuartzService quartzService;
    @Test
    public void sheduleTest() throws SchedulerException {
        quartzService.initVopVos().start();
    }

}
