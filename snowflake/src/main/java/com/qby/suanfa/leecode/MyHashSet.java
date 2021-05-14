package com.qby.suanfa.leecode;

import java.util.LinkedList;

/**
 * 705. 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * <p>
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 * <p>
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 */
public class MyHashSet {
    private LinkedList<Integer>[] linkedLists;
    private int size = 10;


    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        linkedLists = new LinkedList[size];
    }

    public void add(int key) {
        int sz = getHashCode(key);
        if (linkedLists[sz] == null) {
            linkedLists[sz] = new LinkedList<>();
        }
        if (contains(key)) {
            return;
        }
        linkedLists[sz].add(key);
    }

    public void remove(int key) {
        int sz = getHashCode(key);
        if (linkedLists[sz] == null) {
            return;
        }
        for (int i = 0; i < linkedLists[sz].size(); i++) {
            if (linkedLists[sz].get(i) == key) {
                linkedLists[sz].remove(i);
            }
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int sz = getHashCode(key);
        if (linkedLists[sz] == null) {
            return false;
        }
        return linkedLists[sz].contains(key);
    }

    //编写散列函数, 使用一个简单取模法
    public int getHashCode(int key) {
        return key % size;
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // 返回 True
        myHashSet.contains(3); // 返回 False ，（未找到）
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // 返回 True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2); // 返回 False ，（已移除）
    }
}
