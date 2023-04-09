package org.example.practice3.set;

import org.example.practice2.list.SinglyLinkedList;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

class SetIterator<T> implements Iterator<T> {
    private final @Nullable SinglyLinkedList<T>[] buckets;
    private int currentIndex = 0;
    private @Nullable Iterator<T> currentIterator = null;

    SetIterator(@Nullable SinglyLinkedList<T>[] buckets) {
        this.buckets = buckets;
        initCurrentIterator();
    }

    private void initCurrentIterator() {
        for (; currentIndex < buckets.length; currentIndex++) {
            if (buckets[currentIndex] != null) {
                currentIterator = buckets[currentIndex].iterator();
                currentIndex++;
                return;
            }
        }
    }

    @Override
    public boolean hasNext() {
        if (currentIterator == null) return false;

        if (currentIterator.hasNext()) return true;

        currentIterator = null;
        for (; currentIndex < buckets.length; currentIndex++) {
            if (buckets[currentIndex] != null) {
                currentIterator = buckets[currentIndex].iterator();
                currentIndex++;
                break;
            }
        }

        return currentIterator != null && currentIterator.hasNext();
    }

    @Override
    public T next() {
        assert currentIterator != null;
        return currentIterator.next();
    }
}
