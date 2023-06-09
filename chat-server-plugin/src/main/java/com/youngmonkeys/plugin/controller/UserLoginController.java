package com.youngmonkeys.plugin.controller;

import com.tvd12.ezyfox.entity.EzyHashMap;
import com.youngmonkeys.plugin.service.LoginService;
import com.youngmonkeys.plugin.service.WelcomeService;

import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.core.annotation.EzyEventHandler;
import com.tvd12.ezyfoxserver.context.EzyPluginContext;
import com.tvd12.ezyfoxserver.controller.EzyAbstractPluginEventController;
import com.tvd12.ezyfoxserver.event.EzyUserLoginEvent;

import java.util.HashMap;

import static com.tvd12.ezyfoxserver.constant.EzyEventNames.USER_LOGIN;

@EzySingleton
@EzyEventHandler(USER_LOGIN)
public class UserLoginController extends EzyAbstractPluginEventController<EzyUserLoginEvent> {

    @EzyAutoBind
    private WelcomeService welcomeService;
    @EzyAutoBind
    private LoginService loginService;

    @Override
    public void handle(EzyPluginContext ctx, EzyUserLoginEvent event) {
        EzyHashMap data = event.getData();
        data.get("accessToken");
        logger.info("{} login in", welcomeService.welcome(event.getUsername()));
        loginService.login(event.getUsername(), event.getPassword());
    }
}
