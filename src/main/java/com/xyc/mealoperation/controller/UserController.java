package com.xyc.mealoperation.controller;

import com.xyc.mealoperation.constant.ErrorEnum;
import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.ao.UserAO;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.service.UserService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping("/getUser")
    @ResponseBody
    public ResultBean<?> getUser(@RequestParam("objectId") Long objectId) {
        return userService.getUser(objectId);
    }
    /**
     * 用户登录
     * @param
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<User> userLogin(@RequestParam("email") String email,
                                      @RequestParam("password")String password){
        User user =
                userService.userLogin(email,password);
        if (user != null){
            return ResultBean.success(user);
        }else {
            return ResultBean.fail(430,"账号密码错误");
        }
    }

    /**
     * 更换头像
     * @param headFile
     * @param email
     * @return
     */
    @RequestMapping(value = "/updateUserHeader",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<?> updateUserHeader(@RequestParam("headFile") String headFile,@RequestParam("email") String email){
        return userService.updateHeader(headFile,email);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean<?> registerUser(UserAO user){
        log.info(user.toString());
        return userService.register(user);
    }

    /**
     * 修改资料
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateInfo")
    @ResponseBody
    public ResultBean<?> updateInfo(User user){
        String status = userService.updateUserInfo(user);
        if ("1".equals(status)) {
            return ResultBean.success(0,"更新成功");
        }else {
            return ResultBean.fail(ErrorEnum.UNKNOWN_EXCEPTION);
        }
    }

    /**
     * 修改密码
     * @param objectId
     * @param password
     * @return
     */
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public ResultBean<?> updatePassword(@RequestParam("objectId")Long objectId, @RequestParam("password") String password) {
        int status = userService.updatePassword(objectId,password);
        if (status == 1) {
            return ResultBean.success(0,"更新成功");
        }else {
            return ResultBean.fail(ErrorEnum.UNKNOWN_EXCEPTION);
        }
    }

    /**
     * 关注
     * @param userId
     * @param attentionId
     * @return
     */
    @RequestMapping(value = "/addRelation")
    @ResponseBody
    public ResultBean<?> addRelation(@RequestParam("userId") Long userId,
                                  @RequestParam("attentionId") Long attentionId) {
        return userService.addRelation(userId,attentionId);
    }

    /**
     * 我的关注列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "/relationOut")
    @ResponseBody
    public ResultBean<?> relationOut(@RequestParam("userId") Long userId) {
        return userService.relationOut(userId);
    }

    /**
     * 取消关注
     * @param objectId
     * @return
     */
    @RequestMapping(value = "/deleteRelation")
    @ResponseBody
    public ResultBean<?> deleteRelation(@RequestParam("objectId") Long objectId) {
        return userService.deleteRelation(objectId);
    }

    /**
     * 我的收藏夹
     * @param userId
     * @return
     */
    @RequestMapping(value = "/myFavoriteDynainc")
    @ResponseBody
    public ResultBean<?> myFavoriteDynainc(@RequestParam("userId") Long userId) {
        return userService.myFavoriteDynainc(userId);
    }

    /**
     *
     * @param userId
     * @param dyId
     * @return
     */
    @RequestMapping(value = "/deleteFavDy")
    @ResponseBody
    public ResultBean<?> deleteFavDy(@RequestParam("userId") Long userId,
                                     @RequestParam("dyId") Long dyId) {
        return userService.deleteFavDy(userId,dyId);
    }

    /**
     * 添加收藏
     * @param userId
     * @param dyId
     * @return
     */
    @RequestMapping(value = "/addFav")
    @ResponseBody
    public ResultBean<?> addFav(@RequestParam("userId") Long userId,
                             @RequestParam("dyId") Long dyId) {
        return userService.addFav(userId,dyId);
    }

}
