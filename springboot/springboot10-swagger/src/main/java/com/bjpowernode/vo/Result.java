package com.bjpowernode.vo;

import io.swagger.annotations.ApiModel;

/**
 * 统一响应前端的数据格式
 */
public class Result {

    /**
     * 响应码：告知前端业务处理是否成功
     * 0 成功
     * -1 失败
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    public Result() {
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     */
    public static Result success(){
        return new Result(0, "success", null);
    }

    public static Result success(Object data){
        return new Result(0, "success", data);
    }

    /**
     * 失败
     */
    public static Result error(){
        return new Result(-1, "error", null);
    }

    public static Result error(Integer code, String message){
        return new Result(code, message, null);
    }

    public static Result error(Integer code, String message, Object data){
        return new Result(code, message, data);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
