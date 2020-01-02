package com.xyc.mealoperation.service;

import com.xyc.mealoperation.constant.ErrorEnum;
import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.meal.User;
import com.xyc.mealoperation.mapper.UserMapper;
import com.xyc.mealoperation.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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

    private final String WINDOWS_PROFILES_PATH = "C:/meal/profiles/";
    private final String LINUX_PROFILES_PATH = "/root/meal/profiles/";

    /**
     * 根据用户email和密码查询用户来登录
     * @param email
     * @param password
     * @return
     */
    public User userLogin(String email,String password){
        password = EncryptUtil.getInstance().MD5(password);
        return userMapper.findByEmailAndPassword(email,password);
    }

    /**
     * 根据用户上传的头像修改用户头像以及储存用户上传文件
     * @param headerFile
     * @param email
     * @return
     */
    public String updateHeader(MultipartFile headerFile,String email){
        //获取操作系统
        String OSName = System.getProperty("os.name");
        //根据操作系统选择路径
        String filePath = OSName.toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH : LINUX_PROFILES_PATH;

        if (!headerFile.isEmpty()){
            //获取当前用户
            User currentUser = userMapper.findByEmail(email);
            //获取当前用户头像名
            String oldHeaderName = currentUser.getHeader();
            String newHeaderName = "";
            //如若存在头像名则使用该头像名否则新建一个名称
            if(oldHeaderName != null && (filePath.startsWith("C:") && oldHeaderName.startsWith("C:")) || (filePath.startsWith("/root") && oldHeaderName.startsWith("/root"))){
                newHeaderName = oldHeaderName;
            }else {
                newHeaderName = filePath + System.currentTimeMillis() + headerFile.getOriginalFilename();
            }
            //磁盘保存
            BufferedOutputStream out = null;
            try {
                File file = new File(newHeaderName);
                if (!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }else {
                    if (!file.exists()){
                        file.createNewFile();
                    }
                }
                out = new BufferedOutputStream(new FileOutputStream(newHeaderName));
                //写入文件
                out.write(headerFile.getBytes());
                out.flush();
            }catch (Exception e){
                e.printStackTrace();
                return "设置头像失败";
            }finally {
                if (out != null){
                    try {
                        out.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //路径存库
            currentUser.setHeader(newHeaderName);
            userMapper.updateHeaderByEmail(email,newHeaderName);
            return "设置头像成功";
        }
        return "设置头像失败,文件错误";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public ResultBean register(User user){
        if (userMapper.findByEmail(user.getEmail()) != null){
            return ResultBean.fail(ErrorEnum.DATA_EXIST);
        }
        String password = EncryptUtil.getInstance().MD5(user.getPassword());
        user.setPassword(password);
        int status = userMapper.saveInfo(user);
        if (status == 1){
            return ResultBean.success(200,"注册成功");
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
        int status = userMapper.updateInfo(user);
        log.info("返回：{}",status);
        return status + "";
    }
}
