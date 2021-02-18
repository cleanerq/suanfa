package com.qby.jdk8.lamda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 我们经常会在集合中求最大或最小值，使用流就很方便。及早求值。
 */
public class TestCaseStreamMaxMin {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(3);
        students.add(new Student("路飞", 22, 175));
        students.add(new Student("红发", 40, 180));
        students.add(new Student("白胡子", 50, 185));
        Optional<Student> max = students.stream()
                .max(Comparator.comparing(stu -> stu.getAge()));
        Optional<Student> min = students.stream()
                .min(Comparator.comparing(stu -> stu.getAge()));

        // 如果为空值则新建对象
        Student student = max.orElse(new Student());
        max.orElseGet(() ->new Student());

        //判断是否有值
        if (max.isPresent()) {
            System.out.println(max.get());
        }
        if (min.isPresent()) {
            System.out.println(min.get());
        }
    }
}
