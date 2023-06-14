package com.youngmonkeys.app.data;

import com.tvd12.ezyfox.binding.annotation.EzyObjectBinding;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@EzyObjectBinding(read = false)
public class ChatChannelUsers {

    protected long channelId;
    protected Set<String> usernames;
}
