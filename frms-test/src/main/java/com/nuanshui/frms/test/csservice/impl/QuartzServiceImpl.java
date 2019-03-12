package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.config.ScheduledJob;
import com.nuanshui.frms.test.csservice.FrmsTaskService;
import com.nuanshui.frms.test.csservice.QuartzService;
import com.nuanshui.frms.test.entity.cs.FrmsTask;
import com.nuanshui.frms.test.utils.CronDateUtils;
import com.nuanshui.frms.test.utils.DateUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    FrmsTaskService frmsTaskService;


    public Scheduler initVopVos( ){
        //这里获取任务信息数据
        List<FrmsTask> jobList = frmsTaskService.selectRunTask();  //从数据库中获取所以今天需要执行的任务


            for (FrmsTask job : jobList) {
                try {
                TriggerKey triggerKey = TriggerKey.triggerKey(job.getTaskname(), String.valueOf(job.getProductid()));

                //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                //不存在，创建一个
                if (null == trigger) {
                    JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class)
                            .withIdentity(job.getTaskname(), String.valueOf(job.getProductid())).build();
                    jobDetail.getJobDataMap().put("job",job);

                    //表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                            .getJobcron());

                    //按新的cronExpression表达式构建一个新的trigger
                    trigger = TriggerBuilder.newTrigger().withIdentity(job.getTaskname(), String.valueOf(job.getProductid())).withSchedule(scheduleBuilder).build();

                    scheduler.scheduleJob(jobDetail, trigger);
                } else {
                    // Trigger已存在，那么更新相应的定时设置
                    //表达式调度构建器,我这里数据库中存的执行时间是一个日期，这里讲date转成cron才能执行
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                            .getJobcron());

                    //按新的cronExpression表达式重新构建trigger
                    trigger = trigger.getTriggerBuilder().startAt(new Date()).withIdentity(triggerKey)
                            .withSchedule(scheduleBuilder).build();
                    //scheduler.rescheduleJob如果服务器当前时间与你的表达式配置的执行时间差在两小时以内时，
                    //动态修改就会出现立即执行的情况。所以这里设置执行时间从当前时间开始

                    JobDataMap jobDataMap = trigger.getJobDataMap();//重新获取JobDataMap，并且更新参数
                    jobDataMap.put("job",job);

                    //按新的trigger重新设置job执行
                    scheduler.rescheduleJob(triggerKey, trigger);
                }
                } catch (SchedulerException e) {
                    System.out.println("initVopVos Error");
                }
            }

        return scheduler;
    }

}
