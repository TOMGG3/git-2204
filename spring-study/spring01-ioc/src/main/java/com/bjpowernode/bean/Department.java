package com.bjpowernode.bean;

public class Department {

    private Integer id;
    private String dname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", dname='" + dname + '\'' +
                '}';
    }
}
