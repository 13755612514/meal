package com.xyc.mealoperation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.ao.GetDynamicOutAO;
import com.xyc.mealoperation.entity.meal.Dynamic;
import com.xyc.mealoperation.entity.meal.Favorite;
import com.xyc.mealoperation.entity.meal.Relation;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.mapper.DynamicMapper;
import com.xyc.mealoperation.mapper.FavoriteMapper;
import com.xyc.mealoperation.mapper.RelationMapper;
import com.xyc.mealoperation.mapper.UserMapper;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xiongyancong
 * @createTime 2019/12/20 13:49
 * @Description
 **/
@Service
@Slf4j
public class DynamicService {
    private final String WINDOWS_PROFILES_PATH = "C:/meal/profiles/video/";
    private final String LINUX_PROFILES_PATH = "/root/meal/profiles/video/";

    @Autowired
    private DynamicMapper dynamicMapper;

    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    /**
     * 获取一年内所有的动态
     * @return
     */
    public List<Dynamic> getAllDynamic(long userId){
        //获取所有数据
        List<Dynamic> dynamicList =
                dynamicMapper.selectList(new QueryWrapper<Dynamic>());
        //判断该用户是否收藏了动态
        List<Favorite> favoriteList =
                favoriteMapper.selectList(new QueryWrapper<Favorite>()
                        .eq("USER_ID",userId));
        List<Long> dynamicIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(favoriteList)) {
            dynamicIdList =
                    favoriteList.stream().map(Favorite::getDyId).distinct().collect(Collectors.toList());
        }
        List<Long> finalDynamicIdList = dynamicIdList;
        dynamicList.forEach(dynamic -> {
            if (!CollectionUtils.isEmpty(finalDynamicIdList) && finalDynamicIdList.contains(dynamic.getObjectId())) {
                dynamic.setType(1);
            }else
                dynamic.setType(0);
        });
        return dynamicList;
    }

    /**
     * 分页查询
     * @param getDynamicOutAO
     * @return
     */
    public List<Dynamic> getDynamicByPage(GetDynamicOutAO getDynamicOutAO){
        List<Dynamic> dynamicList =
                dynamicMapper.getByPage((getDynamicOutAO.getPageCount()-1)*20,getDynamicOutAO.getNumber());
        return dynamicList;
    }

    /**
     * 分页+类型查询
     * @param getDynamicOutAO
     * @return
     */
    public List<Dynamic> getDynamicByPageAndType(GetDynamicOutAO getDynamicOutAO){
        List<Dynamic> dynamicList =
                dynamicMapper.getByPageAndType((getDynamicOutAO.getPageCount()-1)*20,getDynamicOutAO.getNumber(),1);
        return  dynamicList;
    }

    /**
     * 添加动态
     * @param video
     * @param dynamic
     * @return
     */
    public ResultBean<?> addDynamic(String video, Dynamic dynamic){
            Timestamp timeNow = new Timestamp(System.currentTimeMillis());
            //路径存库
            User user = userMapper.selectById(dynamic.getSendId());
            dynamic.setHeadFile(user.getHeader());
            dynamic.setContent(video);
            dynamic.setCreateTime(timeNow);
            dynamicMapper.insert(dynamic);
            return ResultBean.success(0,"添加成功");
    }

    /**
     * 根据用户id返回该用户关注的用户发表的动态
     * @param user
     * @return
     */
    public List<Dynamic> findByAttentionUser(User user){
        if (user.getObjectId() != null){
            //查询该用户关注列表
            List<Relation> relationList =
                    relationMapper.selectList(new QueryWrapper<Relation>()
                            .eq("USER_ID",user.getObjectId()));
            if (!CollectionUtils.isEmpty(relationList)) {
                List<Long> attentionIdList =
                        relationList.stream().map(Relation::getAttentionId).distinct().collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(attentionIdList)) {
                    List<Dynamic> dynamicList = dynamicMapper.selectList(new QueryWrapper<Dynamic>()
                            .in("SEND_ID",attentionIdList));
                    return dynamicList;
                }
            }
        }
        return null;
    }

    /**
     * 查看我发表的动态
     * @param user
     * @return
     */
    public List<Dynamic> findByUserId(User user){
        if (user.getObjectId() != null){
            List<Dynamic> dynamicList = dynamicMapper.selectList(new QueryWrapper<Dynamic>()
                            .eq("SEND_ID",user.getObjectId()));
            return dynamicList;
        }
        return null;
    }
}
