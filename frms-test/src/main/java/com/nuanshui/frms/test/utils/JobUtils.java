package com.nuanshui.frms.test.utils;

import com.nuanshui.frms.test.config.ScheduledJob;
import com.nuanshui.frms.test.csservice.FrmsTaskService;
import com.nuanshui.frms.test.csservice.QuartzService;
import com.nuanshui.frms.test.entity.cs.FrmsTask;
import com.nuanshui.frms.test.utils.CronDateUtils;
import com.nuanshui.frms.test.utils.DateUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Date;

public class JobUtils {

    @Autowired
    static
    Scheduler scheduler;
    public static boolean modifyJob(FrmsTask job) throws SchedulerException {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getTaskname(), String.valueOf(job.getProductid()));

            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getJobcron());

                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().startAt(new Date()).withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();
                //scheduler.rescheduleJob如果服务器当前时间与你的表达式配置的执行时间差在两小时以内时，
                //动态修改就会出现立即执行的情况。所以这里设置执行时间从当前时间开始

                JobDataMap jobDataMap = trigger.getJobDataMap();//重新获取JobDataMap，并且更新参数
                jobDataMap.put("job", job);

                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);

        }catch (SchedulerException e) {
            System.out.println("modifyVopVos Error");
        }
        return true;
    }

}
