package ru.clevertec.backendtest.service.util;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Least Frequently Used (LFU) cache algorithm that monitor the number of times a value has been accessed.
 * When the cache is overflow system will remove value with the lowest reference frequency.
 *
 * @param <K> unique key of entity
 * @param <V> entity, that will be cached
 */
public class LFUCache<K, V> implements Cache<K, V> {
    private int maxSize;
    private int min = -1;
    private Map<K, V> cacheValues;
    private Map<K, Integer> counts;
    private Map<Integer, LinkedHashSet<K>> keySets;

    public LFUCache(int maxSize) {
        this.maxSize = Math.max(maxSize, 1);
        cacheValues = new HashMap<>();
        counts = new HashMap<>();
        keySets = new HashMap<>();
        keySets.put(0, new LinkedHashSet<>());
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
        if (cacheValues.containsKey(key)) {
            cacheValues.put(key, value);
            return get(key);
        }
        if (cacheValues.size() >= maxSize) {
            K evict = keySets.get(min).iterator().next();
            keySets.get(min).remove(evict);
            cacheValues.remove(evict);
            counts.remove(evict);
        }
        counts.put(key, 0);
        min = 0;
        keySets.get(0).add(key);
        cacheValues.put(key, value);
        return get(key);
    }

    /**
     * Get entity from cache
     *
     * @param key unique key of entity
     * @return the entity from cache if it presents, else return null
     */
    @Override
    public V get(K key) {
        Integer count = 0;
        if (counts.containsKey(key)) {
            count = counts.get(key);
        }
        counts.put(key, count + 1);
        keySets.get(count).remove(key);
        if (count == min && keySets.get(count).size() == 0) {
            min++;
        }
        if (!keySets.containsKey(count + 1)) {
            keySets.put(count + 1, new LinkedHashSet<>());
        }
        keySets.get(count + 1).add(key);
        return cacheValues.get(key);
    }

    /**
     * Delete entity from cache
     *
     * @param key unique key of entity
     */
    @Override
    public void delete(K key) {
        cacheValues.remove(key);
        counts.remove(key);
        keySets.values().stream()
                .filter(keySet -> keySet.contains(key))
                .forEach(keySet -> keySet.remove(key));
    }
}
