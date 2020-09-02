package com.dysy.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.dysy.demo.common.Constant.ERROR_CODE;
import static com.dysy.demo.common.Constant.SUCCESS_CODE;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result() {}

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 判断接口调用是否成功
     * 注意, boolean 类型的方法在解析为 json 时，会被当成一个字段（在此的字段就是 success）
     * @return
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == SUCCESS_CODE;
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(String msg) {
        return new Result(ERROR_CODE, msg);
    }

    public static Result success() {
        return new Result(SUCCESS_CODE, "success");
    }

    public static <T> Result success(T data) {
        return new Result(SUCCESS_CODE, "success", data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}