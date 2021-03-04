package com.haoqian.mybatisMapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tabple_emp") // 配置实体类与表映射
public class Employee implements Serializable { // 二级缓存需要实现序列化

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
    private Integer empId;

    private String empName;

    @Column(name = "emp_salary") // name: 指定数据库映射字段名称
    private Double empSalary;

    private Integer empAge;

    @Transient // 非数据库中对应字段
    private String Phone;
}
