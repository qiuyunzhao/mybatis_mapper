package com.haoqian.mapper03_custom.controller;

import com.haoqian.mapper03_custom.entity.Employee;
import com.haoqian.mapper03_custom.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/selectAll")
    public List<Employee> selectAll() {
        List<Employee> employees = employeeService.selectAll();
        return employees;
    }

    // 测试自定义扩展Mapper接口
    @PutMapping("/batchUpdate")
    public int batchUpdate(@RequestBody List<Employee> employees) {
        return employeeService.batchUpdate(employees);
    }

}
