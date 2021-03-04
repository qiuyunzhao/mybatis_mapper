package com.haoqian.mapper03_custom.CustomMapper;

import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * 用于生成动态SQL的类;
 * 必须继承MapperTemplate;
 */
public class MyBatchUpdateProvider extends MapperTemplate {

    /**
     * 必须加入该构造器，因为没有无参构造器
     */
    public MyBatchUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 必须有一个与自定义Mapper扩展接口中所声明方法的同名方法;
     * 该方法用于生成配套的动态SQL（mybatis的动态SQL拼接）
     *
     * <foreach collection="list" item="record" separator=";" >
     * - UPDATE tabple_emp
     * -- <set>
     * --- emp_name=#{record.empName},
     * --- emp_age=#{record.empAge},
     * --- emp_salary=#{record.empSalary}
     * -- </set>
     * - WHERE emp_id=#{record.empId}
     * </foreach>
     *
     * @param ms
     */
    public String batchUpdate(MappedStatement ms) {

        String idColumnName = null; // 主键名称
        String idHolder = null;     // 主键值的封装类

        // 1.创建StringBuilder用于拼接SQL
        StringBuilder builder = new StringBuilder();

        // 2.拼接<foreach>标签
        builder.append("<foreach collection=\"list\" item=\"record\" separator=\";\" >");

        // 3.获取实体类对应的Class对象
        Class<?> entityClass = super.getEntityClass(ms); // 实体类型
        // 4.获取实体类在数据库中对应的表名
        String tableName = super.tableName(entityClass); // 获取表名
        // 5.拼接update
        String updateClause = SqlHelper.updateTable(entityClass, tableName); // 更新语句
        builder.append(updateClause);

        // 6.拼接<set>标签
        builder.append("<set>");
        Set<EntityColumn> entityColumns = EntityHelper.getColumns(entityClass); // 获取所有实体类属性
        for (EntityColumn entityColumn : entityColumns) {
            if (entityColumn.isId()) {
                // 如果是主键
                idColumnName = entityColumn.getColumn();
                idHolder = entityColumn.getColumnHolder();
            } else {
                // 7.使用非主键字段拼接set子句
                String column = entityColumn.getColumn(); // 获取数据库字段名
                String columnHolder = entityColumn.getColumnHolder();// 实体类属性值封装对象
                builder.append(column).append("=").append(columnHolder).append(",");
            }
        }
        builder.append("</set>");

        // 8.拼接WHERE条件
        builder.append("WHERE ").append(idColumnName).append("=").append(idHolder);

        builder.append("</foreach>");

        // 9.返回动态SQL语句
        return builder.toString();
    }
}
