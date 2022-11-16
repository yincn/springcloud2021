package com.example.util;

import java.util.UUID;

/**
 * 日志相关工具类
 * @Author Yincn
 * @Date 2021/9/9
 */
public class LogUtil {

    /**
     * 生成随机数并替换横线
     * @return
     */
    public static synchronized String getUUID() {
        return UUID.randomUUID().toString();
    }
}