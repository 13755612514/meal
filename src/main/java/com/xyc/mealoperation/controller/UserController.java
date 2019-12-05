package com.xyc.mealoperation.controller;

import com.xyc.mealoperation.entity.Mealuser;
import com.xyc.mealoperation.service.MealUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author xiongyancong
 * @createTime 2019/12/4 15:18
 * @Description
 **/
@Controller
@RequestMapping("/meal")
public class UserController {
    @Autowired
    MealUserService mealUserService;

    @ResponseBody
    @RequestMapping("/alluser")
    public List<Mealuser> mealuserOut(){
        return mealUserService.findAllUser();
    }
}
