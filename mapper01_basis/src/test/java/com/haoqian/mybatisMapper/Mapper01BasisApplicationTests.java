package com.haoqian.mybatisMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Mapper01BasisApplicationTests {

    @Test
    void contextLoads() {}

    @Autowired
    DataSource dataSource;

    @Test
    public void testDruid() throws SQLException {
        System.out.println(dataSource.getClass()); // 数据源
        Connection connection = dataSource.getConnection();// 从数据源获取连接
        System.out.println(connection);
        connection.close();
    }
}
