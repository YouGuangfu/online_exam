package com.you.online_exam.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 加密工具
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-27 17:54
 **/
public class EncryptUtils {
    public static String EncoderByMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }
    }
}
