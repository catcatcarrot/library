package com.zy.library.utils;

import com.zy.library.entity.Book;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<String, Book> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public Book get(String key) {
        return super.getOrDefault(key, null);
    }

    public Book put(String key, Book value) {
        super.put(key, value);
        return value;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Book> eldest) {
        return size() > capacity;
    }
}
