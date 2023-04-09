package org.example.practice3.set;

import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.Set;

/**
 * Класс - затычка, чтобы в основном классе не реализовывать
 * все методы интерфейса Set.
 * @param <T> - тип элемента множества
 */
public abstract class AbstractSet<T> implements Set<T> {
    @NotNull
    @Override
    public final Object @NotNull [] toArray() {
        return new Object[0];
    }

    @Override
    public final  <T1> T1 @NotNull [] toArray(@NotNull T1[] a) {
        return a;
    }

    @Override
    public final boolean remove(Object o) {
        return false;
    }

    @Override
    public final boolean containsAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public final boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public final boolean removeAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public final void clear() {

    }
}
