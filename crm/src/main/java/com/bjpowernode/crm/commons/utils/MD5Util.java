package com.bjpowernode.crm.commons.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密工具类
 * md5是一种算法，它可以将任何的字符串转换为固定长度的字符串（32位，64位），不可逆
 *
 * 暴力破解：对比
 * 1 ==> abc123
 * 2 ==> xyz456
 *
 * 盐加密(混淆字符)：源密码+盐 ==> md5 ==> 多次md5加密
 *
 * 问题：登陆时如何判断用户的密码是否正确？
 *      将用户提交的明文密码再次加密和数据库进行比对即可，加密比对。
 */
public class MD5Util {

    public static String getMD5(String source){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
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
        System.out.println(MD5Util.getMD5(source));
        System.out.println(MD5Util.getMD5(source));
        String salt = "power";
        System.out.println(MD5Util.getMD5(source + salt));
        System.out.println(MD5Util.getMD5(MD5Util.getMD5(source + salt)));
    }
}
