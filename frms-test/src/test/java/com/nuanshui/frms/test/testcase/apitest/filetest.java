package com.nuanshui.frms.test.testcase.apitest;

import com.nuanshui.frms.test.utils.CronDateUtils;
import com.nuanshui.frms.test.utils.DateUtils;
import com.nuanshui.frms.test.utils.IOUtil;
import com.nuanshui.frms.test.utils.PathUtil;
import org.junit.Test;
import org.quartz.SchedulerException;

import java.io.File;

public class filetest {
    @Test
    public void filereadtest(){
        String fileName ="C:\\workspacegit\\frms-test\\frms-test\\src\\main\\resources\\static\\json\\iframetree.json";
        System.out.println(fileName);
        File file =new File(fileName);
        String rep = IOUtil.readStringFromFile(file, "UTF-8");
        System.out.println(rep);

    }
    @Test
    public void sheduleTest2() throws SchedulerException {
        String cron= CronDateUtils.getCron(DateUtils.uvdate("2018-12-24 16:10:30"));
        System.out.println(cron);
    }
}
