package com.xyc.mealoperation.mapper;

import com.xyc.mealoperation.entity.meal.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author xiongyancong
 * @createTime 2019/12/20 11:40
 * @Description
 **/
@Repository
@Mapper
public interface CommentMapper {
    List<Comment> findByDyId(@Param("dyId") int dyId);
}
