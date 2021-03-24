package com.qby.suanfa.redis;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 使用 jdk LinkedHashMap实现LRU
 * @param <K>
 * @param <V>
 */
public class LRUCacheDemo<K, V> extends LinkedHashMap<K, V> {
    private int capacity;//缓存坑位

    public LRUCacheDemo(int capacity) {
        // accessOrder – the ordering mode - true for access-order, false for insertion-order
        // LRU 要用 访问顺序 true
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    /**
     * [1, 2, 3]
     * [2, 3, 4]
     * [2, 3, 4]
     * [2, 3, 4]
     * [2, 3, 4]
     * [3, 4, 5]
     *
     * @param args
     */
    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);

        lruCacheDemo.put(1, "a");
        lruCacheDemo.put(2, "b");
        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(4, "d");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(5, "x");
        System.out.println(lruCacheDemo.keySet());
    }
}
/**
 * true
 * [1, 2, 3]
 * [2, 3, 4]
 * [2, 4, 3]
 * [2, 4, 3]
 * [2, 4, 3]
 * [4, 3, 5]
 * */

/**
 [1, 2, 3]
 [2, 3, 4]
 [2, 3, 4]
 [2, 3, 4]
 [2, 3, 4]
 [3, 4, 5]
 */
