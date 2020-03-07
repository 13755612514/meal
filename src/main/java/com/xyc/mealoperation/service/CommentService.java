package com.xyc.mealoperation.service;

import com.xyc.mealoperation.constant.ErrorEnum;
import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.meal.Comment;
import com.xyc.mealoperation.entity.meal.Commentforcom;
import com.xyc.mealoperation.mapper.CommentMapper;
import com.xyc.mealoperation.mapper.CommentforcomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    @Autowired
    CommentforcomMapper commentforcomMapper;

    /**
     * 根据动态id输出评论
     * @param dyId
     * @return
     */
    public ResultBean<?> getAllByDyId(Long dyId){
        List<Comment> commentList =
                commentMapper.findByDyId(dyId);
        return ResultBean.success(commentList);
    }

    /**
     * 添加评论(对动态)
     * @param comment
     * @return
     */
    public ResultBean<?> addComment(Comment comment) {
        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
        comment.setCreateTime(timeNow);
        int status = commentMapper.insert(comment);
        if (status == 1) {
            return ResultBean.success(200,"评论(对动态)成功");
        }
        return ResultBean.fail(ErrorEnum.UNKNOWN_EXCEPTION);
    }

    /**
     * 添加评论(对评论)
     * @param commentforcom
     * @return
     */
    public ResultBean<?> addCommentForCom(Commentforcom commentforcom) {
        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
        commentforcom.setCreateTime(timeNow);
        int status = commentforcomMapper.insert(commentforcom);
        if (status == 1) {
            return ResultBean.success(200,"评论(对评论)成功");
        }
        return ResultBean.fail(ErrorEnum.UNKNOWN_EXCEPTION);
    }
}
