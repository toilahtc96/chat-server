package com.youngmonkeys.plugin.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.youngmonkeys.common.entity.ChatUser;
import com.youngmonkeys.common.service.ChatUserService;
import com.youngmonkeys.plugin.service.LoginService;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class LoginServiceImpl extends EzyLoggable implements LoginService {
    private ChatUserService chatUserService;

    @Override
    public void login(String username, String password) {
        ChatUser getByUsername = chatUserService.getUser(username);
        if (getByUsername == null) {
            logger.info("user {} not found, create one!", username);
            chatUserService.createUser(username, password);
            logger.info("welcome: {}", username);
        } else {
            if (!password.equals(getByUsername.getPassword())) {
                logger.info("password not correct!");
            } else {
                logger.info("welcome: {}", username);
            }
        }

    }
}
