package com.gujun.springboot02.controller;

import com.alibaba.fastjson.JSONObject;
import com.gujun.springboot02.entity.Role;
import com.gujun.springboot02.entity.User;
import com.gujun.springboot02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public JSONObject save(){
        JSONObject jsonObject=new JSONObject();
        User user=new User();
        user.setUserId(2L);
        user.setUserName("gujun");
        List<Role> roles=new ArrayList<>();
        Role role=new Role();
        role.setRoleId(1L);
        role.setRoleName("管理员");
        roles.add(role);
        user.setRoles(roles);
        userService.saveUser(user);
        jsonObject.put("user",user);
        return jsonObject;
    }

}
