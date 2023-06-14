package com.youngmonkeys.app.data;

import com.tvd12.ezyfox.binding.annotation.EzyObjectBinding;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@EzyObjectBinding
public class ChatContact {
    Long id;
    String username;
}
