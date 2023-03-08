package ru.clevertec.backendtest.util;

@FunctionalInterface
public interface TestBuilder<T> {
    T build();
}
