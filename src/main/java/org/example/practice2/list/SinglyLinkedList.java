package org.example.practice2.list;

import org.example.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Класс представляет собой односвязный список
 *
 * @param <T> - тип хранимого значения
 */
public final class SinglyLinkedList<T> implements Iterable<T> {
    private Node<T> _head = null;
    private Node<T> tail = null;
    private int size = 0;

    /**
     * Метод выполняет поиск элемента, который содержит value.
     * Лямбда должна возвращать элемент, по которому выполняется поиск.
     * Если элемент найти не удалось, возвращается null.
     *
     * @param value значение, элемент с которым необходимо найти
     * @param f     функция для получения элемента, по которому выполняется поиск
     */
    @Nullable
    public <E> T getByOrNull(@NotNull E value, @NotNull Function<T, E> f) {
        Node<T> current = _head;

        // Пробегаемся с начала списка до конца
        while (current != null) {
            // Получаем элемент, по которому идёт сравнение
            E item = f.apply(current.value);

            if (value.equals(item)) {
                return current.value;
            }

            current = current.getNext();
        }

        return null;
    }

    public static void main(String[] args) {
        int count = 10;
        SinglyLinkedList<Furniture> list = new SinglyLinkedList<>();
        for (int i = 1; i <= count; i++) {
            final String partNumber = UUID.randomUUID().toString();
            Furniture furniture = new Furniture(partNumber.substring(0, Math.min(8, partNumber.length())), "title " + i,
                    new Random().nextFloat(), new Date(), i * ThreadLocalRandom.current().nextInt(5, 100));

            list.add(furniture);
        }

        Furniture f = list.getByOrNull("title 3", Furniture::getTitle);
        System.out.println(f);
    }

    /**
     * Метод выполняет сортировку, получаемую с помощью переданной лямбды.
     *
     * @param f функция для получения элемента, по которому выполняется сортировка
     */
    public <E extends Comparable<E>> void sortBy(@NotNull Function<T, E> f) {
        _head = ListUtils.sortListByMerge(_head, f);
    }

    /**
     * Метод добавляет элемент в конец списка
     */
    public void add(@NotNull T item) {
        size++;
        Node<T> newItem = new Node<>(item);

        // Создаём ссылку на начало, если список пуст
        if (_head == null) {
            _head = newItem;
            tail = newItem;
            return;
        }

        // Передвигаем ссылку на конец
        tail.setNext(newItem);
        tail = newItem;
    }

    /**
     * Метод проверяет содержание элемента в списке
     * @param item - проверяемый элемент
     * @return содержится ли элемент в списке
     */
    public boolean contains(@NotNull T item) {
        Node<T> current = _head;

        while (current != null) {
            if (item.equals(current.value)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    @Override
    @NotNull
    public String toString() {
        if (_head == null) return "[]";

        StringBuilder sb = new StringBuilder()
                .append('[');

        Node<T> current = _head;

        while (current != null) {
            if (current.getNext() == null) {
                sb.append(current)
                        .append("]");
            } else {
                sb.append(current)
                        .append(", ");
            }

            current = current.getNext();
        }

        return sb.toString();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator<>(_head);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> current = _head;

        while (current != null) {
            action.accept(current.value);
            current = current.getNext();
        }
    }

    public int getSize() {
        return size;
    }
}