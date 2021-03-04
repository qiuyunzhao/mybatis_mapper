package com.haoqian.mybatisMapper.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass({DruidDataSource.class})
@ConditionalOnMissingBean({DataSource.class})
@ConditionalOnProperty(
        name = {"spring.datasource.type"},
        havingValue = "com.alibaba.druid.pool.DruidDataSource"
)
//@PropertySource(value = "classpath:druid.yml")
public class DruidConfig {
    /**
     * 注册 Druid 数据源,将配置文件中的配置项绑定到DataSource对象的属性中
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}