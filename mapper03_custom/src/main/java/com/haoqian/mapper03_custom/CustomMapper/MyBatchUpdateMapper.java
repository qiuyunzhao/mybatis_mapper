package com.haoqian.mapper03_custom.CustomMapper;

import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;

import java.util.List;

@RegisterMapper
public interface MyBatchUpdateMapper<T> {
    /**
     * 批量更新
     *
     * @param list 被更新数据列表；
     * @UpdateProvider 用于标注操作类型；
     * type：指定生成配套动态SQL的类；
     * method：值是固定的dynamicSQL，表示使用Mybatis动态SQL进行操作；
     */
    @UpdateProvider(type = MyBatchUpdateProvider.class, method = "dynamicSQL")
    int batchUpdate(List<T> list);
}
