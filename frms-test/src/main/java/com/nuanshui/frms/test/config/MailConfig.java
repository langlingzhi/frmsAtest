package com.nuanshui.frms.test.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
@Configuration
public class MailConfig
{
    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }


    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;
    @Value("${spring.mail.mailTemplate}")
    private String mailTemplate;//邮件模板
    @Value("${spring.mail.to}")
    private String to;
    @Value("${spring.mail.subject}")
    private String subject;
    @Value("${spring.mail.default-encoding}")
    private String encoding;
    @Value("${spring.mail.ccList}")
    private String ccList;

    public void setCcList(String ccList)
    {
        this.ccList = ccList;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public void setMailTemplate(String mailTemplate)
    {
        this.mailTemplate = mailTemplate;
    }

    public void setMailPassword(String mailPassword)
    {
        this.mailPassword = mailPassword;
    }

    public void setMailUsername(String mailUsername)
    {
        this.mailUsername = mailUsername;
    }

    public void setMailHost(String mailHost)
    {
        this.mailHost = mailHost;
    }

    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }

    public String getMailHost()
    {
        return this.mailHost;
    }

    public String getMailUsername()
    {
        return this.mailUsername;
    }

    public String getMailPassword()
    {
        return this.mailPassword;
    }

    public String getMailTemplate()
    {
        return this.mailTemplate;
    }

    public String getTo()
    {
        return this.to;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public String getEncoding()
    {
        return this.encoding;
    }

    public String getCcList()
    {
        return this.ccList;
    }

    @Bean
    public JavaMailSenderImpl mailSender()
    {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Properties javaMailProperties = new Properties();


        sender.setJavaMailProperties(javaMailProperties);
        sender.setDefaultEncoding(this.encoding);
        sender.setHost(this.mailHost);

        sender.setUsername(this.mailUsername);
        sender.setPassword(this.mailPassword);
        return sender;
    }
}
