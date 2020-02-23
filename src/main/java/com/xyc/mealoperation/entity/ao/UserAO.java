package com.xyc.mealoperation.entity.ao;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author xiongyancong
 * @createTime 2019/12/17 11:46
 * @Description
 **/
@Data
public class UserAO {

    private MultipartFile headFile;
    private String username;
    private String email;
    private String password;
}
