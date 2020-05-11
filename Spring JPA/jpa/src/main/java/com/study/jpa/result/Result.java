package com.study.jpa.result;

/**
 * @Author: 张远志
 * @Date: 2020/4/24 14:48
 */
public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> Result ok(T data){
        Result result = new Result(200,"查询成功",data);
        return result;
    }
}
