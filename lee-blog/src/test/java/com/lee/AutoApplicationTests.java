package com.lee;

import com.lee.dao.ResourceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lee.domain.entity.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class AutoApplicationTests {

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        List<Resource> resources = resourceMapper.listByUserId(1L);
        for (Resource resource : resources) {
            System.out.println(resource.toString());
        }
    }

    @Test
    public void getEncodePassword() {
        String password = "123456";
        String encodePassword = passwordEncoder.encode(password);
        System.out.println(encodePassword);
    }

    @Test
    public void deCodePassword() {
        String password = "$2a$10$UQx79DHOWVRFhdT/8h/Zz.gJlbTpdzU6KeO.awkdJv/frd10.bG3q";
        final boolean matches = passwordEncoder.matches("123456", password);
        System.out.println(matches);
    }
}
