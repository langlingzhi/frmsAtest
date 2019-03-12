package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.appservice.PayOrderService;
import com.nuanshui.frms.test.csmapper.MessageListMapper;
import com.nuanshui.frms.test.csmapper.RepaymentOrderMapper;
import com.nuanshui.frms.test.csservice.MessageListService;
import com.nuanshui.frms.test.csservice.RepaymentOrderService;
import com.nuanshui.frms.test.entity.app.PayOrder;
import com.nuanshui.frms.test.entity.cs.MessageList;
import com.nuanshui.frms.test.entity.cs.RepaymentOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageListServiceImpl implements MessageListService {
    @Autowired
    private MessageListMapper messageListMapper;
    @Autowired
    private PayOrderService payOrderService;

    @Override

    public Long getMessageList(MessageList messagelist) {

        PayOrder payOrder = new PayOrder();
        payOrder.setUserId(12345L);
        payOrder.setOrderNo("1234");
        payOrderService.selectByUserIdForUnclearedOrder(payOrder);
        return messageListMapper.getMessageList(messagelist);
    }
    @Override
    public List<MessageList> searchMessageList(){

        return messageListMapper.searchMessageList();
    }
}
