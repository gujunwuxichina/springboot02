package com.gujun.springboot02.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gujun.springboot02.entity.Student;
import com.gujun.springboot02.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/getAll")
    public JSONObject getAll(){
        JSONObject jsonObject=new JSONObject();
        List<Student> students=studentService.getAll();
        jsonObject.put("students",students);
        return jsonObject;
    }

    //配置PageHelper插件分页查询
    @RequestMapping("/getByPage")
    public JSONObject getByPage(int pages,int pageSize){
        JSONObject jsonObject=new JSONObject();
        PageHelper.startPage(pages, pageSize);
        List<Student> students=studentService.getAll();
        //如果直接返回list，得到了分页的数据，如果添加下面步骤，返回pageInfo，则能得到包括list在内的分页信息
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        jsonObject.put("pageInfo",pageInfo);
        return jsonObject;
    }


    @RequestMapping("/getById")
    public JSONObject getById(Integer sId){
        JSONObject jsonObject=new JSONObject();
        Student student=studentService.getById(sId);
        jsonObject.put("student",student);
        return jsonObject;
    }

    @RequestMapping("/saveOne")
    public JSONObject saveOne(){
        JSONObject jsonObject=new JSONObject();
        Student student=new Student("gao",22,"女");
        int i=studentService.saveOne(student);
        jsonObject.put("result",i);
        return jsonObject;
    }

    //测试事务功能
    @RequestMapping("/updates")
    public JSONObject updates(){
        JSONObject jsonObject=new JSONObject();
        Student student=studentService.getById(1);
        student.setSex("保密");
        try {
            studentService.updates(student,11);
            jsonObject.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result","error");
        }
        return jsonObject;
    }

}
