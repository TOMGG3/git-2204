package com.bjpowernode.bean;

public class Orders {

    private String orderNum;
    private Double totalPrice;
    /**
     * 关系属性
     */
    private Customer customer;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderNum='" + orderNum + '\'' +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                '}';
    }
}
