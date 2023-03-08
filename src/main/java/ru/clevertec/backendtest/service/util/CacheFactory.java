package ru.clevertec.backendtest.service.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheFactory {
    @Value("${cache.size}")
    private static int size;
    @Value("${cache.type}")
    private static String type;

    static Cache<Integer, Object> createCache() {
        return switch (type.toLowerCase()) {
            case "lru" -> new LRUCache<>(size);
            case "lfu" -> new LFUCache<>(size);
            default -> throw new RuntimeException("There is no such cache type");
        };
    }
}
