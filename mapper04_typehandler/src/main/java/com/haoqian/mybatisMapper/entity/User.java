package com.haoqian.mybatisMapper.entity;

import com.haoqian.mybatisMapper.typeHandler.AddressTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
    private Integer userId;
    private String userName;

    // @ColumnType(typeHandler = AddressTypeHandler.class) //使用自定义类型转换器
    @Column // 将复杂类型当做普通类型对待
    private Address address;

    // @ColumnType(jdbcType = JdbcType.VARCHAR) // 将枚举类型当做String简单类型处理
    @Column
    private SeasonEnum season;
}
