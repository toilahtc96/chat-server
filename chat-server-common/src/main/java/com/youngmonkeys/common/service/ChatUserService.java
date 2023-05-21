package com.youngmonkeys.common.service;

import com.youngmonkeys.common.entity.ChatUser;

public interface ChatUserService {
    void saveUser(ChatUser chatUser);

    ChatUser createUser(String username, String password);

    ChatUser getUser(String username);
}
