package ru.clevertec.backendtest.service.util.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.clevertec.backendtest.service.util.cache.impl.LRUCache;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LRUCacheTest {
    private LRUCache<Integer, Object> lruCache;

    @BeforeEach
    public void init() {
        lruCache = new LRUCache<>(5);
        for (int i = 0; i < 10; i++) {
            lruCache.put(i, String.valueOf(i));
        }
    }

    @ParameterizedTest
    @MethodSource("putProvider")
    void putShouldReturnValidResult(Integer key, String value, String expectedResult) {
        assertThat(lruCache.put(key, value)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("getProvider")
    void getShouldReturnValidResult(Integer key, String expectedResult) {
        assertThat(lruCache.get(key)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("deleteProvider")
    void deleteShouldReturnNullAfterGet(Integer key) {
        lruCache.delete(key);
        assertThat(lruCache.get(key)).isNull();
    }

    private static Stream<Arguments> putProvider() {
        return Stream.of(
                Arguments.of(1, "1", null),
                Arguments.of(9, "9", "9"),
                Arguments.of(10, "10", null),
                Arguments.of(1, "2", null),
                Arguments.of(10, "10", null),
                Arguments.of(5, "1123", "5")
        );
    }

    private static Stream<Arguments> getProvider() {
        return Stream.of(
                Arguments.of(1, null),
                Arguments.of(9, "9"),
                Arguments.of(10, null),
                Arguments.of(1, null),
                Arguments.of(5, "5"),
                Arguments.of(4, null)
        );
    }

    private static Stream<Arguments> deleteProvider() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(9),
                Arguments.of(10),
                Arguments.of(1),
                Arguments.of(5),
                Arguments.of(4)
        );
    }
}