package com.nuanshui.frms.test.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

@Slf4j
public class RepositoryUtils {
    private RepositoryUtils() {
        //default construct
    }

    /**
     * 获取Exception的详细错误信息
     *
     * @param e
     * @return
     */
    public static String exceptionStackTrace(Throwable e) {
        CharArrayWriter array = new CharArrayWriter(1000);
        PrintWriter writer = new PrintWriter(array);
        e.printStackTrace(writer);
        writer.close();
        return array.toString();
    }

    public static String limitStrLen(String str,int len){
        if(StringUtils.isNotEmpty(str) && str.length() > len){
            return str.substring(0,len);
        }
        return str;
    }

}
