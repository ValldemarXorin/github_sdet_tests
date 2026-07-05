package com.github_tst_sdet.configuration.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class TestDataCache<K, V> {

    private final Cache<K, V> cache;

    public TestDataCache(long maxSize, long expireMinutes) {
        this.cache = Caffeine.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(expireMinutes, TimeUnit.MINUTES)
                .build();
    }

    public boolean contains(K key) {
        return cache.asMap().containsKey(key);
    }

    public V get(K key) {
        return cache.getIfPresent(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }
}