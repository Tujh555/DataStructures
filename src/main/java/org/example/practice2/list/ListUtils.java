package org.example.practice2.list;

import org.example.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

final class ListUtils {

    public static <T> SinglyLinkedList<T> singlyLinkedList(int size, Function<Integer, T> initial) {
        SinglyLinkedList<T> list = new SinglyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(initial.apply(i));
        }

        return list;
    }

    /**
     * Метод выполняет сортировку односвязного списка слиянием
     *
     * @param head - начало списка
     * @param f - функция для получения элементов сравнения
     * @return начало отсортированного списка
     * @param <T> - тип элементов в списке
     * @param <E> - тип элемента сравнения
     */
    @Nullable
    public static <T, E extends Comparable<E>> Node<T> sortListByMerge(@Nullable Node<T> head, @NotNull Function<T, E> f) {
        // Если нет начального элемента или он один, сортировка не имеет смысла
        if (head == null) return null;
        if (head.getNext() == null) return head;

        // Если элемента в списке 2 - просто меняем
        // их местами и возвращаем результат

        if (head.getNext().getNext() == null) {

            // Если второй элемент больше первого - просто
            // возвращаем список без изменений
            if (f.apply(head.getNext().value).compareTo(f.apply(head.value)) >= 0)
                return head;

            // Если первый элемент больше - меняем местами
            Node<T> nxt = head.getNext();
            head.setNext(null);
            nxt.setNext(head);

            return nxt;
        }

        // Заводим 2 указателя для разделения списка пополам
        // (быстрый и медленный)

        Node<T> slow = head;
        Node<T> fast = head;

        // Двигаем указатели, пока быстрый не достигнет
        // последнего элемента
        while (fast.getNext() != null) {
            assert slow != null : "Slow pointer was null";
            slow = slow.getNext();

            // Необходимо остановиться на последнем элементе,
            // поэтому если через 2 элемента null - выходим из цикла
            if (fast.getNext().getNext() == null) {
                break;
            }

            fast = fast.getNext().getNext();
        }

        // Отделяем первую часть списка от второй
        assert slow != null : "Slow pointer was null";
        Node<T> mid = slow.getNext();
        slow.setNext(null);

        // Сортируем две части списка по отдельности рекурсивно
        Node<T> first = sortListByMerge(head, f);
        Node<T> second = sortListByMerge(mid, f);

        assert first != null && second != null : "First or second heads were null";
        return merge(first, second, f);
    }

    /**
     * Метод соединяет воедино два отсортированных списка
     *
     * @param first - начало первого списка
     * @param second - начало второго списка
     * @param f - функция для получения элемента сравнения
     * @return начало слитого списка
     * @param <T> - тип элементов в списке
     * @param <E> - тип элемента сравнения
     */
    @NotNull
    private static <T, E extends Comparable<E>> Node<T> merge(@Nullable Node<T> first, @Nullable Node<T> second, @NotNull Function<T, E> f) {
        @Nullable Node<T> p1 = first;
        @Nullable Node<T> p2 = second;

        // нициализируем начало нового списка наименьшим первым элементом
        // из двух пришедших списков
        final int compareResult = f.apply(p2.value).compareTo(f.apply(p1.value));
        @NotNull Node<T> res;

        if (compareResult >= 0) {
            Node<T> nxt = p1.getNext();
            p1.setNext(null);
            Node<T> ans = p1;
            p1 = nxt;
            res = ans;
        } else {
            Node<T> nxt = p2.getNext();
            p2.setNext(null);
            Node<T> ans = p2;
            p2 = nxt;
            res = ans;
        }

        Node<T> curr = res;

        // Двигаем 2 указателя, по двум спискам, пока оба они не обнулятся
        while (p1 != null && p2 != null) {

            // Если оба указателя не дошли до конца,
            // двигаем элемент, который меньше
            final int _compareResult = f.apply(p2.value).compareTo(f.apply(p1.value));
            if (_compareResult >= 0) {
                Node<T> nxt = p1.getNext();
                p1.setNext(null);
                Node<T> ans = p1;
                p1 = nxt;
                curr.setNext(ans);
            } else {
                Node<T> nxt = p2.getNext();
                p2.setNext(null);
                Node<T> ans = p2;
                p2 = nxt;
                curr.setNext(ans);
            }

            curr = curr.getNext();
            assert curr != null;
        }

        // Если один из указателей дошел до конца,
        // просто добавляем оставшиеся элементы другого списка в конец
        if (p1 != null) {
            curr.setNext(p1);
        }

        if (p2 != null) {
            curr.setNext(p2);
        }

        return res;
    }
}
