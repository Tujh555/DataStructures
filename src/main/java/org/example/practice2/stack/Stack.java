package org.example.practice2.stack;

import org.jetbrains.annotations.NotNull;

public interface Stack<T> {
    int getSize();

    /**
     * Метод добавляет элемент на вершину стека
     */
    void put(@NotNull T item);

    /**
     * Метод возвращает и удаляет элемент с вершины стека
     */
    @NotNull
    T pop();
}
