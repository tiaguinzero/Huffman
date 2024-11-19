package ListaEncadeadaSimplesOrdenada;

import ListaEncadeadaSimplesOrdenada.ListaEncadeadaSimplesOrdenada;

public class HashMap<K extends Comparable<K>, V extends Comparable<V>> {
    private class Pair implements Comparable<Pair> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(Pair other) {
            return this.key.compareTo(other.key);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair other = (Pair) obj;
            return key.equals(other.key);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private ListaEncadeadaSimplesOrdenada<Pair>[] table;
    private int capacity;

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        this.capacity = capacity;
        this.table = new ListaEncadeadaSimplesOrdenada[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new ListaEncadeadaSimplesOrdenada<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) throws Exception {
        int index = hash(key);
        ListaEncadeadaSimplesOrdenada<Pair> bucket = table[index];
        for (int i = 0; i < bucket.getTamanho(); i++) {
            Pair current = bucket.getPrimeiro();
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }
        }
        bucket.guarde(new Pair(key, value));
    }

    public V get(K key) throws Exception {
        int index = hash(key);
        ListaEncadeadaSimplesOrdenada<Pair> bucket = table[index];
        for (int i = 0; i < bucket.getTamanho(); i++) {
            Pair current = bucket.getPrimeiro();
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
        }
        return null;
    }

    public void remove(K key) throws Exception {
        int index = hash(key);
        ListaEncadeadaSimplesOrdenada<Pair> bucket = table[index];
        for (int i = 0; i < bucket.getTamanho(); i++) {
            Pair current = bucket.getPrimeiro();
            if (current.getKey().equals(key)) {
                bucket.remova(current);
                return;
            }
        }
    }

    public boolean containsKey(K key) throws Exception {
        return get(key) != null;
    }
}
