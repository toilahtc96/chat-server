package com.youngmonkeys.common.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.youngmonkeys.common.entity.ChatUser;
import com.youngmonkeys.common.repo.ChatUserRepo;
import com.youngmonkeys.common.service.ChatUserService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@EzySingleton
@AllArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {

    private ChatUserRepo chatUserRepo;
    @Override
    public void saveUser(ChatUser chatUser) {
        chatUserRepo.save(chatUser);
    }

    @Override
    public ChatUser createUser(String username, String password) {
        ChatUser chatUser1 = new ChatUser();
        chatUser1.setUsername(username);
        chatUser1.setPassword(password);
        chatUserRepo.save(chatUser1);
        return chatUser1;
    }

    @Override
    public ChatUser getUser(String username) {
        return chatUserRepo.findByField("username", username);
    }
}
