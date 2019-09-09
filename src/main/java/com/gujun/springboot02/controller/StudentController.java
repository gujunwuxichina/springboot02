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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /*
        为了简化其method属性，增加了@Get/Post/Patch/Put/DeleteMapping注解；
        tip：在REST风格会讨论Patch/Put/Delete；

     */
    @RequestMapping("/getAll")
    public JSONObject getAll(){
        JSONObject jsonObject=new JSONObject();
        List<Student> students=studentService.getAll();
        jsonObject.put("students",students);
        return jsonObject;
    }

    //配置PageHelper插件分页查询
    @RequestMapping("/getByPage")
    /*
        获取控制器参数：
        1.无注解下获取参数:SpringMVC也可以获取参数，其参数允许为空，要求是参数名称和http请求的参数名称保持一致；
        2.@RequestParam,指定请求参数和方法参数的映射关系，value,required配置属性；
        3.传递数组，支持使用，分割数组元素；见下testArrayParam；
     */
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
    public JSONObject getById(@RequestParam(value = "sId",required = true) Integer sId){
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

    @RequestMapping("/testArrayParam")
    public JSONObject testArrayParam(int[] ints,@RequestParam(value = "strs") String[] strs){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ints",ints);
        jsonObject.put("strs",strs);
        return jsonObject;
    }

}
