package com.haoqian.mapper03_custom.mapper;

import com.haoqian.mapper03_custom.CustomMapper.MyMapper;
import com.haoqian.mapper03_custom.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper extends MyMapper<Employee> {

}
