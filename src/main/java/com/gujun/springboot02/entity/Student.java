package com.gujun.springboot02.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias(value = "st")    //通过注解取别名；
public class Student implements Serializable {

    private static final long serialVersionUID = -1496372289823312053L;

    private Integer sId;

    private String sName;

    private int sage;

    private String sex;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Student() {
    }

    public Student(String sName, int sage, String sex) {
        this.sName = sName;
        this.sage = sage;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", sage=" + sage +
                ", sex='" + sex + '\'' +
                '}';
    }
}
