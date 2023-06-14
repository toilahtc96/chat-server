package com.youngmonkeys.app.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.youngmonkeys.app.data.ChatChannelUsers;
import com.youngmonkeys.app.entity.ChatChannelUser;
import com.youngmonkeys.app.repo.ChatChannelUserRepo;
import com.youngmonkeys.app.service.ChatChannelUserService;
import lombok.Setter;

import java.util.*;


@Setter
@EzySingleton
public class ChatChannelUserServiceImpl implements ChatChannelUserService {

    @EzyAutoBind
    private ChatChannelUserRepo channelUserRepo;

    @Override
    public List<ChatChannelUsers> getChannelsOfUser(String username) {
        List<Long> channelIds = getChannelIdsOfUser(username);

        return getChannelsOfUser(channelIds, username);
    }

    @Override
    public List<Long> getChannelIdsOfUser(String username) {
        List<ChatChannelUser> channelUserList = channelUserRepo.findByUser(username);
        List<Long> answer = new ArrayList<>();

        for (ChatChannelUser channelUser : channelUserList) {
            answer.add(channelUser.getId().getChannelId());
        }
        return answer;
    }

    @Override
    public List<ChatChannelUsers> getChannelsOfUser(List<Long> channelIds, String username) {
        List<ChatChannelUser> list = channelUserRepo.findByChannelIds(channelIds);

        Map<Long, Set<String>> map = mapChannelUsers(list);

        List<ChatChannelUsers> answer = new ArrayList<>();
        for (Long id : map.keySet()) {
            answer.add(new ChatChannelUsers(id, map.get(id)));
        }

        return answer;
    }

    @Override
    public void saveChannelUsers(List<ChatChannelUser> channelUsers) {
        channelUserRepo.save(channelUsers);
    }

    @Override
    public ChatChannelUsers getChannelUsers(long channelId) {
        List<ChatChannelUser> list = channelUserRepo.findByChannelId(channelId);
        Map<Long, Set<String>> map = mapChannelUsers(list);
        return new ChatChannelUsers(channelId, map.get(channelId));
    }

    private Map<Long, Set<String>> mapChannelUsers(List<ChatChannelUser> list) {
        Map<Long, Set<String>> map = new HashMap<>();
        for (ChatChannelUser item : list) {
            map.computeIfAbsent(item.getId().getChannelId(), k -> new HashSet<>());
            Set<String> s = map.get(item.getId().getChannelId());
            s.add(item.getId().getUsername());
        }

        return map;
    }
}
