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

}
