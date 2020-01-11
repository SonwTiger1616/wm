package com.wm.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.UUID;

public class MD5 {

    /**
     * MD5加密
     * @param text 明文
     * @param key 密钥
     * @return
     */
    public static String md5(String text, String key) {
        return DigestUtils.md5DigestAsHex((text + key).getBytes());
    }

    /**
     * 验证MD5
     * @param text 明文
     * @param key 密钥
     * @param md5 需要验证的MD5值
     * @return
     */
    public static boolean verify(String text, String key, String md5) {
        if (StringUtils.isEmpty(md5)) return false;
        if (md5.equalsIgnoreCase(md5(text, key))) {
            return true;
        }
        return false;
    }

    /**
     * 获取随机密钥
     * @return
     */
    public static String getKey() {
        Random random = new Random();
        int in = random.nextInt();
        return String.valueOf(in);
    }

    public static void main(String[] args) {
        System.out.println(md5("admin", "123"));
    }

}
