package org.example.practice2.stack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Класс представляет собой стек на основе массива
 */
public final class ArrayStack<T> implements Stack<T> {
    private static final int DEFAULT_CONTAINER_SIZE = 16;
    private int top;
    private @Nullable T[] container;

    @Override
    public int getSize() {
        return top;
    }

    private ArrayStack(T[] arr) {
        container = arr;
    }

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        container = (T[])new Object[DEFAULT_CONTAINER_SIZE];
    }

    /**
     * Метод добавляет элемент на вершину стека
     */
    @Override
    public void put(@NotNull T item) {
        // Если размер массива становится слишком маленьким,
        // создаём новый массив и копируем содержимое старого
        if (top == container.length - 1) {
            container = Arrays.copyOf(container, container.length << 1);
        }

        // Добавляем элемент на вершину стека
        container[top++] = item;
    }

    /**
     * Метод возвращает и удаляет элемент с вершины стека
     */
    @Override
    @NotNull
    public T pop() {
        // Забираем последний элемент из массива
        T item = container[--top];

        // Удаляем взятый элемент из массива
        if (top + 1 < container.length) {
            container[top + 1] = null;
        }

        assert item != null : "Stack is empty";

        return item;
    }

    @Override
    public String toString() {
        if (container.length == 0) return "[]";

        StringBuilder sb = new StringBuilder().append('[');
        for (int i = 0; i < container.length; i++) {
            if (i == container.length - 1) {
                sb.append(container[i]).append(']');
            } else {
                sb.append(container[i]).append(", ");
            }
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayStack<T> init(int size, Function<Integer, T> func) {
        // Создаём пустой массив Object'ов
        Object[] arr = new Object[size];

        // Заполняем его данными, полученными с помощью
        // передаваемой функции
        for (int i = 0; i < size; i++) {
            arr[i] = func.apply(i);
        }

        // Создаём и возвращаем стек
        ArrayStack<T> res = new ArrayStack<>((T[])arr);
        res.top = size;

        return res;
    }
}
