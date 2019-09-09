package com.gujun.springboot02.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Document   //标识为MongoDB文档
public class User implements Serializable {

    private static final long serialVersionUID = -8591675423959821360L;

    @Id //MongoDB文档编号，即主键
    private Long userId;

    @Field("user_name") //Mongo中使用user_name保存属性;
    private String userName;

    //如果想保存其引用，可以使用@DBRef标注，这样只会保存引用信息，而不是roles;
    private List<Role> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

}
