package com.xyc.mealoperation.entity.ao;

import lombok.Data;

/**
 * @Author xiongyancong
 * @createTime 2019/12/20 17:20
 * @Description
 **/
@Data
public class GetDynamicOutAO {
    //每页条数默认为20条数据
    private int number = 20;
    //页数
    private int pageCount = 1;
    //类型
    private int type;
}
