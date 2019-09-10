package com.gujun.springboot02.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@RequestMapping("upload")
@RestController
public class UploadController {

    /*
        SpringMVC文件上传：
        在使用SpringMVC上传文件时，还需要配置MultipartHttpServletRequest,这个是通过MultipartResolver接口实现的；
        一般都使用实现类是StandardServletMultipartResolver,SpringBoot会自动配置（会根据配置文件中的配置项自动配置）；
        下面是几种SpringMVC处理上传的方式；
     */

    @PostMapping("/uploadRequest")
    public JSONObject uploadRequest(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        //在调用控制器前，DispatcherServlet会将其转化为还需要配置MultipartHttpServletRequest对象；
        try {
            MultipartHttpServletRequest multipartHttpServletRequest= (MultipartHttpServletRequest) request;//此处直接强转，不先进行判断；
            MultipartFile multipartFile=multipartHttpServletRequest.getFile("file");
            String originName=multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(originName));
            jsonObject.put("result","success");
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("result","error");
        }
        return jsonObject;
    }

    //和上面一样，只是写法不同；
    @PostMapping("/uploadMultipartFile")
    public JSONObject uploadMultipartFile(MultipartFile multipartFile){
        JSONObject jsonObject=new JSONObject();
        try {
            String originName=multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(originName));
            jsonObject.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result","error");
        }
        return jsonObject;
    }

    @PostMapping("/uploadPart")
    public JSONObject uploadPart(Part part){
        JSONObject jsonObject=new JSONObject();
        try {
            String fileName=part.getSubmittedFileName();
            part.write(fileName);
            jsonObject.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result","error");
        }

        return jsonObject;
    }

}
