package com.haoqian.mybatisMapper.service;

import com.haoqian.mybatisMapper.entity.Employee;
import com.haoqian.mybatisMapper.mapper.EmployeeMapper;
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

    /**
     * 根据条件查询
     * <p>
     * 实体类封装查询条件生成 WHERE 子句的规则：
     * (1) 使用非空的属性值生成 WHERE 子句;
     * (2) 在条件表达式中使用 “=” 进行比较;
     *
     * @param employeeCondition 查询条件
     * @return 要求必须返回一个实体类结果，如果有多个，则会抛出异常;
     */
    public Employee selectOne(Employee employeeCondition) {
        Employee employee = employeeMapper.selectOne(employeeCondition);
        return employee;
    }


    /**
     * 根据主键进行查询
     * 注意：实体类中必须使用 @Id 标明主键映射
     *
     * @param id 查询条件-主键
     * @return
     */
    public Employee selectByPrimaryKey(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * 根据主键查询是否存在
     *
     * @param id
     * @return
     */
    public boolean existsWithPrimaryKey(Integer id) {
        boolean exist = employeeMapper.existsWithPrimaryKey(id);
        return exist;
    }

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param employeeInsert
     * @return 返回受影响的行数
     * 主键上若有@GeneratedValue注解，则会返回新插入数据的主键到属性上
     */
    public Integer insert(Employee employeeInsert) {
        System.out.println("插入前：" + employeeInsert);

        Integer affectedRows = employeeMapper.insert(employeeInsert); // 返回受影响记录数

        System.out.println("插入后：" + employeeInsert); // 新增数据的主键回写
        return affectedRows;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param employeeInsert
     * @return 返回受影响的行数
     * 主键上若有@GeneratedValue注解，则会返回新插入数据的主键到属性上
     */
    public Integer insertSelective(Employee employeeInsert) {
        System.out.println("插入前：" + employeeInsert);

        Integer affectedRows = employeeMapper.insertSelective(employeeInsert);

        System.out.println("插入后：" + employeeInsert); // 新增数据的主键回写
        return affectedRows;
    }

    /**
     * 根据主键更新属性不为null的值，属性为null的保持数据库中原来的值
     *
     * @param employeeUpdate
     * @return 返回受影响的行数
     */
    public Integer updateByPrimaryKeySelective(Employee employeeUpdate) {
        System.out.println("更新前：" + employeeUpdate);

        Integer affectedRows = employeeMapper.updateByPrimaryKeySelective(employeeUpdate);

        System.out.println("更新后：" + employeeUpdate);
        return affectedRows;
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * 注意如果所有属性均为null，会没有where条件，导致数据表清空
     *
     * @param employeeDelete
     * @return 返回受影响的行数
     */
    public Integer delete(Employee employeeDelete) {
        System.out.println("删除前：" + employeeDelete);

        Integer affectedRows = employeeMapper.delete(employeeDelete);

        System.out.println("删除后：" + employeeDelete);
        return affectedRows;
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param id
     * @return 返回受影响的行数
     */
    public Integer deleteByPrimaryKey(Integer id) {
        System.out.println("删除前：" + id);

        Integer affectedRows = employeeMapper.deleteByPrimaryKey(id);
        return affectedRows;
    }


    // ===========================================  QBC查询 =========================================

    /**
     * 目标：WHERE (emp_salary>? AND emp_age<?) OR (emp_salary<? AND emp_age>?)
     *
     * @return
     */
    public List<Employee> qbcQuery1() {
        // 1.创建 Example 对象
        Example example = new Example(Employee.class);

        // 2.通过 Example 对象创建 Criteria 对象
        Example.Criteria criteria01 = example.createCriteria();
        Example.Criteria criteria02 = example.createCriteria();

        // 3.在两个 Criteria 对象中分别设置查询条件
        //    property 参数：实体类的属性名
        //    value    参数：实体类的属性值
        criteria01.andGreaterThan("empSalary", 3000)
                .andLessThan("empAge", 25);
        criteria02.andLessThan("empSalary", 5000)
                .andGreaterThan("empAge", 30);

        // 4.使用 OR 关键词组装两个 Criteria 对象(设计的有点别扭)
        example.or(criteria02);

        //5.执行查询
        List<Employee> empList = employeeMapper.selectByExample(example);

        return empList;
    }

    /**
     * 目标：
     * SELECT distinct emp_salary , emp_age
     * FROM tabple_emp
     * WHERE ( ( emp_salary > ? and emp_age < ? ) ) order by emp_salary ASC,emp_age DESC
     *
     * @return
     */
    public List<Employee> qbcQuery2() {

        Example example = new Example(Employee.class);

        Example.Criteria criteria01 = example.createCriteria();
        criteria01.andGreaterThan("empSalary", 1000)
                .andLessThan("empAge", 50);

        // 设置排序
        example.orderBy("empSalary").asc()
                .orderBy("empAge").desc();
        // 设置去重
        example.setDistinct(true);

        // 设置需要查询的字段
        example.selectProperties("empSalary").selectProperties("empAge");

        // 执行查询
        List<Employee> empList = employeeMapper.selectByExample(example);

        return empList;
    }
}
