package ru.clevertec.backendtest.utils;

@FunctionalInterface
public interface TestBuilder<T> {
    T build();
}
