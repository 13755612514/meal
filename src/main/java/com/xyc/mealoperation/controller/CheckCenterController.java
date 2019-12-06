package com.xyc.mealoperation.controller;

import com.xyc.mealoperation.service.WebSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @Author xiongyancong
 * @createTime 2019/12/6 14:48
 * @Description
 **/
public class CheckCenterController {
    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

//    //推送数据接口
//    @ResponseBody
//    @RequestMapping("/socket/push/{cid}")
//    public ApiReturnObject pushToWeb(@PathVariable String cid, String message) {
//        try {
//            WebSocketServer.sendInfo(message, cid);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ApiReturnUtil.error(cid + "#" + e.getMessage());
//        }
//        return ApiReturnUtil.success(cid);
//    }
}
