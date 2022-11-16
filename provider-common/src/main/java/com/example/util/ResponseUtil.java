package com.example.util;

import com.example.enums.ResultCode;
import com.example.res.Response;


/**
 * @author Yincn
 * @Date 2022/3/15
 */
public class ResponseUtil {
    public static <T> Response<T> genResp(T data) {
        Response<T> resp = new Response<>();
        resp.setCode(ResultCode.SUCCESS.getCode());
        resp.setMsg(ResultCode.SUCCESS.getMsg());
        resp.setData(data);
        return resp;
    }
}
