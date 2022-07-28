package com.lee;

import com.lee.dao.ResourceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lee.domain.entity.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
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
        String password = "$2a$10$xc8A44uiO6qKKb8FGf36uusbvsF7hEkhUy/YSnekIGQ1LMQrST3Cm";

        final boolean matches = passwordEncoder.matches("123456", password);
        System.out.println(matches);
    }

    @Test
    public void renameDir() {

    }

    public static void changeFileName(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        changeFileName(file.getAbsolutePath());
                    } else {
                        //是文件，判断是否需要重命名
                        File parentPath = new File("");//文件所在父级路径
                        parentPath = file.getParentFile();
                        String fileName = file.getName(); //旧名
                        // 获取文件名前半部分（车牌号）
                        String firstName = fileName.substring(0, fileName.indexOf("("));
                        // 获取文件名前后部分（检测时间）
                        String sedName = fileName.substring(fileName.indexOf("(") + 1, fileName.indexOf(")"));
                        //重新定义名字
                        String newName = sedName + "(" + firstName + ")" + ".docx";
                        File newFile = new File(parentPath + "/", newName);
                        System.out.println(fileName + "-->" + newName);
                        //进行重命名操作
                        if (file.renameTo(newFile)) {
                            System.out.println(file.getName() + "重命名成功---");
                        } else {
                            System.out.println(file.getName() + "重命名失败+++");
                        }
                    }
                }
            }
        }
    }
}
