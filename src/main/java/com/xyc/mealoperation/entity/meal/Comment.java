package com.xyc.mealoperation.entity.meal;

import lombok.Data;

@Data
public class Comment {

  private String objectId;
  private long senderId;
  private long recipId;
  private String type;
  private String status;
  private String content;
  private long relationId;
  private java.sql.Timestamp createTime;

}
