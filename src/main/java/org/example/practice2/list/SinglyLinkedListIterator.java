package org.example.practice2.list;

import org.example.Node;

import java.util.Iterator;

final class SinglyLinkedListIterator<T> implements Iterator<T> {
    private Node<T> head;

    public SinglyLinkedListIterator(Node<T> head) {
        this.head = head;
    }

    @Override
    public boolean hasNext() {
        return head != null;
    }

    @Override
    public T next() {
        T value = head.value;
        head = head.getNext();
        return value;
    }
}
