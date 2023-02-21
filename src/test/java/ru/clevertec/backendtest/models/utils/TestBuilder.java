package ru.clevertec.backendtest.models.utils;

@FunctionalInterface
public interface TestBuilder<T> {
    T build();
}
