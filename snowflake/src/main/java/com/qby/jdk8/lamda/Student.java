package com.qby.jdk8.lamda;

import java.util.List;

public class Student {
    private String name;
    private int age;
    private int stature;
    private List<SpecialityEnum> specialities;

    public Student() {
    }

    public Student(String name, int age, int stature) {
        this.name = name;
        this.age = age;
        this.stature = stature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStature() {
        return stature;
    }

    public void setStature(int stature) {
        this.stature = stature;
    }

    public List<SpecialityEnum> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<SpecialityEnum> specialities) {
        this.specialities = specialities;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", stature=" + stature +
                ", specialities=" + specialities +
                '}';
    }

    public Student(String name, int age, int stature, List<SpecialityEnum> specialities) {
        this.name = name;
        this.age = age;
        this.stature = stature;
        this.specialities = specialities;
    }
}
