package com.youngmonkeys.common.repo;

import com.tvd12.ezydata.mongodb.EzyMongoRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import com.youngmonkeys.common.entity.ChatUser;

@EzyRepository("userRepo")
public interface ChatUserRepo extends EzyMongoRepository<Long, ChatUser> {
}
