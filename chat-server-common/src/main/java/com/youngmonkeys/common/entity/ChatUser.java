package com.youngmonkeys.common.entity;

import com.tvd12.ezyfox.annotation.EzyId;
import com.tvd12.ezyfox.database.annotation.EzyCollection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EzyCollection
@ToString
public class ChatUser {

    @EzyId
    private int id;

    private String username;
    private String password;
}
