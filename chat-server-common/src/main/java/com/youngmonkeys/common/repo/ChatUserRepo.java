package com.youngmonkeys.common.repo;

import com.tvd12.ezydata.mongodb.EzyMongoRepository;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.youngmonkeys.common.entity.ChatUser;

@EzySingleton("userRepo")
public interface ChatUserRepo extends EzyMongoRepository<Long, ChatUser> {
}
