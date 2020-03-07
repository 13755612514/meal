package com.xyc.mealoperation.controller;


import com.xyc.mealoperation.constant.ResultBean;
import com.xyc.mealoperation.entity.meal.Comment;
import com.xyc.mealoperation.entity.meal.Commentforcom;
import com.xyc.mealoperation.mapper.CommentMapper;
import com.xyc.mealoperation.mapper.CommentforcomMapper;
import com.xyc.mealoperation.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/addComment")
    @ResponseBody
    public ResultBean<?> addComment(@RequestParam("senderId") Long senderId,
                                    @RequestParam("recipId") Long recipId,
                                    @RequestParam("dyId") Long dyId,
                                    @RequestParam("content") String content) {
        Comment comment = new Comment();
        comment.setSenderId(senderId);
        comment.setRecipId(recipId);
        comment.setDyId(dyId);
        comment.setContent(content);
        return commentService.addComment(comment);
    }

    @RequestMapping(value = "/addCommentForCom")
    @ResponseBody
    public ResultBean<?> addCommentForCom(@RequestParam("senderId") Long senderId,
                                    @RequestParam("recipId") Long recipId,
                                    @RequestParam("commentId") Long commentId,
                                    @RequestParam("content") String content) {
        Commentforcom commentforcom = new Commentforcom();
        commentforcom.setSenderId(senderId);
        commentforcom.setRecipId(recipId);
        commentforcom.setCommentId(commentId);
        commentforcom.setContent(content);
        return commentService.addCommentForCom(commentforcom);
    }

    @RequestMapping(value = "/commentOut")
    @ResponseBody
    public ResultBean<?> commentOut(@RequestParam("dyId") Long dyId) {
        return commentService.getAllByDyId(dyId);
    }
}
