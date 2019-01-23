package com.practice.collection;

import java.util.Objects;

public class MyHashMap<K, V> {

    private int size;
    private int capacity;
    private double loadFactor;

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private Pair<K, V>[] elementData;

    public MyHashMap(int capacity, double loadFactor) {
        init(capacity, loadFactor);
    }

    public MyHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(double loadFactor) {
        this(DEFAULT_CAPACITY, loadFactor);
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    private void init(int capacity, double loadFactor) {
        elementData = new Pair[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    public int size() {
        return size;
    }

    private static class Pair<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Pair<K, V> next;

        public Pair(int hash, K key, V value, Pair<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o instanceof MyHashMap.Pair) {
                Pair<?, ?> pair = (Pair<?, ?>) o;
                if (Objects.equals(key, pair.getKey()) &&
                        Objects.equals(value, pair.getValue()))
                    return true;
            }
            return false;
        }
    }

    //Implementation from original java HashMap class
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int bucketIndex(int hash) {
        return hash % capacity;
    }

    public void put(K key, V value) {

        if (key == null) {
            putForNull(value);
        } else {

            int h = hash(key);
            int bucketIndex = bucketIndex(h);

            Pair<K, V> rootPair = elementData[bucketIndex];
            if (rootPair == null) {
                Pair<K, V> pair = new Pair<>(h, key, value, null);
                elementData[bucketIndex] = pair;
            } else {

                Pair<K, V> occurrence = checkOccurrence(rootPair, h, key);
                if (occurrence == null) {
                    Pair<K, V> pair = new Pair<>(h, key, value, rootPair);
                    elementData[bucketIndex] = pair;
                } else {
                    occurrence.value = value;
                    size--;
                }
            }
        }

        size++;
    }

    private Pair<K, V> checkOccurrence(Pair<K, V> root, int hash, K key) {

        Pair<K, V> temp = root;
        while (temp != null) {

            if (key == null && temp.key == null
                    || temp.hash == hash && temp.key.equals(key)) {
                break;
            }
            temp = temp.next;
        }

        return temp;
    }

    private void putForNull(V value) {

        Pair<K, V> element = new Pair<>(0, null, value, null);
        if (elementData[0] != null) {

            Pair<K, V> occurrence = checkOccurrence(elementData[0], 0, null);
            if (occurrence != null) {
                occurrence.value = value;
            } else {
                element.next = elementData[0];
            }
        }
        elementData[0] = element;
    }

    public V get(K key) {

        Pair<K, V> pair = getPair(key);

        if (pair == null)
            return null;

        return pair.value;
    }

    private Pair<K, V> getPair(K key) {

        for (Pair<K, V> bucket : elementData) {
            while (bucket != null) {
                if (key == null && bucket.key == null
                        || key != null && key.equals(bucket.key)) {
                    return bucket;
                }
                bucket = bucket.next;
            }
        }

        return null;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("[ ");

        for (Pair<K, V> temp : elementData) {
            while (temp != null) {
                builder.append("{").append(temp.key)
                        .append(":").append(temp.value).append("} ");

                temp = temp.next;
            }
        }

        builder.append("]");

        return builder.toString();
    }
}