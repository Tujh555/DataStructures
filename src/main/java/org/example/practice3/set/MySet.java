package org.example.practice3.set;

import org.example.practice2.list.SinglyLinkedList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Класс представляет собой множество на основе хеш-функции и массива
 * @param <T> - тип элемента множества
 */
public class MySet<T> extends AbstractSet<T> {
    // region Values
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.9f;
    private int size = 0;
    private final float loadFactor;
    private int capacity;
    private SinglyLinkedList<T>[] buckets;

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    // endregion

    @SafeVarargs
    private MySet(int initialCapacity, float loadFactor, SinglyLinkedList<T>... arr) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity should be greater than zero");
        }

        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Incorrect value for load factor: " + loadFactor);
        }

        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;

        buckets = Arrays.copyOf(arr, initialCapacity);
    }

    public MySet() { this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR); }

    /**
     * Метод проверяет, содержится ли элемент в множестве
     * @param o element whose presence in this set is to be tested
     * @return содержится ли элемент в множестве
     */
    @Override
    public boolean contains(Object o) {
        // Вычисляем позицию элемента на основе хэш-кода
        int index = hash(o);
        SinglyLinkedList<T> list = buckets[index];


        if (list == null) {
            return false;
        }

        // Делегируем проверку содержания элемента списку
        try {
            return list.contains((T)o);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Метод добавляет любую коллекцию в множество
     * @param c collection containing elements to be added to this collection
     * @return добавилась ли коллекция в множество
     */
    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        for (T item : c) {
            add(item);
        }

        return true;
    }

    /**
     * Метод добавляет элемент в множество
     * @param item element whose presence in this collection is to be ensured
     * @return был ли добавлен элемент
     */
    public boolean add(T item) {
        // Если размер массива становится больше порогового
        // значения, то увеличиваем размер корзины
        if (size > threshold()) {
            resize();
        }

        // Вычисляем индекс элемента в корзине на основе
        // хэш-кода
        int index = hash(item);
        if (buckets[index] == null) {
            buckets[index] = new SinglyLinkedList<>();
        }

        // Если элемента не было в корзине, то добавляем его
        SinglyLinkedList<T> current = buckets[index];
        if (!current.contains(item)) {
            current.add(item);
            size++;
            return true;
        }
        return false;
    }

    /**
     * Метод вычисляет пороговое значение, при достижении
     * которого требуется увеличить размер корзины
     * @return пороговое значение размера
     */
    private int threshold() {
        return (int)(loadFactor * capacity);
    }

    /**
     * Метод увеличивает размер корзины и переносит все
     * элементы в новую
     */
    private void resize() {
        // Увеличиваем ёмкость корзины на 2
        capacity <<= 1;

        SinglyLinkedList<T>[] oldBuckets = buckets;

        // Копируем старые элементы
        buckets = Arrays.copyOf(buckets, capacity);

        // Очищаем старую корзину
        Arrays.fill(buckets, null);
        for (SinglyLinkedList<T> list : oldBuckets) {
            // Если элемент список был найден в старой корзине,
            // то копируем все его элементы с новыми позициями
           if (list != null) {
               for (T item: list) {
                   int index = hash(item);

                   if (buckets[index] == null) {
                       buckets[index] = new SinglyLinkedList<>();
                   }

                   buckets[index].add(item);
               }
           }
        }
    }

    /**
     * Метод вычисляет позицию элемента на основе хэш-кода
     * с учетом текущего размера корзины
     * @param item - элемент, код которого нужно вычислить
     * @return позиция элемента в корзине
     */
    private int hash(@NotNull Object item) {
        int hashCode = item.hashCode();

        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        hashCode ^= (hashCode >>> 7) ^ (hashCode >>> 4);

        if (hashCode < 0) hashCode = -hashCode;
        return hashCode % capacity;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new SetIterator<>(buckets);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (SinglyLinkedList<T> list : buckets) {
            if (list != null) {
                for (T item : list) {
                    action.accept(item);
                }
            }
        }
    }

    @Override
    public String toString() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) return "{}";

        StringBuilder stringBuilder = new StringBuilder().append("{ ");

        while (iterator.hasNext()) {
            T current = iterator.next();

            if (iterator.hasNext()) {
                stringBuilder.append(current).append(", ");
            } else {
                stringBuilder.append(current).append(" }");
            }
        }

        return stringBuilder.toString();
    }

    static class Item<T, E> {
        T f;
        E s;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Item<Integer, String>>[] arr = new SinglyLinkedList[3];

        var a = arr[0];

    }
}
