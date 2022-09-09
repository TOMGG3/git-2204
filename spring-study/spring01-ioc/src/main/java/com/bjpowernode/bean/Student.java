package com.bjpowernode.bean;

import java.util.*;

public class Student {

    private String name;
    private Integer age;
    private Double score;
    private Date birthday;
    private String[] courseName;
    private List<String> list;
    private Set<Integer> set;
    private Map<String, Double> map;
    private Properties props;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setCourseName(String[] courseName) {
        this.courseName = courseName;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<Integer> set) {
        this.set = set;
    }

    public void setMap(Map<String, Double> map) {
        this.map = map;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", birthday=" + birthday +
                ", courseName=" + Arrays.toString(courseName) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", props=" + props +
                '}';
    }
}
