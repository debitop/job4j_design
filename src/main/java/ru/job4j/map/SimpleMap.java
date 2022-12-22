package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key));

        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] expandTable = new MapEntry[capacity];
        for (MapEntry<K, V> t : table) {
            if (t != null) {
                int index = indexFor(hash(t.key));
                expandTable[index] = t;
            }
        }
        table = expandTable;
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key));
        return table[i] != null && (Objects.hashCode(key) == Objects.hashCode(table[i].key))
                && Objects.equals(table[i].key, key) ? table[i].value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key));
        boolean rsl = false;
        if (table[index] != null && Objects.hashCode(key) == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}