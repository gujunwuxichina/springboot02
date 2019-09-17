package com.gujun.springboot02.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gujun.springboot02.entity.Student;
import com.gujun.springboot02.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
        4.传递JSON,方法参数使用@RequestBody修饰，JSON数据和实体类的属性名保持一致；见下testJSONParam;
        5.通过URL传递参数，有一些网站，提出了REST风格，此时参数往往通过URL传递；SpringMVC对此也提供了良好的支持，
        可以通过处理器映射和@PathVariable组合获取URL参数；首先通过处理器映射定位参数位置和名称，再@PathVariable通过名称来获取参数；见下testUrl；
        6.获取格式化参数，H;

        自定义参数转换规则；

        数据验证：

        数据模型：

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

    /*
        重定向：就是通过各种方法将各种网络请求重新定个方向转到其他位置；
     */
    @RequestMapping("/saveOneAndRedirect")
    public void saveOneAndRedirect(HttpServletRequest request, HttpServletResponse response){
        Student student=new Student("yao",100,"男");
        try {
            studentService.saveOne(student);
            response.sendRedirect("/student/getAll");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        重定向时传递参数：
     */
    @RequestMapping("/saveOneAndRedirectAndParam")
    public void saveOneAndRedirectAndParam(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra){
        Student student=new Student("xi",100,"男");
        try {
//            studentService.saveOne(student);
//            ra.addAttribute("name","gujun");  //跟在url后面；
            ra.addFlashAttribute("name","gujun");    //把参数放在session中 ，跳转之后再从session中移除;
            response.sendRedirect("/student/showStu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //取不到   ???
    @RequestMapping("/showStu")
    public JSONObject showStu(@ModelAttribute("name") String name){
        JSONObject jsonObject=new JSONObject();
        System.out.println(name);
        jsonObject.put("name",name);
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

    @RequestMapping("/testJSONParam")
    public void testJSONParam(@RequestBody Student student){
        System.out.println(student.toString());
    }

    @RequestMapping("/testUrl/{name}")
    public void testUrl(@PathVariable("name") String name){
        System.out.println(name);
    }

    /*
        操作会话对象session：
        SpringMVC提供了两个注解；
        @SessionAttribute,将HttpSession中的属性取出，赋予指定参数；
        @SessionAttributes,将数据模型保存到Session中；
        H
     */

}
