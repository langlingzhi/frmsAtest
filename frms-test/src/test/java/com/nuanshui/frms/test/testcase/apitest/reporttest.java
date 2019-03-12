package com.nuanshui.frms.test.testcase.apitest;


import com.nuanshui.frms.test.TestApplication;
import com.nuanshui.frms.test.api.listeners.AutoTestListener;
import com.nuanshui.frms.test.api.listeners.RetryListener;
import com.nuanshui.frms.test.command.example.ReportInfo;
import com.nuanshui.frms.test.csservice.FrmsRunService;
import com.nuanshui.frms.test.utils.http.ReportUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.List;
@Listeners({ AutoTestListener.class, RetryListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(TestApplication.class)
public class reporttest extends AbstractTestNGSpringContextTests {
    @Autowired
    FrmsRunService frmsRunService;
    @Test
    public void testreport(){
        List<ReportInfo> reportInfos=new ArrayList<>();
//        ReportInfo reportInfo=new ReportInfo();
//        reportInfo.setName("llz");
//        reportInfo.setDescription("");
//        reportInfo.setClassName("");
//        reportInfo.getStatus();
//        reportInfo.setLog("");
//        reportInfo.set
        reportInfos=frmsRunService.frmsruncase();
        ReportUtils.makereport(reportInfos);
    }
}
