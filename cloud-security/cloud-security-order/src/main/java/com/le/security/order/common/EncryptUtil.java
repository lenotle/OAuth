package com.le.security.order.common;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Auther: xll
 * @Date: 2022/1/19 16:28
 */
@Slf4j
public class EncryptUtil {
    public static String decodeUTF8StringBase64(String str) {
        String decoded = null;
        byte[] bytes = Base64.getDecoder().decode(str);
        try {
            decoded = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage());
        }
        return decoded;
    }

    public static String encodeUTF8StringBase64(String str) {
        String encoded = null;
        try {
            encoded = Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage());
        }
        return encoded;
    }

    public static void main(String[] args) {
        String encoded = encodeUTF8StringBase64("123");
        System.out.println(encoded);

        String decoded = decodeUTF8StringBase64("MTIz");
        System.out.println(decoded);
    }
}
