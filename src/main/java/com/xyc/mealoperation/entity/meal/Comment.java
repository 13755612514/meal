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
}
