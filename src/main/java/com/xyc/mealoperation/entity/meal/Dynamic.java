package com.xyc.mealoperation.entity.meal;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Dynamic {

  private int objectId;
  private long sendId;
  private String title;
  private String description;
  private String content;
  private Timestamp createTime;
  private long type;
  private long likeCount;
  private long favoriteCount;

  public int getObjectId() {
    return objectId;
  }

  public void setObjectId(int objectId) {
    this.objectId = objectId;
  }

  public long getSendId() {
    return sendId;
  }

  public void setSendId(long sendId) {
    this.sendId = sendId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

  public long getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(long likeCount) {
    this.likeCount = likeCount;
  }

  public long getFavoriteCount() {
    return favoriteCount;
  }

  public void setFavoriteCount(long favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  @Override
  public String toString() {
    return "Dynamic{" +
            "objectId=" + objectId +
            ", sendId=" + sendId +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", content='" + content + '\'' +
            ", createTime=" + createTime +
            ", type=" + type +
            ", likeCount=" + likeCount +
            ", favoriteCount=" + favoriteCount +
            '}';
  }
}
