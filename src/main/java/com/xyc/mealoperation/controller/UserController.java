package com.xyc.mealoperation.controller;

import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.ao.UserLoginAO;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author xiongyancong
 * @createTime 2019/12/17 10:54
 * @Description
 **/
@RestController
@RequestMapping("/meal/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<User> userLogin(@RequestBody UserLoginAO userLoginAO){
        User user =
                userService.userLogin(userLoginAO.getEmail(),userLoginAO.getPassword());
        if (user != null){
            return ResultBean.success(user);
        }else {
            return ResultBean.fail(430,"登录错误");
        }
    }
}
