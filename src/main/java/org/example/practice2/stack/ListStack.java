package org.example.practice2.stack;

import org.example.BinaryLinkedNode;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * Класс представляет собой стек на основе двусвязного списка
 */
public final class ListStack<T> implements Stack<T> {
    private BinaryLinkedNode<T> bottom = null;
    private BinaryLinkedNode<T> top = null;

    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void put(@NotNull T item) {
        BinaryLinkedNode<T> newItem = new BinaryLinkedNode<>(item);
        size++;

        if (bottom == null) {
            bottom = newItem;
            top = newItem;

            return;
        }

        if (top == bottom) {
            top = newItem;
            top.setPrevious(bottom);
            bottom.setNext(top);

            return;
        }

        top.setNext(newItem);
        newItem.setPrevious(top);
        top = newItem;
    }

    @Override
    @NotNull
    public T pop() {
        assert top != null : "Stack was empty";

        T item = top.getValue();

        BinaryLinkedNode<T> prev = top.getPrevious();

        top.setPrevious(null);
        if (prev != null) prev.setNext(null);

        top = prev;

        if (prev == null) {
            bottom = null;
        }

        size--;

        return item;
    }

    @Override
    public String toString() {
        if (bottom == null) return "[]";

        StringBuilder sb = new StringBuilder().append('[');

        BinaryLinkedNode<T> current = bottom;

        while (current != null) {
            if (current.getNext() == null) {
                sb.append(current).append(']');
            } else {
                sb.append(current).append(", ");
            }

            current = current.getNext();
        }

        return sb.toString();
    }

    @NotNull
    public static <T> ListStack<T> init(int size, Function<Integer, T> func) {
        ListStack<T> stack = new ListStack<>();

        for (int i = 0; i < size; i++) {
            stack.put(func.apply(i));
        }

        return stack;
    }
}
