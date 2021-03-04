package com.haoqian.mapper03_custom.config;

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

    /**
     * 配置Druid的监控服务器(一个管理后台的Servlet)
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        // 利用servletRegistrationBean注册servlet
        ServletRegistrationBean<StatViewServlet> bean
                = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
        bean.addInitParameter("allow", "127.0.0.1");
        // 添加IP黑名单(当白名单和黑名单重复时，黑名单优先级更高)
        bean.addInitParameter("deny", "192.168.15.21");
        // 添加控制台管理用户
        bean.addInitParameter("loginUsername", "root");
        bean.addInitParameter("loginPassword", "aaaaaa");
        // 是否能够重置数据
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    /**
     * 配置服务过滤器
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> statFilter() {
        FilterRegistrationBean<WebStatFilter> bean
                = new FilterRegistrationBean<>(new WebStatFilter());
        // 添加过滤规则
        bean.addUrlPatterns("/*");
        // 忽略过滤格式
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return bean;
    }
}