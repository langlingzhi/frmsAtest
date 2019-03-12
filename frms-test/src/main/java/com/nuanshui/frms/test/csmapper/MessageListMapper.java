package com.nuanshui.frms.test.csmapper;

import com.nuanshui.frms.test.entity.cs.MessageList;

import java.util.List;

public interface MessageListMapper {
    Long getMessageList(MessageList messagelist);
    List<MessageList> searchMessageList();
}
