package com.mock.dubbo.domain;

import java.io.Serializable;

/**
 * 
 * @author duanzongxiang1
 *
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer id;
    private String userName;
    private Integer age;

    public User() {

    }

    public User(Integer id, String userName, Integer age) {
	this.id = id;
	this.userName = userName;
	this.age = age;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }
}
