package com.youngmonkeys.app.service;

import com.youngmonkeys.app.entity.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    ChatMessage newMessage(long channelId, String messageContent, String sender);

    void saveMessage(ChatMessage message);

    List<ChatMessage> getChannelMessages(long channelId);

}
