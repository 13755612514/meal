package com.xyc.mealoperation.entity.meal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @Description  
 * @Author  xiongyancong
 * @Date 2020-01-15 16:22:54 
 */

public class Relation  implements Serializable {

	private static final long serialVersionUID =  4307618229017387148L;
	@TableId(type = IdType.AUTO)
	private Long obiectId;
	private Long userId;
	private Long attentionId;
	private String followedHeader;
	private String createDt;


	public String getFollowedHeader() {
		return followedHeader;
	}

	public void setFollowedHeader(String followedHeader) {
		this.followedHeader = followedHeader;
	}

	public Long getObiectId() {
		return this.obiectId;
	}

	public void setObiectId(Long obiectId) {
		this.obiectId = obiectId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAttentionId() {
		return this.attentionId;
	}

	public void setAttentionId(Long attentionId) {
		this.attentionId = attentionId;
	}

	public String getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	@Override
	public String toString() {
		return "{" +
					"obiectId='" + obiectId + '\'' +
					"userId='" + userId + '\'' +
					"attentionId='" + attentionId + '\'' +
					"followedId='" + followedHeader + '\'' +
					"createDt='" + createDt + '\'' +
				'}';
	}

}
