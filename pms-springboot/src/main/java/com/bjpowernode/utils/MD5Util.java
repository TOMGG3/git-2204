package com.bjpowernode.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author steven
 * MD5工具类
 * MD5是一种算法：它可以将任意的字符串转换为32位固定字符串
 */
public class MD5Util {

    public static String getMD5(String source){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //调用md5函数转换源串
            md5.update(source.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String source = "123456";
        System.out.println(MD5Util.getMD5(source));
    }
}
