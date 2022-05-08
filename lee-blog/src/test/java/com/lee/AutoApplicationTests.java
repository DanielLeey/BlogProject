package com.lee;

import com.lee.dao.ResourceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lee.domain.entity.Resource;
import java.util.List;

@SpringBootTest
public class AutoApplicationTests {

    @Autowired
    ResourceMapper resourceMapper;

    @Test
    void test() {
        List<Resource> resources = resourceMapper.listByUserId(1L);
        for (Resource resource : resources) {
            System.out.println(resource.toString());
        }
    }
}
