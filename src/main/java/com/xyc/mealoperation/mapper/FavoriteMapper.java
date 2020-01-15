package com.xyc.mealoperation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xyc.mealoperation.entity.meal.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author xiongyancong
 * @createTime 2020/1/15 17:50
 * @Description
 **/
@Repository
@Mapper
public interface FavoriteMapper extends BaseMapper<Favorite> {
}
