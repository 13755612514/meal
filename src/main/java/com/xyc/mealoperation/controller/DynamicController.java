package com.xyc.mealoperation.controller;

import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.ao.GetDynamicOutAO;
import com.xyc.mealoperation.entity.meal.Dynamic;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.service.DynamicService;
import com.xyc.mealoperation.util.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResultBean dynamicOut(@RequestParam("objectId") Long objectId){
        List<Dynamic> dynamicList =
                dynamicService.getAllDynamic(objectId);
        log.info(dynamicList.toString());
        return ResultBean.success(dynamicList);
    }

    @RequestMapping("/dynamicOutPage")
    @ResponseBody
    public ResultBean<List<Dynamic>> dynamicOutLimit(@RequestBody GetDynamicOutAO getDynamicOutAO){
        List<Dynamic> dynamicList =
                dynamicService.getDynamicByPage(getDynamicOutAO);
        return ResultBean.success(dynamicList);
    }

    /**
     * 添加动态
     * @param video
     * @param sendId
     * @param title
     * @param description
     * @return
     */
    @RequestMapping("/saveDynamic")
    @ResponseBody
    public ResultBean saveDynamic(@RequestParam("video") String video,
                                  @RequestParam("sendId") Long sendId,
                                  @RequestParam("title") String title,
                                  @RequestParam("description") String description) {
        Dynamic dynamic = new Dynamic();
        dynamic.setDescription(description);
        dynamic.setTitle(title);
        dynamic.setSendId(sendId);
        return dynamicService.addDynamic(video,dynamic);
    }

    /**
     * 添加动态
     * @param video
     * @param sendId
     * @param title
     * @param description
     * @return
     */
    @RequestMapping(value = "/saveDynamicFile", headers="content-type=multipart/form-data")
    @ResponseBody
    public ResultBean saveDynamic(@RequestParam("video") MultipartFile video,
                                  @RequestParam("sendId") Long sendId,
                                  @RequestParam("title") String title,
                                  @RequestParam("description") String description) {
        Dynamic dynamic = new Dynamic();
        dynamic.setDescription(description);
        dynamic.setTitle(title);
        dynamic.setSendId(sendId);
        return dynamicService.addDynamic(video,dynamic);
    }

    /**
     * 输出我关注的
     * @param objectId
     * @return
     */
    @RequestMapping("/myAttentionDynamic")
    @ResponseBody
    public ResultBean myAttentionDynamic(@RequestParam("objectId") Long objectId) {
        User user = new User();
        user.setObjectId(objectId);
        List<Dynamic> dynamicList =
                dynamicService.findByAttentionUser(user);
        return ResultBean.success(dynamicList);
    }


    /**
     * 我发表的
     * @param objectId
     * @return
     */
    @RequestMapping("/myDynamic")
    @ResponseBody
    public ResultBean myDynamic(@RequestParam("objectId") Long objectId) {
        User user = new User(objectId);
        List<Dynamic> dynamicList =
                dynamicService.findByUserId(user);
        return ResultBean.success(dynamicList);
    }
}
