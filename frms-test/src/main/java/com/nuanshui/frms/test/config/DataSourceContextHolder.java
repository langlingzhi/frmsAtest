package com.nuanshui.frms.test.config;

import com.nuanshui.frms.test.command.DataSourceType;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(DataSourceType.read.getType());
    }
    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.write.getType());
    }

    public static void app() {
        local.set(DataSourceType.app.getType());
    }


    public static void cs() {
        local.set(DataSourceType.cs.getType());
    }

    public static String getJdbcType() {
        return local.get();
    }
}
