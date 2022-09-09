package com.bjpowernode.factory;

import com.bjpowernode.bean.Cat;

public class CatInstanceFactory {

    public Cat getInstance(){
        return new Cat();
    }

    public static void main(String[] args) {
        CatInstanceFactory factory = new CatInstanceFactory();
        Cat c = factory.getInstance();
    }
}
