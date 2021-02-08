package com.qby.jdk8.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestCaseStreamPartitioning {
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

        //省略List<student> students的初始化
        Map<Boolean, List<Student>> listMap = students1.stream().collect(
                Collectors.partitioningBy(student -> student.getSpecialities().
                        contains(SpecialityEnum.SING)));
        System.out.println(listMap);

        System.out.println(listMap.get(Boolean.TRUE));
        System.out.println(listMap.get(Boolean.FALSE));
    }
}
