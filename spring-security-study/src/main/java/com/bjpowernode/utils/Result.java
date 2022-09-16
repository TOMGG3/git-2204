package com.bjpowernode.utils;

/**
 * @author steven
 * 后台服务向前端响应的统一格式类
 */
public class Result {

    /**
     * 响应码
     * 0成功
     * -1失败
     * -2登录用户不存在
     * -3登录密码有误
     * -4登录用户已被锁定，请联系管理员!
     * -5当前账号已失效，请联系管理员!
     * -6用户名已存在
     * -7原密码有误
     * -11字典类型编码已存在
     * -12字典类型的字典值已存在
     * -13字典类型中存在字典值，请删除字典值后再操作
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应的数据
     */
    private Object data;

    public Result() {
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 成功
     * 新增，修改，删除
     */
    public static Result success(){
        return new Result(0, "success", null);
    }

    /**
     * 成功
     * 查询
     */
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

    public static Result error(Object data){
        return new Result(-1, "error", data);
    }
}
