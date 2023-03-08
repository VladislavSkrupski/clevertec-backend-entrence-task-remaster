package ru.clevertec.backendtest.service.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Least Recently Used (LRU) cache algorithm that monitor time when a value has been accessed.
 * When the cache is overflow system will remove value with the least recently usage.
 *
 * @param <K> unique key of entity
 * @param <V> entity, that will be cached
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {
    private int maxSize;

    public LRUCache(int maxSize) {
        super(Math.max(maxSize, 1), 1f, true);
        this.maxSize = Math.max(maxSize, 1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > maxSize;
    }

    /**
     * Put entity in cache
     *
     * @param key   unique key of entity
     * @param value entity
     * @return the entity put in cache if it presents, else return null
     */
    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }

    /**
     * Get entity from cache
     *
     * @param key unique key of entity
     * @return the entity from cache if it presents, else return null
     */
    @Override
    public V get(Object key) {
        return super.get(key);
    }

    /**
     * Delete entity from cache
     *
     * @param key unique key of entity
     */
    @Override
    public void delete(K key) {
        remove(key);
    }
}
