package com.youngmonkeys.app.request;

import com.tvd12.ezyfox.binding.annotation.EzyObjectBinding;
import lombok.Data;

import java.util.List;

@Data
@EzyObjectBinding
public class GetChannelRequest {

    List<String> users;
}
