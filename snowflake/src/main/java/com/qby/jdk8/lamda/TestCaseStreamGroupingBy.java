package com.qby.jdk8.lamda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据分组是一种更自然的分割数据操作，与将数据分成 ture 和 false 两部分不同，
 * 可以使用任意值对数据分组。Collectors.groupingBy接收一个Function做转换。
 */
public class TestCaseStreamGroupingBy {
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
        Map<SpecialityEnum, List<Student>> listMap =
                students1.stream().collect(
                        Collectors.groupingBy(student -> student.getSpecialities().get(0)));

        System.out.println(listMap);
    }
}
