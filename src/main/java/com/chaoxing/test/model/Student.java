package com.chaoxing.test.model;

import java.io.Serializable;

public class Student implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;

    private String introduce;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", introduce='" + introduce + '\'' +
                '}';
    }

    public Student(Integer id, String name, Integer age, String introduce) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.introduce = introduce;
    }

    public Student(String name, Integer age, String introduce) {
        this.name = name;
        this.age = age;
        this.introduce = introduce;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}