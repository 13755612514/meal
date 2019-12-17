package com.xyc.mealoperation.service;

import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xiongyancong
 * @createTime 2019/12/17 11:20
 * @Description
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户email和密码查询用户
     * @param email
     * @param password
     * @return
     */
    public User userLogin(String email,String password){
        return userMapper.findByEmailAndPassword(email,password);
    }
}
