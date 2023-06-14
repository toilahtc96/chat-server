package com.youngmonkeys.app.entity;

import com.tvd12.ezyfox.annotation.EzyId;
import com.tvd12.ezyfox.binding.annotation.EzyObjectBinding;
import com.tvd12.ezyfox.database.annotation.EzyCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@EzyCollection
@NoArgsConstructor
@AllArgsConstructor
@EzyObjectBinding
public class ChatMessage {
    @EzyId
    Long id;

    String message;
    long channelId;
    String sender;
}
