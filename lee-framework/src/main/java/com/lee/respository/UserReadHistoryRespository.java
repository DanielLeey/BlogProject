package com.lee.respository;

import com.lee.domain.entity.UserReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author: liyansong
 * @Date: 2022/7/28 20:09
 * @Version: 1.0
 * 用户浏览历史Repository
 */
public interface UserReadHistoryRespository extends MongoRepository<UserReadHistory,String> {
    /**
     * 根据用户id按时间倒序获取浏览记录
     * @param memberId 会员id
     */
    List<UserReadHistory> findByUserIdOrderByCreateTimeDesc(Long userId);
}
