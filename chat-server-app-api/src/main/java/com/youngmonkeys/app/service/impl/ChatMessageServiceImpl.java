package com.youngmonkeys.app.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.youngmonkeys.app.entity.ChatMessage;
import com.youngmonkeys.app.repo.ChatMessageRepo;
import com.youngmonkeys.app.service.ChatMessageService;
import com.youngmonkeys.common.service.ChatMaxIdService;
import lombok.Setter;

import java.util.List;

@Setter
@EzySingleton
public class ChatMessageServiceImpl implements ChatMessageService {

    @EzyAutoBind
    private ChatMaxIdService maxIdService;

    @EzyAutoBind
    private ChatMessageRepo messageRepo;

    @Override
    public ChatMessage newMessage(long channelId, String messageContent, String sender) {
        long newId = maxIdService.incrementAndGet("chat_message");

        return new ChatMessage(
                newId, messageContent, channelId, sender
        );
    }

    @Override
    public void saveMessage(ChatMessage message) {
        messageRepo.save(message);
    }

    @Override
    public List<ChatMessage> getChannelMessages(long channelId) {
        return messageRepo.findByChannelId(channelId);
    }
}
