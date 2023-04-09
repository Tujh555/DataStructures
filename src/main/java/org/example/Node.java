package org.example;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Класс представляет собой контейнер для какого либо значения
 *
 * @param <T> - тип значения для контейнера
 */
public final class Node<T> {
    public final T value;
    private Node<T> next = null;

    public Node(T value) {
        this.value = value;
    }

    @Nullable
    public Node<T> getNext() {
        return next;
    }

    public void setNext(@Nullable Node<T> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return value.equals(node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
