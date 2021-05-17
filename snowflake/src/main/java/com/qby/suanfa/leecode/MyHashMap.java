package com.qby.suanfa.leecode;


import java.util.Iterator;
import java.util.LinkedList;

public class MyHashMap {

    private LinkedList<Node>[] linkedLists;
    private int size = 10;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        linkedLists = new LinkedList[size];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int i = getHashCode(key);
        if (linkedLists[i] == null) {
            linkedLists[i] = new LinkedList();
        }
        Node obj = getObj(key);
        if (obj != null) {
            obj.setVal(value);
        } else {
            linkedLists[i].add(new Node(key, value));
        }
    }

    private Node getObj(int key) {
        int i = getHashCode(key);

        if (linkedLists[i] != null) {
            Iterator<Node> iterator = linkedLists[i].iterator();
            while (iterator.hasNext()) {
                Node next = iterator.next();
                Integer key1 = next.getKey();
                if (key1.intValue() == key) {
                    return next;
                }
            }
        }
        return null;
    }


    /**
     * Returns the value to which the specified key is mapped,
     * or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int i = getHashCode(key);
        Node obj = getObj(key);
        if (obj != null) {
            return obj.getVal();
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value
     * key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int i = getHashCode(key);
        if (linkedLists[i] != null) {
            Node node = getObj(key);
            linkedLists[i].remove(node);
            if (linkedLists[i].isEmpty()) {
                linkedLists[i] = null;
            }
        }
    }

    // 编写散列函数, 使用一个简单取模法
    public int getHashCode(int key) {
        return key % size;
    }

//    public static void main(String[] args) {
//        MyHashMap myHashMap = new MyHashMap();
//        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
//        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
//        myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
//        myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
//        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
//        myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
//        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
//        myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
//    }
}

class Node {
    private int key;
    private int val;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
