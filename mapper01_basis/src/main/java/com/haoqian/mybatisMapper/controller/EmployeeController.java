package com.haoqian.mybatisMapper.controller;

import com.haoqian.mybatisMapper.entity.Employee;
import com.haoqian.mybatisMapper.service.EmployeeService;
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

    @PostMapping("/selectOne")
    public Employee selectOne(@RequestBody Employee employeeCondition) {
        Employee employee = employeeService.selectOne(employeeCondition);
        return employee;
    }

    @GetMapping("/selectByPrimaryKey/{id}")
    public Employee selectByPrimaryKey(@PathVariable Integer id) {
        Employee employee = employeeService.selectByPrimaryKey(id);
        return employee;
    }

    @GetMapping("/existsWithPrimaryKey/{id}")
    public boolean existsWithPrimaryKey(@PathVariable Integer id) {
        boolean exist = employeeService.existsWithPrimaryKey(id);
        return exist;
    }

    @PostMapping("/insert")
    public Integer insert(@RequestBody Employee employeeInsert) {
        Integer affectedRows = employeeService.insert(employeeInsert);
        return affectedRows;
    }

    @PostMapping("/insertSelective")
    public Integer insertSelective(@RequestBody Employee employeeInsert) {
        Integer affectedRows = employeeService.insertSelective(employeeInsert);
        return affectedRows;
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public Integer updateByPrimaryKeySelective(@RequestBody Employee employeeUpdate) {
        Integer affectedRows = employeeService.updateByPrimaryKeySelective(employeeUpdate);
        return affectedRows;
    }

    @PostMapping("/delete")
    public Integer delete(@RequestBody Employee employeeDelete) {
        Integer affectedRows = employeeService.delete(employeeDelete);
        return affectedRows;
    }

    @GetMapping("/deleteByPrimaryKey/{id}")
    public Integer delete(@PathVariable Integer id) {
        Integer affectedRows = employeeService.deleteByPrimaryKey(id);
        return affectedRows;
    }

    // ================================== QBC查询 ======================================
    @GetMapping("/qbcQuery1")
    public List<Employee> qbcQuery1() {
        return employeeService.qbcQuery1();
    }

    @GetMapping("/qbcQuery2")
    public List<Employee> qbcQuery2() {
        return employeeService.qbcQuery2();
    }

}
