package com.youngmonkeys.app.service;

import com.youngmonkeys.app.entity.ChatChannel;

public interface ChatChannelService {
    long newChannelId();

    void saveChannel(ChatChannel channel);
}
