package com.nuanshui.frms.test.entity.cs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageList implements Serializable {
    private Long id;
    private String message_order_no;
    private String order_no;
    private String loan_person;
    private String telphone;
    private String dispatch_user;
    private String message_content;
    private String message_create_time;
    private String message_source;

}
