package org.example;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Класс представляет собой контейнер для одного элемента
 * двусвязного списка
 * @param <T> - тип значения
 */
public final class BinaryLinkedNode<T> {
    private final T value;
    private BinaryLinkedNode<T> next;
    private BinaryLinkedNode<T> previous;

    public BinaryLinkedNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Nullable
    public BinaryLinkedNode<T> getNext() {
        return next;
    }

    public void setNext(@Nullable BinaryLinkedNode<T> next) {
        this.next = next;
    }

    @Nullable
    public BinaryLinkedNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(@Nullable BinaryLinkedNode<T> previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryLinkedNode<?> that = (BinaryLinkedNode<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
