package ru.meldren.lab1.task2;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Objects;


public final class BinomialHeap<E extends Comparable<? super E>> extends AbstractQueue<E> {

    public final Node<E> head = new Node<>();

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int size() {
        int result = 0;
        for (Node<?> node = head.next; node != null; node = node.next) {
            if (node.rank >= 31) {
                throw new ArithmeticException("Size overflow.");
            }
            result |= 1 << node.rank;
        }
        return result;
    }

    @Override
    public void clear() {
        head.next = null;
    }

    @Override
    public boolean offer(E val) {
        Objects.requireNonNull(val);
        merge(new Node<>(val));
        return true;
    }

    @Override
    public E peek() {
        E result = null;
        for (Node<E> node = head.next; node != null; node = node.next) {
            if (result == null || node.value.compareTo(result) < 0) {
                result = node.value;
            }
        }
        return result;
    }

    @Override
    public E poll() {
        if (head.next == null) {
            return null;
        }
        E min = null;
        Node<E> nodeBeforeMin = null;
        Node<E> prevNode = head;
        while (true) {
            Node<E> node = prevNode.next;
            if (node == null) {
                break;
            }
            if (min == null || node.value.compareTo(min) < 0) {
                min = node.value;
                nodeBeforeMin = prevNode;
            }
            prevNode = node;
        }
        Node<E> minNode = nodeBeforeMin.next;
        nodeBeforeMin.next = minNode.next;
        minNode.next = null;
        merge(minNode.removeRoot());
        return min;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public void merge(BinomialHeap<E> other) {
        if (other == this) {
            throw new IllegalArgumentException();
        }
        merge(other.head.next);
        other.head.next = null;
    }

    private void merge(Node<E> other) {
        Node<E> self = head.next;
        head.next = null;
        Node<E> prevTail = null;
        Node<E> tail = head;

        while (self != null || other != null) {
            Node<E> node;
            if (other == null || self != null && self.rank <= other.rank) {
                node = self;
                self = self.next;
            } else {
                node = other;
                other = other.next;
            }
            node.next = null;
            if (tail.rank < node.rank) {
                prevTail = tail;
                tail.next = node;
                tail = node;
            } else if (tail.rank == node.rank + 1) {
                node.next = tail;
                prevTail.next = node;
                prevTail = node;
            } else if (tail.rank == node.rank) {
                if (tail.value.compareTo(node.value) <= 0) {
                    node.next = tail.down;
                    tail.down = node;
                    tail.rank++;
                } else {
                    tail.next = node.down;
                    node.down = tail;
                    node.rank++;
                    tail = node;
                    prevTail.next = node;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static final class Node<E extends Comparable<? super E>> {

        E value;
        int rank;
        Node<E> down, next;

        public Node() {
            this(null);
            rank = -1;
        }

        public Node(E val) {
            value = val;
            rank = 0;
            down = null;
            next = null;
        }

        public Node<E> removeRoot() {
            Node<E> result = null;
            Node<E> node = down;
            while (node != null) {
                Node<E> next = node.next;
                node.next = result;
                result = node;
                node = next;
            }
            return result;
        }
    }
}