package com.youngmonkeys.plugin.service.impl;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.io.EzyStrings;
import com.tvd12.ezyfox.security.EzySHA256;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyfoxserver.constant.EzyLoginError;
import com.tvd12.ezyfoxserver.exception.EzyLoginErrorException;
import com.youngmonkeys.common.entity.ChatUser;
//import com.youngmonkeys.common.service.AccessTokenService;
import com.youngmonkeys.common.service.ChatUserService;
import com.youngmonkeys.plugin.service.LoginService;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class LoginServiceImpl extends EzyLoggable implements LoginService {
  private ChatUserService chatUserService;
//  private AccessTokenService accessTokenService;

//  @Override
//  public void login(String username, String password, String accessToken) {
//    if (username != null && password != null && !EzyStrings.isBlank(username) && !EzyStrings.isBlank(password)) {
//      doLoginWithUsernamePassword(username, password);
//    } else {
//      if (accessToken == null || EzyStrings.isBlank(accessToken)) {
//        throw new EzyLoginErrorException(EzyLoginError.INVALID_USERNAME);
//      }
//    }
//    if (accessToken != null && !EzyStrings.isBlank(accessToken)) {
////      accessTokenService.verifyToken(accessToken);
//    }
//
//  }

  private void doLoginWithUsernamePassword(String username, String password) {
    password = encodePassword(password);
    ChatUser getByUsername = chatUserService.getUser(username);
    if (getByUsername == null) {
      logger.info("user {} not found, create one!", username);
      chatUserService.createUser(username, password);
      logger.info("welcome: {}", username);
    } else {
      if (!password.equals(getByUsername.getPassword())) {
        logger.info("password not correct!");
        throw new EzyLoginErrorException(EzyLoginError.INVALID_PASSWORD);
      } else {
        logger.info("welcome: {}", username);
      }
    }
  }

  private String encodePassword(String password) {
    return EzySHA256.cryptUtfToLowercase(password);
  }

  @Override
  public void login(String username, String password) {
    doLoginWithUsernamePassword(username, password);
  }
}
