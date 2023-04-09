package org.example.practice1;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Practice1 {
    /**
     * Метод находит последнюю позицию наименьшего
     * положительного элемента
     * @param arr массив, в котором выполняется поиск
     * @return позиция наименьшего положительного элемента
     */
    public static int findPositionOfLastLeastElement(int[] arr) {
        int result = -1;
        int minPositive = Integer.MAX_VALUE;

        // Ищем минимальный позитивный элемент
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 1 && arr[i] <= minPositive) {
                minPositive = arr[i];
                result = i;
            }
        }

        return result;
    }

    /**
     * Метод находит в строке первый символ, который
     * встречается во второй строке.
     * @param object строка, в которой выполняется поиск
     * @param subject строка, символ которой должен встречаться в первой
     * @return символ из первой строки
     */
    @Nullable
    public static Character firstSymbolOf(@NotNull String object, @NotNull String subject) {
        // Пробегаем в цикле по первой строке
        for (char c : object.toCharArray()) {
            // Если встречаем символ из второй - возвращаем его
            if (subject.indexOf(c) != -1) {
                return c;
            }
        }

        // Возвращаем null если ничего не нашли
        return null;
    }
}
