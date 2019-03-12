package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.command.example.Mail;

public interface MailService {
//    public void sendSimpleMail(String sendTo, String titel, String content);
    public void sendFreemarker(Mail mail,Integer id);

}
