package org.example.practice3.queue;

import org.example.BinaryLinkedNode;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Класс, представляющий собой очередь, основанную на
 * двусвязном списке.
 * @param <T> - тип элемента в очереди
 */
public final class Queue<T> implements Iterable<T> {
    private BinaryLinkedNode<T> bottom = null;
    private BinaryLinkedNode<T> top = null;

    private int size;

    public int getSize() {
        return size;
    }

    /**
     * Метод добавляет элемент в конец очереди
     * @param item - добавляемый элемент
     */
    public void put(@NotNull T item) {
        BinaryLinkedNode<T> newItem = new BinaryLinkedNode<>(item);
        size++;

        // Если первого элемента не существует -
        // его нужно задать
        if (bottom == null) {
            bottom = newItem;
            top = newItem;

            return;
        }

        // Если top и bottom указывают на один
        // элемент, то нужно сменить ссылку у top на
        // новый элемент
        if (top == bottom) {
            top = newItem;
            top.setPrevious(bottom);
            bottom.setNext(top);

            return;
        }

        // Передвигаем верхний указатель
        top.setNext(newItem);
        newItem.setPrevious(top);
        top = newItem;
    }

    /**
     * Метод возвращает первое значение в очереди и удаляет его
     */
    @NotNull
    public T pop() {
        // Забираем значение у первой ноды
        T item = bottom.getValue();
        assert item != null : "Queue is empty";

        // Удаляем первую ноду
        BinaryLinkedNode<T> next = bottom.getNext();
        bottom.setNext(null);

        if (next != null) next.setPrevious(null);
        bottom = next;

        if (next == null) {
            top = null;
        }

        size--;

        return item;
    }

    @Override
    @NotNull
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
    @Override
    public Iterator<T> iterator() {
        return new QueueIterator<>(bottom);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        BinaryLinkedNode<T> current = bottom;

        while (current != null) {
            action.accept(current.getValue());
            current = current.getNext();
        }
    }
}
