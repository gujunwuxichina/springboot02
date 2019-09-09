package com.gujun.springboot02.service;

import com.gujun.springboot02.entity.User;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.bulk.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user,"user");    //使用名称为user的文档保存信息
    }

    @Override
    public DeleteRequest deleteUserById(Long userId) {
        return null;
    }

    @Override
    public User findById(Long userId) {
        return mongoTemplate.findById(userId,User.class);
    }

    @Override
    public List<User> findUsers(String userName, int skip, int limit) {
        return null;
    }

    @Override
    public UpdateRequest updateUser(User user) {
        return null;
    }
}
