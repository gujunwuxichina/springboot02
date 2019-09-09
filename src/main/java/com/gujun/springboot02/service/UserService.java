package com.gujun.springboot02.service;

import com.gujun.springboot02.entity.User;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.bulk.UpdateRequest;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    DeleteRequest deleteUserById(Long userId);

    User findById(Long userId);

    List<User> findUsers(String userName,int skip,int limit);

    UpdateRequest updateUser(User user);

}
