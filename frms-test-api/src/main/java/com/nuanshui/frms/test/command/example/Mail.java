package com.nuanshui.frms.test.command.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mail implements Serializable {
    private static final long serialVersionUID = 1L;
    //必填参数
    private String to;//接收方邮件
    private String subject;//主题
    private String ccList;//邮件内容
    //选填
    private String template;//模板
    private HashMap<String, String> kvMap;// 自定义参数
}
