package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.command.example.Mail;
import com.nuanshui.frms.test.config.MailConfig;
import com.nuanshui.frms.test.csservice.FrmsReportService;
import com.nuanshui.frms.test.csservice.FrmsTaskService;
import com.nuanshui.frms.test.csservice.MailService;
import com.nuanshui.frms.test.entity.cs.FrmsReport;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {
    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private FreeMarkerConfigurer configurer;
    @Autowired
    FrmsReportService frmsReportService;

//    @Override
//    public void sendSimpleMail(String sendTo, String titel, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(mailConfig.getEmailFrom());
//        message.setTo(sendTo);
//        message.setSubject(titel);
//        message.setText(content);
//        mailSender.send(message);
//    }
    @Override
    public void sendFreemarker(Mail mail,Integer id)
    {
        try
        {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setFrom(this.mailConfig.getMailUsername());
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setCc(mail.getCcList());
            File file = ResourceUtils.getFile("classpath:templates/frmsreport/report.ftl");//
            helper.addAttachment("report.html", file);
            FrmsReport frmsReport=frmsReportService.selectByPrimaryKey(id);
            System.out.println(frmsReport.toString());
            Map<String, Object> model = new HashMap();
            model.put("Reports", frmsReport);
            try
            {
                Template template = this.configurer.getConfiguration().getTemplate(mail.getTemplate());
                //添加附件

                try
                {
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                    helper.setText(text, true);
                    this.mailSender.send(mimeMessage);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
