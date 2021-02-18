package com.qby.jdk8.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字符串拼接
 *
 * 如果将所有学生的名字拼接起来，怎么做呢？通常只能创建一个StringBuilder，
 * 循环拼接。使用Stream，使用Collectors.joining()简单容易。
 */
public class TestCaseStreamJoining {
    public static void main(String[] args) {
        List<Student> students1 = new ArrayList<>(3);
        List<SpecialityEnum> list = new ArrayList<>();
        list.add(SpecialityEnum.SING);
        students1.add(new Student("路飞", 23, 175, list));

        list = new ArrayList<>();
        list.add(SpecialityEnum.DANCE);
        students1.add(new Student("红发", 40, 180, list));

        list = new ArrayList<>();
        list.add(SpecialityEnum.SWIMMING);
        students1.add(new Student("白胡子", 50, 185, list));
        String names = students1.stream()
                .map(Student::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println(names);

        System.out.println(students1.stream().map(Student::getName).collect(Collectors.joining(",", "", "")));

    }
}
