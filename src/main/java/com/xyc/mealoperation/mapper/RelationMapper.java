package com.xyc.mealoperation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xyc.mealoperation.entity.meal.Relation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author xiongyancong
 * @createTime 2020/1/15 16:09
 * @Description
 **/
@Repository
@Mapper
public interface RelationMapper extends BaseMapper<Relation> {
}
