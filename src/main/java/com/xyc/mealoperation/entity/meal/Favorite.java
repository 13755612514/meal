package com.xyc.mealoperation.entity.meal;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  xiongyancong
 * @Date 2020-01-15 17:49:25 
 */


public class Favorite  implements Serializable {

	private static final long serialVersionUID =  8220078060728008032L;
	private Long objectId;
	private Long userId;
	private Long dyId;
	private Date creatTime;
	private Date updateTime;

	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDyId() {
		return this.dyId;
	}

	public void setDyId(Long dyId) {
		this.dyId = dyId;
	}

	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "{" +
					"objectId='" + objectId + '\'' +
					"userId='" + userId + '\'' +
					"dyId='" + dyId + '\'' +
					"creatTime='" + creatTime + '\'' +
					"updateTime='" + updateTime + '\'' +
				'}';
	}

}
