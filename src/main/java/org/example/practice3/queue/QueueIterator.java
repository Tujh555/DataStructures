package org.example.practice3.queue;

import org.example.BinaryLinkedNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

final class QueueIterator<T> implements Iterator<T> {
    private @Nullable BinaryLinkedNode<T> head;

    public QueueIterator(@Nullable BinaryLinkedNode<T> head) {
        this.head = head;
    }

    @Override
    public boolean hasNext() {
        return head != null;
    }

    @Override
    @NotNull
    public T next() {
        assert head != null : "Iterator is empty";
        T value = head.getValue();
        head = head.getNext();
        return value;
    }
}
