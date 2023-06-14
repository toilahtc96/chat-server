package com.youngmonkeys.common.service.impl;

import com.tvd12.ezydata.database.repository.EzyMaxIdRepository;
import com.tvd12.ezyfox.bean.annotation.EzyAutoBind;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.youngmonkeys.common.service.ChatMaxIdService;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class ChatMaxIdServiceImpl implements ChatMaxIdService {

    @EzyAutoBind
    private EzyMaxIdRepository ezyMaxIdRepository;

    @Override
    public void loadAll() {

    }

    @Override
    public Long incrementAndGet(String s) {
        return ezyMaxIdRepository.incrementAndGet(s);
    }

    @Override
    public Long incrementAndGet(String s, int i) {
        return ezyMaxIdRepository.incrementAndGet(s,i);
    }
}
