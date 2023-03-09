package ru.clevertec.backendtest.service.util.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.clevertec.backendtest.service.util.cache.impl.LFUCache;
import ru.clevertec.backendtest.service.util.cache.impl.LRUCache;

/**
 * Cache factory, that provides method to create cache based on <b>application.yml</b> file
 */
@Component
public class CacheFactory {
    @Value("${cache.size}")
    private int size;
    @Value("${cache.type}")
    private String type;

    /**
     * Create new cache, based on <b>application.yml</b> file
     *
     * @return new cache object or throw {@link RuntimeException} if cache type is wrong
     */
    public Cache<Integer, Object> createCache() {
        return switch (type.toLowerCase()) {
            case "lru" -> new LRUCache<>(size);
            case "lfu" -> new LFUCache<>(size);
            default -> throw new RuntimeException("There is no such cache type");
        };
    }
}
