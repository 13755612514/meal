package com.xyc.mealoperation.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xyc.mealoperation.entity.ao.GetDynamicOutAO;
import com.xyc.mealoperation.entity.meal.Dynamic;
import com.xyc.mealoperation.entity.meal.Relation;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.mapper.DynamicMapper;
import com.xyc.mealoperation.mapper.RelationMapper;
import com.xyc.mealoperation.mapper.UserMapper;
import com.xyc.mealoperation.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    /**
     * 获取一年内所有的动态
     * @return
     */
    public List<Dynamic> getAllDynamic(){
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime lastYear = timeNow.minusYears(1);
        //获取过去一年数据显示
        List<Dynamic> dynamicList =
                dynamicMapper.selectList(new QueryWrapper<Dynamic>());
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
    public String addDynamic(MultipartFile video, Dynamic dynamic){
        //获取操作系统
        String OSName = System.getProperty("os.name");
        //根据操作系统选择路径
        String filePath = OSName.toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH : LINUX_PROFILES_PATH;
        //文件不为空时才写入
        if (!video.isEmpty()){
            //获取当前文件名
            String videoPathName = filePath + System.currentTimeMillis() + video.getOriginalFilename();;
            //磁盘保存
            BufferedOutputStream out = null;
            try {
                File file = new File(videoPathName);
                if (!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }else {
                    if (!file.exists()){
                        file.createNewFile();
                    }
                }
                out = new BufferedOutputStream(new FileOutputStream(videoPathName));
                //写入文件
                out.write(videoPathName.getBytes());
                out.flush();
            }catch (Exception e){
                e.printStackTrace();
                return "上传失败";
            }finally {
                if (out != null){
                    try {
                        out.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Timestamp timeNow = new Timestamp(System.currentTimeMillis());
            //路径存库
            User user = userMapper.selectById(dynamic.getSendId());
            dynamic.setHeadFile(user.getHeader());
            dynamic.setContent(videoPathName);
            dynamic.setCreateTime(timeNow);
            dynamicMapper.insert(dynamic);
            return "上传成功。";
        }
        return "上传成功。";
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
