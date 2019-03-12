package com.nuanshui.frms.test.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nuanshui.frms.test.command.example.Mail;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.csservice.FrmsReportService;
import com.nuanshui.frms.test.csservice.FrmsRunService;
import com.nuanshui.frms.test.csservice.MailService;
import com.nuanshui.frms.test.entity.cs.FrmsTask;
import com.nuanshui.frms.test.utils.JacksonUtils;
import com.nuanshui.frms.test.utils.http.ReportUtils;
import net.sf.json.JSONObject;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.Map;

public class ScheduledJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);

    @Autowired
    FrmsRunService frmsRunService;

    @Autowired
    FrmsReportService frmsReportService;
    @Autowired
    MailService mailService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job开始：" + System.currentTimeMillis());

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        FrmsTask frmsTask = (FrmsTask) dataMap.get("job");
        try {
            Integer id = frmsTask.getId();
            ReportData reportData = new ReportData();
            reportData = frmsRunService.runfrmstask(id);
            frmsReportService.insertfrmsreport(reportData);
            Integer repid =frmsReportService.selectByReportId(reportData.getReportId());
            ReportUtils.makereports(reportData);
            Mail mail = new Mail();
            mail.setTo(frmsTask.getEmail());
            mail.setSubject("接口自动化定时任务_测试报告");
            mail.setCcList("lianglingzhi@heatedloan.com");
            mail.setTemplate("report.ftl");
            mailService.sendFreemarker(mail, repid);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }
        System.out.println("job结束：" + System.currentTimeMillis());

    }
}

