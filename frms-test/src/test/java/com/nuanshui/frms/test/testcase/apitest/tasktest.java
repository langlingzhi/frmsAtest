package com.nuanshui.frms.test.testcase.apitest;

import com.nuanshui.frms.test.TestApplication;
import com.nuanshui.frms.test.api.listeners.AutoTestListener;
import com.nuanshui.frms.test.api.listeners.RetryListener;
import com.nuanshui.frms.test.command.example.ReportData;
import com.nuanshui.frms.test.controller.frmsapi.FrmsReportController;
import com.nuanshui.frms.test.csservice.FrmsReportService;
import com.nuanshui.frms.test.csservice.FrmsTaskService;
import com.nuanshui.frms.test.entity.cs.FrmsTask;
import com.nuanshui.frms.test.utils.JacksonUtils;
import com.nuanshui.frms.test.utils.http.ReportUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ui.Model;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.util.List;

@Listeners({ AutoTestListener.class, RetryListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(TestApplication.class)
public class tasktest  extends AbstractTestNGSpringContextTests {
    @Autowired
    FrmsTaskService frmsTaskService;
    @Autowired
    FrmsReportService frmsReportService;
    @Test
    public void tasktest(){
        List<FrmsTask> ss=frmsTaskService.selectfrmstaskname();
        ss.toString();
    }
    @Test
    public void reptest() throws IOException {
//        String str=frmsReportService.selectByPrimaryKey(7).getTestResult();
//        System.out.println(str);
        ReportData reportData2=new ReportData();
        reportData2= JacksonUtils.parseJsonFromString(frmsReportService.selectByPrimaryKey(7).getTestResult(),ReportData.class);
        ReportUtils.makereports(reportData2);
    }

}
