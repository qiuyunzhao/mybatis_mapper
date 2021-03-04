package com.haoqian.mybatisMapper.mapper;

import com.haoqian.mybatisMapper.entity.Employee;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
@CacheNamespace // 使用二级缓存
public interface EmployeeMapper extends Mapper<Employee> {

}
