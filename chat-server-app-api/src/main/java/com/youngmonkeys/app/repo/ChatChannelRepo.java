package com.youngmonkeys.app.repo;

import com.tvd12.ezydata.mongodb.EzyMongoRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import com.youngmonkeys.app.entity.ChatChannel;

@EzyRepository
public interface ChatChannelRepo extends EzyMongoRepository<Long, ChatChannel> {
}
