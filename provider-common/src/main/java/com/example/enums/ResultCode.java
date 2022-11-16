package com.example.enums;

/**
 * @author Yincn
 * @Date 2022/3/15
 */
public enum ResultCode {
    SUCCESS("200", "成功"),
    ERROR("99999", "系统繁忙，请稍后再试");

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
