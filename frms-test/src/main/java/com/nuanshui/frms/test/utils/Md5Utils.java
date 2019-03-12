package com.nuanshui.frms.test.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * MD5摘要工具类
 *
 * @author xingshi
 */
//密码加密其实就是多次md5加密
@Slf4j
public class Md5Utils {

    private Md5Utils() {
    }

    public static String md5ToBas64(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return StringUtils.toEncodedString(Base64.getEncoder().encode(md.digest(data.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            log.error("not suport md5", e);
            return null;
        }
    }
}
