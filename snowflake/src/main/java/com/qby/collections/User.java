package com.qby.collections;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class User implements Comparable<User> {
    private String name;

    // 省略getter、setter、构造方法
    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(User o) {
        return this.getName().compareTo(o.getName());
    }


    public static void fillMapByString(Map<String, String> map) {
        map.put("33", "33");
        map.put("aa", "aa");
        map.put("gg", "gg");
        map.put("dd", "dd");
        map.put("11", "11");
        map.put("ee", "ee");
        map.put("aa", "aaaa");
    }

    public static void fillMapByUser(Map<User, String> map) {
        map.put(new User("33"), "33");
        map.put(new User("aa"), "aa");
        map.put(new User("gg"), "gg");
        map.put(new User("dd"), "dd");
        map.put(new User("11"), "11");
        map.put(new User("ee"), "ee");
        map.put(new User("aa"), "aaaa");
    }

    public static void fillSetByString(Set<String> set) {
        set.add("33");
        set.add("aa");
        set.add("gg");
        set.add("dd");
        set.add("11");
        set.add("ee");
        set.add("aa");
    }

    public static void fillSetByUser(Set<User> set) {
        set.add(new User("33"));
        set.add(new User("aa"));
        set.add(new User("gg"));
        set.add(new User("dd"));
        set.add(new User("11"));
        set.add(new User("ee"));
        set.add(new User("aa"));
    }

    public static void testTreeMap1() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        fillMapByString(map);

        System.out.println("===============testTreeMap1===================");
        System.out.println(map.size() + "个元素：");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void testTreeMap2() {
        TreeMap<User, String> map = new TreeMap<User, String>();
        fillMapByUser(map);

        System.out.println("===============testTreeMap2===================");
        System.out.println(map.size() + "个元素：");
        for (Map.Entry<User, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void testTreeSet1() {
        TreeSet<String> set = new TreeSet<String>();
        fillSetByString(set);

        System.out.println("===============testTreeSet1===================");
        System.out.println(set.size() + "个元素：");
        for (String s : set) {
            System.out.println(s);
        }
    }

    public static void testTreeSet2() {
        TreeSet<User> set = new TreeSet<User>();
        fillSetByUser(set);

        System.out.println("===============testTreeSet2===================");
        System.out.println(set.size() + "个元素：");
        for (User user : set) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        testTreeMap2();
    }
}