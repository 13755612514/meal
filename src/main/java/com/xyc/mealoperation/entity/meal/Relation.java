package com.xyc.mealoperation.entity.meal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description  
 * @Author  xiongyancong
 * @Date 2020-01-15 16:22:54 
 */

@Entity
@Table ( name ="relation")
public class Relation  implements Serializable {

	private static final long serialVersionUID =  4307618229017387148L;

	@Id
   	@Column(name = "OBIECT_ID" )
	private Long obiectId;

	/**
	 * 用户id
	 */
   	@Column(name = "USER_ID" )
	private Long userId;

	/**
	 * 关注id
	 */
   	@Column(name = "ATTENTION_ID" )
	private Long attentionId;

	/**
	 * 被关注id
	 */
   	@Column(name = "FOLLOWED_ID" )
	private Long followedId;

	/**
	 * 创建日期
	 */
   	@Column(name = "CREATE_DT" )
	private String createDt;

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

	public Long getFollowedId() {
		return this.followedId;
	}

	public void setFollowedId(Long followedId) {
		this.followedId = followedId;
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
					"followedId='" + followedId + '\'' +
					"createDt='" + createDt + '\'' +
				'}';
	}

}
