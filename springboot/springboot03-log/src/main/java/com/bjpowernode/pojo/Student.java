package com.bjpowernode.pojo;

import lombok.*;

//@Setter
//@Getter
//@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private String address;
    private Double score;

    public static void main(String[] args) {
        Student student = new Student();
        student.setId(10);
        student.setName("小明");
        student.setAddress("北京");
        student.setScore(98.5);
        System.out.println(student);
        System.out.println(student.getName());

        Student student2 = new Student(11, "小红", "上海", 100.0);
        System.out.println(student2);
    }
}
