package com.haoqian.mapper03_custom.service;

import com.haoqian.mapper03_custom.entity.Employee;
import com.haoqian.mapper03_custom.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> selectAll() {
        List<Employee> employees = employeeMapper.selectAll();
        return employees;
    }

    public int batchUpdate(List<Employee> employees) {
        return employeeMapper.batchUpdate(employees);
    }
}
