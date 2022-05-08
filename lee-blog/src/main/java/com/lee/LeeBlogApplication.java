package com.lee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: admin
 * @Date: 2022/4/2 11:16
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.lee.dao")
@EnableScheduling
public class LeeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeeBlogApplication.class,args);
    }
}
