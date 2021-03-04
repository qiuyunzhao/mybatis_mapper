package com.haoqian.commons;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装请求的响应结果的类
 * <p>
 * 注解版的进阶做法见下文
 * https://mp.weixin.qq.com/s/EW-vo8ERQLAVc3D8YwTr6w
 */
@Data
public class Result implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    public void setResultCodeAndMessage(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    /**
     * 返回不带数据的成功
     *
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setResultCodeAndMessage(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 返回带数据的成功
     *
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCodeAndMessage(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 返回不带数据的失败
     *
     * @return
     */
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCodeAndMessage(resultCode);
        return result;
    }

    /**
     * 返回带数据的失败
     *
     * @return
     */
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCodeAndMessage(resultCode);
        result.setData(data);
        return result;
    }
}
