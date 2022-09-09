package com.bjpowernode.crm.commons.utils;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取主键值的方法
     * UUID全球唯一识别码，能够生成一个32位不重复的字符串
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtil.getUUID());
    }
}
