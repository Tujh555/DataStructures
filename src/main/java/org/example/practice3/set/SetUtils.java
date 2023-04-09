package org.example.practice3.set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.plaf.PanelUI;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Утилитарный класс, содержащий основные операции с множествами
 */
class SetUtils {
    /**
     * Метод создаёт множество из элементов коллекции
     * @param iterable коллекция, из элементов которой создаётся множество
     */
    public static <T> @NotNull MySet<T> from(@NotNull Iterable<? extends T> iterable) {
        MySet<T> res = new MySet<>();

        for (T item : iterable) {
            res.add(item);
        }

        return res;
    }

    /**
     * Метод создаёт множество из нескольких элементов
     * @param elements элементы нового множество
     */
    @SafeVarargs
    public static <T> @NotNull MySet<T> from(T... elements) {
        MySet<T> res = new MySet<>();

        res.addAll(Arrays.asList(elements));

        return res;
    }

    /**
     * Метод находит разность множеств. Возвращает множество,
     * все элементы которого содержатся в первом множестве,
     * но не содержатся во втором.
     * @param reducing уменьшаемое множество
     * @param subtracted вычитаемое множество
     * @return разность двух множеств
     * @param <T> тип хранимых данных
     */
    @NotNull
    public static <T> MySet<T> div(@NotNull Set<? extends T> reducing, @NotNull Set<? extends T> subtracted) {
        MySet<T> res = new MySet<>();

        // Добавляем элементы первого массива,
        // если они не содержатся во втором
        for (T item : reducing) {
            if (!subtracted.contains(item)) {
                res.add(item);
            }
        }

        return res;
    }

    /**
     * Метод проверяет на равенство 2 множества
     * @return равенство двух множеств
     */
    public static boolean isEquals(@Nullable Set<?> first, @Nullable Set<?> second) {
        if (first == null && second == null) return true;

        if (first == null || second == null) return false;

        if (first.size() != second.size()) return false;

        // Проверяем содержание элементов первого массива
        // во втором
        for (Object item : first) {
            if (!second.contains(item)) {
                return false;
            }
        }

        return true;
    }

    @SafeVarargs
    @NotNull
    public static <T> MySet<T> union(@NotNull Set<? extends T>... sets) {
        return union(Arrays.asList(sets));
    }

    /**
     * Метод находит объединение всех множеств в коллекции
     * @param sets коллекция множеств
     * @return объединение множеств
     * @param <T> тип хранимых данных
     */
    @NotNull
    public static <T> MySet<T> union(@NotNull Collection<? extends Set<? extends T>> sets) {
        MySet<T> res = new MySet<>();
        for (Set<? extends T> set : sets) {
            res.addAll(set);
        }

        return res;
    }

    @SafeVarargs
    @NotNull
    public static <T> MySet<T> intersection(@NotNull Set<? extends T>... sets) {
        return intersection(Arrays.asList(sets));
    }

    /**
     * Метод находит пересечение всех множеств в коллекции
     * @param sets коллекция множеств
     * @return пересечение всех множеств
     * @param <T> тип хранимого элемента
     */
    @NotNull
    public static <T> MySet<T> intersection(@NotNull Collection<? extends Set<? extends T>> sets) {
        // Если входная коллекция пуста, возвращаем пустой Set
        if (sets.size() == 0) {
            return new MySet<>();
        }

        // Если в коллекции 1 элемент, возвращаем его
        if (sets.size() == 1) {
            for (Set<? extends T> set : sets) {
                return from(set);
            }
        }

        MySet<T> res = new MySet<>();

        // Ищем минимальный по длине Set с помощью java StreamApi
        sets.stream()
                .min(Comparator.comparingInt(Set::size))
                .ifPresent(minSet -> {
                    // Пробегаем в цикле по всем элементам самого короткого
                    // множества и проверяем его элементы на предмет
                    // содержания в остальных множествах
                    for (T item : minSet) {
                        boolean isCotainedAll = true;

                        for (Set<? extends T> currentSet : sets) {
                            if (!currentSet.contains(item)) {
                                isCotainedAll = false;
                                break;
                            }
                        }

                        if (isCotainedAll) {
                            res.add(item);
                        }
                    }
                });

        return res;
    }

    public static class Fruit {
        public void fruitMethod() {
            System.out.println(this.getClass().getSimpleName());
        }
    }

    public static class Citrus extends Fruit {
        public void citrusMethod() {}
    }

    public static class Orange extends Citrus {
        public void orangeMethod() {}
    }

    public static class BigOrange extends Orange {
        public void bigOrangeMethod() {}
    }

    public static void main(String[] args) {

    }
}