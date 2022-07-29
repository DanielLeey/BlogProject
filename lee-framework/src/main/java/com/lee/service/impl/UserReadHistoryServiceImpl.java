package com.lee.service.impl;

import com.lee.domain.entity.UserReadHistory;
import com.lee.respository.UserReadHistoryRespository;
import com.lee.service.UserReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyansong
 * @Date: 2022/7/29 17:17
 * @Version: 1.0
 */
@Service
public class UserReadHistoryServiceImpl implements UserReadHistoryService {
    @Autowired
    UserReadHistoryRespository userReadHistoryRespository;

    @Override
    public int create(UserReadHistory userReadHistory) {
        userReadHistory.setId(null);
        userReadHistory.setCreateTime(new Date());
        userReadHistoryRespository.save(userReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<UserReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            UserReadHistory userReadHistory = new UserReadHistory();
            userReadHistory.setId(id);
            deleteList.add(userReadHistory);
        }
        userReadHistoryRespository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<UserReadHistory> list(Long userId) {
        return userReadHistoryRespository.findByUserIdOrderByCreateTimeDesc(userId);
    }
}
