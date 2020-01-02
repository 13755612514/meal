package com.xyc.mealoperation.service;

import com.xyc.mealoperation.entity.ao.GetDynamicOutAO;
import com.xyc.mealoperation.entity.meal.Dynamic;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.mapper.DynamicMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author xiongyancong
 * @createTime 2019/12/20 13:49
 * @Description
 **/
@Service
@Slf4j
public class DynamicService {
    private final String WINDOWS_PROFILES_PATH = "C:/meal/profiles/";
    private final String LINUX_PROFILES_PATH = "/root/meal/profiles/";

    @Autowired
    private DynamicMapper dynamicMapper;

    /**
     * 获取一年内所有的动态
     * @return
     */
    public List<Dynamic> getAllDynamic(){
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime lastYear = timeNow.minusYears(1);
        //获取过去一年数据显示
        List<Dynamic> dynamicList =
                dynamicMapper.getAllByTimeBetween(Timestamp.valueOf(lastYear),Timestamp.valueOf(timeNow));
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

    public String addDynamic(MultipartFile video, int sendId){
//        //获取操作系统
//        String OSName = System.getProperty("os.name");
//        //根据操作系统选择路径
//        String filePath = OSName.toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH : LINUX_PROFILES_PATH;
//
//        if (!video.isEmpty()){
//            //获取当前用户
//            User currentUser = userMapper.findByEmail(email);
//            //获取当前用户头像名
//            String oldHeaderName = currentUser.getHeader();
//            String newHeaderName = "";
//            //如若存在头像名则使用该头像名否则新建一个名称
//            if(oldHeaderName != null && (filePath.startsWith("C:") && oldHeaderName.startsWith("C:")) || (filePath.startsWith("/root") && oldHeaderName.startsWith("/root"))){
//                newHeaderName = oldHeaderName;
//            }else {
//                newHeaderName = filePath + System.currentTimeMillis() + headerFile.getOriginalFilename();
//            }
//            //磁盘保存
//            BufferedOutputStream out = null;
//            try {
//                File file = new File(newHeaderName);
//                if (!file.getParentFile().exists()){
//                    file.getParentFile().mkdirs();
//                    file.createNewFile();
//                }else {
//                    if (!file.exists()){
//                        file.createNewFile();
//                    }
//                }
//                out = new BufferedOutputStream(new FileOutputStream(newHeaderName));
//                //写入文件
//                out.write(headerFile.getBytes());
//                out.flush();
//            }catch (Exception e){
//                e.printStackTrace();
//                return "设置头像失败";
//            }finally {
//                if (out != null){
//                    try {
//                        out.close();
//                    }catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            //路径存库
//            currentUser.setHeader(newHeaderName);
//            userMapper.updateHeaderByEmail(email,newHeaderName);
//            return "设置头像成功";
//        }
        return "设置头像失败,文件错误";
    }
}
