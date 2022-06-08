package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            size();
        }
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        container[Objects.checkIndex(index, container.length)] = newValue;
        return container[index];
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        modCount++;
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[size - 1] = null;
        size--;
        return container[index];
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        container = Arrays.copyOf(container, container.length * 2);
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}