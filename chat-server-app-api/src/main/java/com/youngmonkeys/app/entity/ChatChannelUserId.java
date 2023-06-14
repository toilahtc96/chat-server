package com.youngmonkeys.app.entity;

import com.tvd12.ezyfox.annotation.EzyId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@EzyId
@NoArgsConstructor
@AllArgsConstructor
public class ChatChannelUserId {
    private Long channelId;
    private String username;
}
