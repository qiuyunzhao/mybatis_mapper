package com.haoqian.mybatisMapper.mapper;

import com.haoqian.mybatisMapper.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

}
