package com.bjpowernode.factory;

import com.bjpowernode.bean.Cat;

/**
 * 静态工厂
 */
public class CatStaticFactory {

    public static Cat getInstance(){
        return new Cat();
    }
}
