package com.xyc.mealoperation.service;

import com.xyc.mealoperation.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void userLogin() {
        System.out.println(userService.userLogin("1411248803@qq.com","13755612514"));
    }

    @Test
    public void updateHeader() {
        String content = Base64Util.encryptToBase64("D:\\Base64\\175e467b7f089bc86c97ee04f15c4802.mp4");
        System.out.println(content);
    }

    @Test
    public void register() {

    }

    @Test
    public void updateUserInfo() {
    }
}