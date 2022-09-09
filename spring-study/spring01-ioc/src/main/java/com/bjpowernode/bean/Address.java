package com.bjpowernode.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("addr")
//标识当前Bean为主bean
// @Primary
public class Address {

    @Value("郑州市")
    private String city;
    @Value("金水区")
    private String area;
    @Value("民航路")
    private String street;

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
