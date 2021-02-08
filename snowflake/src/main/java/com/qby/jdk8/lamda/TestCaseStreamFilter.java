package com.qby.jdk8.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCaseStreamFilter {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));
        List<Student> list = students.stream()
                .filter(stu -> stu.getStature() < 180)
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
