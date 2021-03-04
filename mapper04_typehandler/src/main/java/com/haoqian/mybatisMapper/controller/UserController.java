package com.haoqian.mybatisMapper.controller;

import com.haoqian.mybatisMapper.entity.User;
import com.haoqian.mybatisMapper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/selectAll")
    public List<User> selectAll() {
        List<User> users = userService.selectAll();
        return users;
    }

    @PostMapping("/insert")
    public int insertOne(@RequestBody User user) {
        return userService.insertOne(user);
    }

}
