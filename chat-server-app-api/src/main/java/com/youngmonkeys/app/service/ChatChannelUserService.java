package com.youngmonkeys.app.service;

import com.youngmonkeys.app.data.ChatChannelUsers;
import com.youngmonkeys.app.entity.ChatChannelUser;

import java.util.List;

public interface ChatChannelUserService {
    List<ChatChannelUsers> getChannelsOfUser(String username);

    List<Long> getChannelIdsOfUser(String username);

    List<ChatChannelUsers> getChannelsOfUser(List<Long> channelIds, String username);

    void saveChannelUsers(List<ChatChannelUser> channelUsers);

    ChatChannelUsers getChannelUsers(long channelId);
}
