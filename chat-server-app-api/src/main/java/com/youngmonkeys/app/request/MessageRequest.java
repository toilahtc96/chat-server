package com.youngmonkeys.app.request;

import com.tvd12.ezyfox.binding.annotation.EzyObjectBinding;
import lombok.Data;

@Data
@EzyObjectBinding
public class MessageRequest {

    private String message;
    private long channelId;

}
