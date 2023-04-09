package org.example.practice3.queue;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class QueueUtils {

    /**
     * Метод создаёт и инициализирует новую очередь
     * с помощью передаваемой лямбды
     * @param size размер очереди
     * @param func функция для инициализации очереди
     * @return новая очередь
     */
    @NotNull
    public static <T> Queue<T> init(int size, @NotNull Function<Integer, T> func) {
        Queue<T> stack = new Queue<>();

        for (int i = 0; i < size; i++) {
            stack.put(func.apply(i));
        }

        return stack;
    }
}
