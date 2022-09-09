package com.bjpowernode.bean;

public class Dog {

    private String name;

    public Dog() {
        System.out.println("Dog对象的构造方法...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Dog对象的setName方法...");
        this.name = name;
    }

    /**
     * 对象初始化方法
     */
    public void init(){
        System.out.println("Dog对象初始化方法....");
    }

    /**
     * 对象销毁方法
     */
    public void destroy(){
        System.out.println("Dog对象销毁方法....");
    }
}
