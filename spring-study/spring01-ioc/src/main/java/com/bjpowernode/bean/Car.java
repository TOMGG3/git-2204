package com.bjpowernode.bean;

public class Car {

    /**
     * 成员变量
     */
    private String name;
    private String type;
    private Double price;
    private Integer doors;

    public Car(String name, String type, Double price, Integer doors) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", doors=" + doors +
                '}';
    }
}
