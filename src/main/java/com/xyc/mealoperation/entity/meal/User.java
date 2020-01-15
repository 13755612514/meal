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
 * @Date 2020-01-15 14:15:00 
 */

@Entity
@Table ( name ="user")
public class User  implements Serializable {

	private static final long serialVersionUID =  1983486887651619859L;

   	@Column(name = "OBJECT_ID" )
	@Id
	private Long objectId;

   	@Column(name = "USER_NAME" )
	private String userName;

   	@Column(name = "PASSWORD" )
	private String password;

   	@Column(name = "EMAIL" )
	private String email;

   	@Column(name = "CREAT_DT" )
	private String creatDt;

   	@Column(name = "INTRODUCTION" )
	private String introduction;

   	@Column(name = "ADDRESS" )
	private String address;

   	@Column(name = "AGE" )
	private Long age;

   	@Column(name = "BIRTHDAY" )
	private String birthday;

   	@Column(name = "EDUCATIONAL" )
	private String educational;

   	@Column(name = "HEADER" )
	private String header;

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
