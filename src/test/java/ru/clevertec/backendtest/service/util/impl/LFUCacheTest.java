package ru.clevertec.backendtest.service.util.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.clevertec.backendtest.service.util.cache.impl.LFUCache;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LFUCacheTest {
    private LFUCache<Integer, Object> lfuCache;

    @BeforeEach
    public void init() {
        lfuCache = new LFUCache<>(5);
        for (int i = 0; i < 10; i++) {
            lfuCache.put(i, String.valueOf(i));
            for (int j = 0; j < i / 2; j++) {
                lfuCache.get(i);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("putProvider")
    void putShouldReturnValidResult(Integer key, String value, String expected) {
        assertThat(lfuCache.put(key, value)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getProvider")
    void getShouldReturnValidResult(Integer key, String expectedResult) {
        assertThat(lfuCache.get(key)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("deleteProvider")
    void deleteShouldReturnNullAfterGet(Integer key) {
        lfuCache.delete(key);
        assertThat(lfuCache.get(key)).isNull();
    }

    private static Stream<Arguments> putProvider() {
        return Stream.of(
                Arguments.of(1, "1", null),
                Arguments.of(9, "9", "9"),
                Arguments.of(10, "10", null),
                Arguments.of(1, "2", null),
                Arguments.of(10, "10", null),
                Arguments.of(5, "1123", "1123")
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