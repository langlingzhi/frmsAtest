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
public class Message implements Serializable {
    private String transId;
    private String orderNo;
    private String transTime;
    private String messageContent;
    private String msgOrderStatus;
}
