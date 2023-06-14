package com.youngmonkeys.app.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.youngmonkeys.app.entity.ChatChannel;
import com.youngmonkeys.app.repo.ChatChannelRepo;
import com.youngmonkeys.app.service.ChatChannelService;
import com.youngmonkeys.common.service.ChatMaxIdService;
import lombok.Setter;

@Setter
@EzySingleton
public class ChatChannelServiceImpl implements ChatChannelService {

    @EzyAutoBind
    ChatMaxIdService maxIdService;

    @EzyAutoBind
    ChatChannelRepo channelRepo;

    @Override
    public long newChannelId() {
        return maxIdService.incrementAndGet("chat_channel");
    }

    @Override
    public void saveChannel(ChatChannel channel) {
        channelRepo.save(channel);
    }
}
