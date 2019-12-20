package com.xyc.mealoperation.entity.meal;

import lombok.Data;

@Data
public class User {
    private String objectId;

    private String userName;

    private String password;

    private String email;

    private String creatDt;

    private String introduction;

    private String address;

    private Integer age;

    private String birthday;

    private String educational;

    private String header;

    public User(String objectId, String userName, String password, String email, String creatDt, String introduction, String adress, Integer age, String birthday, String educational, String header) {
        this.objectId = objectId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.creatDt = creatDt;
        this.introduction = introduction;
        this.address = adress;
        this.age = age;
        this.birthday = birthday;
        this.educational = educational;
        this.header = header;
    }

    public User() {
        super();
    }

}