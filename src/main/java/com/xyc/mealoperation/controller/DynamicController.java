package com.xyc.mealoperation.controller;

import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.ao.GetDynamicOutAO;
import com.xyc.mealoperation.entity.meal.Dynamic;
import com.xyc.mealoperation.service.DynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author xiongyancong
 * @createTime 2019/12/20 13:47
 * @Description
 **/
@Controller
@Slf4j
@CrossOrigin
@RequestMapping("/dynamic")
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;

    @RequestMapping("/dynamicOut")
    @ResponseBody
    public ResultBean<List<Dynamic>> dynamicOut(){
        List<Dynamic> dynamicList =
                dynamicService.getAllDynamic();
        log.info(dynamicList.toString());
        if (dynamicList != null){
            return ResultBean.success(dynamicList);
        }else {
            return ResultBean.fail(404,"未找到数据!");
        }
    }

    @RequestMapping("/dynamicOutPage")
    @ResponseBody
    public ResultBean<List<Dynamic>> dynamicOutLimit(GetDynamicOutAO getDynamicOutAO){
        List<Dynamic> dynamicList =
                dynamicService.getDynamicByPage(getDynamicOutAO);
        return ResultBean.success(dynamicList);
    }


}
