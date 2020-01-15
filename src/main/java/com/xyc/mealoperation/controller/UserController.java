package com.xyc.mealoperation.controller;

import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.ao.UserLoginAO;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiongyancong
 * @createTime 2019/12/17 10:54
 * @Description
 **/
@Controller
@Slf4j
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<User> userLogin(@RequestBody UserLoginAO userLoginAO){
        User user =
                userService.userLogin(userLoginAO.getEmail(),userLoginAO.getPassword());
        if (user != null){
            return ResultBean.success(user);
        }else {
            return ResultBean.fail(430,"账号密码错误");
        }
    }

    @RequestMapping(value = "/updateUserHeader",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<String> updateUserHeader(@RequestParam("headFile") MultipartFile headFile,@RequestParam("email") String email){
        String message = userService.updateHeader(headFile,email);
        return ResultBean.success(message);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean registerUser(@RequestBody User user){
        log.info(user.toString());
        Map<Integer,String> map = new HashMap<>();
        return userService.register(user);
    }
    @RequestMapping(value = "/updateInfo")
    @ResponseBody
    public ResultBean updateInfo(User user){
        String status = userService.updateUserInfo(user);
        return ResultBean.success(200,"更新成功");
    }
}
