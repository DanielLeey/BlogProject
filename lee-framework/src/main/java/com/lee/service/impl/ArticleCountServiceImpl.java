package com.lee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.dao.ArticleCountMapper;
import com.lee.domain.entity.ArticleCount;
import com.lee.service.ArticleCountService;
import org.springframework.stereotype.Service;

/**
 * @Author: lee
 * @Date: 2022/7/10 11:36
 * @Version: 1.0
 */
@Service
public class ArticleCountServiceImpl extends ServiceImpl<ArticleCountMapper, ArticleCount> implements ArticleCountService {
}
