package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.entity.cs.MessageList;

import java.util.List;

public interface MessageListService {
    Long getMessageList(MessageList messagelist);
    List<MessageList> searchMessageList();
}
