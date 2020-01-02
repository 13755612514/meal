package com.xyc.mealoperation.entity.meal;


import lombok.Data;

@Data
public class Commentforcom {

  private long objectId;
  private long senderId;
  private long recipId;
  private String type;
  private String status;
  private String content;
  private long relationId;
  private java.sql.Timestamp createTime;
  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }
  private long commentId;

}
