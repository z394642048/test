package com.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现一个简单版的LRU算法：
 * LinkedHashMap底层就是用的【HashMap 加 双链表】实现的，而且本身已经实现了按照访问顺序的存储。
 * 此外，LinkedHashMap中本身就实现了一个方法removeEldestEntry用于判断是否需要移除最不常读取的数，
 * 方法默认是直接返回false，不会移除元素，所以需要重写该方法,即当缓存满后就移除最不常用的数。
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    /**
     * 缓存大小
     */
    private final int CACHE_SIZE;

    /**
     * 当accessOrder设置为false时，会按照插入顺序进行排序，
     * 当accessOrder为true时，会按照访问顺序
     *
     * @param cacheSize
     */
    public LRUCache(int cacheSize) {
        //修改accessOrder属性值为true
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    /**
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        // 当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
        return size() > CACHE_SIZE;
    }

}