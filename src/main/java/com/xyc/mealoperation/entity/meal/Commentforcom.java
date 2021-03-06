package com.xyc.mealoperation.entity.meal;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
public class Commentforcom {

    @TableId(type = IdType.AUTO)
    private long objectId;
    private long senderId;
    private long recipId;
    private String type;
    private String status;
    private String content;
    private long relationId;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private java.sql.Timestamp createTime;
    private long commentId;

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getRecipId() {
        return recipId;
    }

    public void setRecipId(long recipId) {
        this.recipId = recipId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getRelationId() {
        return relationId;
    }

    public void setRelationId(long relationId) {
        this.relationId = relationId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Commentforcom{" +
                "objectId=" + objectId +
                ", senderId=" + senderId +
                ", recipId=" + recipId +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                ", relationId=" + relationId +
                ", createTime=" + createTime +
                ", commentId=" + commentId +
                '}';
    }
}
