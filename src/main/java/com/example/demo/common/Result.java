package com.example.demo.common;


/**
 * 返回值类
 */
public class Result {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 构造函数
     *
     * @param code
     * @param message
     */
    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param code
     * @param message
     * @param data
     */
    private Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    private static int CODE_SUCCESS = 0;

    private static int CODE_EROOR = 1;

    public boolean isSuccess() {
        return code == CODE_SUCCESS;
    }

    public static Result sucessData(Object data) {
        return new Result(CODE_SUCCESS, "", data);
    }

    public static Result error(int code, String message) {
        return new Result(code, message);
    }

    public static Result success() {
        return new Result(CODE_SUCCESS, "");
    }

    /**
     * 成功
     *
     * @param message
     * @return 成功时返回码为0
     */
    public static Result success(String message) {
        return new Result(CODE_SUCCESS, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
