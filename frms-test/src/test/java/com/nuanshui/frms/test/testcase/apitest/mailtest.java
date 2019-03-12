package com.nuanshui.frms.test.testcase.apitest;


import com.nuanshui.frms.test.TestApplication;
import com.nuanshui.frms.test.api.listeners.AutoTestListener;
import com.nuanshui.frms.test.api.listeners.RetryListener;
import com.nuanshui.frms.test.command.example.Mail;
import com.nuanshui.frms.test.config.MailConfig;
import com.nuanshui.frms.test.csservice.MailService;
import com.nuanshui.frms.test.utils.http.MailUtils;
import org.springframework.beans.factory.annotation.Value;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Listeners;

@Listeners({AutoTestListener.class, RetryListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(TestApplication.class)
public class mailtest {

    @Autowired
    MailService mailService;

    @Test
    public void mailtest() {
        String sendTo = "1737477724@qq.com";
        String titel = "测试邮件标题";
        String content = "测试邮件内容";
//        mailService.sendSimpleMail(sendTo, titel, content);

    }
    @Test
    public void mailsendtest() {

        Mail mail=new Mail();
        mail.setTo("1737477724@qq.com");
        mail.setSubject("接口自动化定时任务_测试报告");
        mail.setCcList("lianglingzhi@heatedloan.com");
        mail.setTemplate("report.ftl");
        mailService.sendFreemarker(mail,9);
    }




}
