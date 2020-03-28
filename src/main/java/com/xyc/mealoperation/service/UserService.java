package com.xyc.mealoperation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xyc.mealoperation.constant.ErrorEnum;
import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.ao.UserAO;
import com.xyc.mealoperation.entity.meal.Dynamic;
import com.xyc.mealoperation.entity.meal.Favorite;
import com.xyc.mealoperation.entity.meal.Relation;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.mapper.DynamicMapper;
import com.xyc.mealoperation.mapper.FavoriteMapper;
import com.xyc.mealoperation.mapper.RelationMapper;
import com.xyc.mealoperation.mapper.UserMapper;
import com.xyc.mealoperation.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author xiongyancong
 * @createTime 2019/12/17 11:20
 * @Description
 **/
@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private DynamicMapper dynamicMapper;

    private final String WINDOWS_PROFILES_PATH = "C:/meal/profiles/header";
    private final String LINUX_PROFILES_PATH = "/root/meal/profiles/header";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 根据用户email和密码查询用户来登录
     * @param email
     * @param password
     * @return
     */
    public User userLogin(String email, String password){
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("EMAIL",email)
                .eq("PASSWORD",EncryptUtil.getInstance().MD5(password)));
    }

    /**
     * 根据用户上传的头像修改用户头像以及储存用户上传文件
     * @param headerFile
     * @param email
     * @return
     */
    public ResultBean<?> updateHeader(String headerFile,String email){
        userMapper.updateHeaderByEmail(email,headerFile);
        return ResultBean.success(0,"更换头像成功");
    }

    /**
     * 用户注册
     * @param userAO
     * @return
     */
    public ResultBean register(UserAO userAO){
        if (userMapper.findByEmail(userAO.getEmail()) != null){
            return ResultBean.fail(ErrorEnum.DATA_EXIST);
        }
        User user = new User();
        String password = EncryptUtil.getInstance().MD5(userAO.getPassword());
        String createTime = LocalDate.now().format(formatter);
        user.setEmail(userAO.getEmail());
        user.setCreatDt(createTime);
        user.setPassword(password);
        user.setUserName(userAO.getUsername());
        int status = userMapper.saveInfo(user);
        if (status == 1){
            return ResultBean.success(0,"注册成功");
        }else {
            return ResultBean.fail(ErrorEnum.UNKNOWN_EXCEPTION);
        }

    }

    /**
     * 修改用户个人信息
     * @param user
     * @return
     */
    public String updateUserInfo(User user){
        int status = userMapper.updateById(user);
        log.info("返回：{}",status);
        return status + "";
    }

    /**
     * 修改密码
     * @param objectId
     * @param password
     * @return
     */
    public int updatePassword(Long objectId, String password) {
        User user = new User();
        user.setPassword(EncryptUtil.getInstance().MD5(password));
        user.setObjectId(objectId);
        int status = userMapper.updateById(user);
        return status;
    }

    /**
     * 添加关注
     * @param userId
     * @param attentionId
     * @return
     */
    public ResultBean addRelation (Long userId, Long attentionId) {
        Relation relation = relationMapper.selectOne(new QueryWrapper<Relation>()
                .eq("USER_ID",userId)
                .eq("ATTENTION_ID",attentionId));
        if (relation != null) {
            return ResultBean.fail(ErrorEnum.DATA_EXIST);
        }
        String header = userMapper.selectById(attentionId).getHeader();
        LocalDate timeNow = LocalDate.now();
        Relation relation2 = new Relation();
        relation2.setUserId(userId);
        relation2.setAttentionId(attentionId);
        relation2.setCreateDt(timeNow.format(formatter));
        relation2.setFollowedHeader(header);
        int status = relationMapper.insert(relation2);
        return ResultBean.success(0,"关注成功");
    }

    /**
     * 取消关注
     * @param userId
     * @param attentionId
     * @return
     */
    public ResultBean deleteRelation(Long userId, Long attentionId){
        int status = relationMapper.delete(new QueryWrapper<Relation>().eq("USER_ID",userId)
                                    .eq("ATTENTION_ID",attentionId));
        if (status == 1) {
            return ResultBean.success(0,"取消成功");
        }
        return ResultBean.fail(ErrorEnum.DATA_NOT_FOUND);
    }

    /**
     * 我的关注列表
     * @param userId
     * @return
     */
    public ResultBean relationOut(Long userId) {
        List<Relation> relationList = relationMapper.selectList(new QueryWrapper<Relation>()
                .eq("USER_ID",userId));
        return ResultBean.success(relationList);
    }

    /**
     * 我的收藏夹
     * @param userId
     * @return
     */
    public ResultBean myFavoriteDynainc (Long userId) {
        List<Favorite> favoriteList =
                favoriteMapper.selectList(new QueryWrapper<Favorite>()
                        .eq("USER_ID",userId));
        if (!CollectionUtils.isEmpty(favoriteList)) {
            List<Long> dynamicIdList =
                    favoriteList.stream().map(Favorite::getDyId).distinct().collect(Collectors.toList());
            List<Dynamic> dynamicList =
                    dynamicMapper.selectList(new QueryWrapper<Dynamic>()
                            .in("OBJECT_ID",dynamicIdList));
            dynamicList.forEach(dynamic -> {
                dynamic.setType(1);
            });
            return ResultBean.success(dynamicList);
        }
        return ResultBean.fail(ErrorEnum.DATA_NOT_FOUND);
    }

    /**
     * 取消收藏
     * @param userId
     * @param dyId
     * @return
     */
    public ResultBean deleteFavDy(Long userId, Long dyId){
        Favorite favorite = favoriteMapper.selectOne(new QueryWrapper<Favorite>()
                .eq("USER_ID",userId)
                .eq("DY_ID",dyId));
        if (favorite != null) {
            int status = favoriteMapper.deleteById(favorite.getObjectId());
            if (status == 1) {
                return ResultBean.success(0,"取消收藏成功");
            }
        }

        return ResultBean.fail(ErrorEnum.DATA_NOT_FOUND);
    }

    /**
     * 添加收藏
     * @param userId
     * @param dyId
     * @return
     */
    public ResultBean addFav(Long userId, Long dyId) {
        Favorite favoriteOld = favoriteMapper.selectOne(new QueryWrapper<Favorite>()
                .eq("USER_ID",userId)
                .eq("DY_ID",dyId));
        if (favoriteOld != null) {
            return ResultBean.fail(ErrorEnum.DATA_EXIST);
        }
        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setDyId(dyId);
        favorite.setCreatTime(timeNow);
        favorite.setUpdateTime(timeNow);
        int status = favoriteMapper.insert(favorite);
        if (status == 1) {
            return ResultBean.success(0,"收藏成功");
        }
        return ResultBean.fail(ErrorEnum.UNKNOWN_EXCEPTION);
    }

    public ResultBean<?> getUser(Long  id) {
        User user =
                userMapper.selectById(id);
        return ResultBean.success(user);
    }
}
