package com.example.auxiliary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ztx
 * @date 2021-02-27 11:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 成功数据
     */
    private T data;

    /**
     * 响应编码200为成功
     */
    private Integer code;

    /**
     * 请求消耗时间
     */
    private long cost;

    /**
     * 描述
     */
    private String msg;

    /**
     * 请求id
     */
    private String requestId;

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result create(Integer code, String msg){
        Result result = new Result(code, msg);
        return result;
    }

    /**
     * 无数据返回成功
     * @return
     */
    public static Result createSuccess() {
        return create(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc());
    }

    /**
     * 有数据返回成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> createSuccess(T data) {
        return createSuccess().setData(data);
    }

    /**
     * 无描述返回失败
     * @return
     */
    public static Result createFail() {
        return create(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getDesc());
    }

    /**
     * 描述返回失败
     * @param msg
     * @return
     */
    public static Result createFail(String msg) {
        return create(ResultEnum.FAIL.getCode(),msg);
    }

    /**
     * 自定义返回失败描述
     * @param code
     * @param msg
     * @return
     */
    public static Result createFail(Integer code, String msg) {
        return create(code, msg);
    }

    public Result setData(T data){
        this.data = data;
        return this;
    }

}