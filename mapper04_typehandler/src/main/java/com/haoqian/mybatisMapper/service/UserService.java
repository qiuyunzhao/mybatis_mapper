package com.haoqian.mybatisMapper.service;

import com.haoqian.mybatisMapper.entity.User;
import com.haoqian.mybatisMapper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public int insertOne(User user) {
        int affectRow = userMapper.insert(user);
        return affectRow;
    }

}
