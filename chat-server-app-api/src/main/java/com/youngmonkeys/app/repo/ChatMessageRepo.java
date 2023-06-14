package com.youngmonkeys.app.repo;

import com.tvd12.ezydata.mongodb.EzyMongoRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import com.youngmonkeys.app.entity.ChatMessage;

import java.util.List;

@EzyRepository
public interface ChatMessageRepo extends EzyMongoRepository<Long, ChatMessage> {
    @EzyQuery("{'channelId': ?0}")
    List<ChatMessage> findByChannelId(long channelId);
}
