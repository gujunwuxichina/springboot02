package com.gujun.springboot02.service;

import com.gujun.springboot02.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll();

    Student getById(Integer sId);

    int saveOne(Student student);

    int update(Student student);

    int deleteById(Integer sId);

    void updates(Student student,Integer sId);

}
