package com.xyc.mealoperation.entity.meal;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Comment {

  private int objectId;
  private long senderId;
  private long recipId;
  private String type;
  private String status;
  private String content;
  private long dyId;
  private Timestamp createTime;
  private List<Commentforcom> commentforcomList;

    public Comment() {
    }

    public Comment(int objectId, long senderId, long recipId, String type, String status, String content, long dyId, Timestamp createTime, List<Commentforcom> commentforcomList) {
        this.objectId = objectId;
        this.senderId = senderId;
        this.recipId = recipId;
        this.type = type;
        this.status = status;
        this.content = content;
        this.dyId = dyId;
        this.createTime = createTime;
        this.commentforcomList = commentforcomList;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
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

    public long getDyId() {
        return dyId;
    }

    public void setDyId(long dyId) {
        this.dyId = dyId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public List<Commentforcom> getCommentforcomList() {
        return commentforcomList;
    }

    public void setCommentforcomList(List<Commentforcom> commentforcomList) {
        this.commentforcomList = commentforcomList;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "objectId=" + objectId +
                ", senderId=" + senderId +
                ", recipId=" + recipId +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                ", dyId=" + dyId +
                ", createTime=" + createTime +
                ", commentforcomList=" + commentforcomList +
                '}';
    }
}
