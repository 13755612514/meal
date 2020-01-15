package com.xyc.mealoperation.service;

import com.xyc.mealoperation.entity.meal.Comment;
import com.xyc.mealoperation.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xiongyancong
 * @createTime 2020/1/2 14:22
 * @Description
 **/
@Service
@Slf4j
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 根据动态id写入数据
     * @param dyId
     * @return
     */
    public List<Comment> getAllByDyId(int dyId){
        List<Comment> commentList =
                commentMapper.findByDyId(dyId);
        return commentList;
    }
}
