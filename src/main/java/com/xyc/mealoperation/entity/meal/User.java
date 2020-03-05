package com.xyc.mealoperation.entity.meal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @Description  
 * @Author  xiongyancong
 * @Date 2020-01-15 14:15:00 
 */
public class User  implements Serializable {

	private static final long serialVersionUID =  1983486887651619859L;

	@TableId(type = IdType.AUTO)
	private Long objectId;
	private String userName;
	private String password;
	private String email;
	private String creatDt;
	private String introduction;
	private String address;
	private Long age;
	private String birthday;
	private String educational;
	private String header;

	public User() {
	}

	public User(Long objectId) {
		this.objectId = objectId;
	}

	public User(Long objectId, String userName, String password, String email, String creatDt, String introduction, String address, Long age, String birthday, String educational, String header) {
		this.objectId = objectId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.creatDt = creatDt;
		this.introduction = introduction;
		this.address = address;
		this.age = age;
		this.birthday = birthday;
		this.educational = educational;
		this.header = header;
	}

	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatDt() {
		return this.creatDt;
	}

	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getAge() {
		return this.age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEducational() {
		return this.educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getHeader() {
		return this.header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "{" +
					"objectId='" + objectId + '\'' +
					"userName='" + userName + '\'' +
					"password='" + password + '\'' +
					"email='" + email + '\'' +
					"creatDt='" + creatDt + '\'' +
					"introduction='" + introduction + '\'' +
					"address='" + address + '\'' +
					"age='" + age + '\'' +
					"birthday='" + birthday + '\'' +
					"educational='" + educational + '\'' +
					"header='" + header + '\'' +
				'}';
	}

}
