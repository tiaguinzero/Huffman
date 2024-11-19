package Estruturas;

public class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16; // Tamanho inicial da tabela
    private ListaEncadeadaSimplesDesordenada<ListaEncadeadaSimplesDesordenada<Pair<K, V>>> table;
    private int capacity;
    private int size;

    class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return key.equals(pair.key); // Comparação apenas pela chave
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.table = new ListaEncadeadaSimplesDesordenada<>();
        try {
            for (int i = 0; i < capacity; i++) {
                table.guardeNoInicio(new ListaEncadeadaSimplesDesordenada<>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity; // Função de hash simples
    }

    private ListaEncadeadaSimplesDesordenada<Pair<K, V>> getBucket(int index) throws Exception {
        return table.get(index);
    }

    public void put(K key, V value) {
        int index = hash(key);
        try {
            ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(index);

            // Verifica se a chave já existe na lista
            for (int i = 0; i < bucket.getTamanho(); i++) {
                Pair<K, V> pair = bucket.get(i);
                if (pair.key.equals(key)) {
                    pair.value = value; // Atualiza o valor
                    return;
                }
            }

            // Adiciona um novo par
            bucket.guardeNoInicio(new Pair<>(key, value));
            size++;
        } catch (Exception e) {
            e.printStackTrace(); // Lidar com possíveis exceções
        }
    }

    public V get(K key) {
        int index = hash(key);
        try {
            ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(index);

            for (int i = 0; i < bucket.getTamanho(); i++) {
                Pair<K, V> pair = bucket.get(i);
                if (pair.key.equals(key)) {
                    return pair.value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Chave não encontrada
    }

    public void remove(K key) {
        int index = hash(key);
        try {
            ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(index);

            for (int i = 0; i < bucket.getTamanho(); i++) {
                Pair<K, V> pair = bucket.get(i);
                if (pair.key.equals(key)) {
                    bucket.remova(i);
                    size--;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean contemKey(K key) {
        int index = hash(key);
        try {
            ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(index);

            for (int i = 0; i < bucket.getTamanho(); i++) {
                Pair<K, V> pair = bucket.get(i);
                if (pair.key.equals(key)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        try {
            for (int i = 0; i < table.getTamanho(); i++) {
                ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(i);
                for (int j = 0; j < bucket.getTamanho(); j++) {
                    sb.append(bucket.get(j)).append(", ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("}");
        return sb.toString();
    }
}
