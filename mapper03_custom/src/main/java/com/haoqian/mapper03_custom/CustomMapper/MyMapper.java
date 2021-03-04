package com.haoqian.mapper03_custom.CustomMapper;

import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

/**
 * 自定义Mapper接口，继承Mapper提供的某个接口进行扩展
 * 注意不能与mapper数据库操作接口放在同一个目录下，
 *
 * @param <T>
 */
public interface MyMapper<T> extends
        SelectAllMapper<T>,
        SelectByExampleMapper<T>,
        // 继承自己的扩展Mapper接口
        MyBatchUpdateMapper<T> {
}
