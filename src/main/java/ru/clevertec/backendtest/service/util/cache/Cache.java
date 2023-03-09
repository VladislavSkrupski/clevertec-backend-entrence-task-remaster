package ru.clevertec.backendtest.service.util.cache;

/**
 * Interface defining methods for cache classes
 *
 * @param <K> unique key of entity
 * @param <V> entity, that will be cached
 */
public interface Cache<K, V> {
    /**
     * Put entity in cache
     *
     * @param key   unique key of entity
     * @param value entity
     * @return the entity put in cache if it presents, else return null
     */
    V put(K key, V value);

    /**
     * Get entity from cache
     *
     * @param key unique key of entity
     * @return the entity from cache if it presents, else return null
     */
    V get(K key);

    /**
     * Delete entity from cache
     *
     * @param key unique key of entity
     */
    void delete(K key);
}
