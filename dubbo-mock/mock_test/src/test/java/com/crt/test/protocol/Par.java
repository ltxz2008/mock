package com.crt.test.protocol;

import java.io.Serializable;

/**
 * 
 * @author duanzongxiang1
 *
 */
public class Par implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    @Override
    public String toString() {
	return "Par [name=" + name + ", age=" + age + "]";
    }

}
