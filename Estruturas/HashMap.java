package Estruturas;

public class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16; // Tamanho inicial da tabela
    private ListaEncadeadaSimplesDesordenada<Entry<K, V>>[] table;
    private int size;

    
    class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) obj;
            return key.equals(entry.key); // Comparação apenas pela chave
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked") // Necessário para criar um array de genéricos
    public HashMap(int capacity) {
        table = new ListaEncadeadaSimplesDesordenada[capacity];
        size = 0;
        for(int i = 0; i < capacity; i++){
            table[i] = new ListaEncadeadaSimplesDesordenada<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length; // Função de hash simples
    }

    public void put(K key, V value) {
        int index = hash(key);
        ListaEncadeadaSimplesDesordenada<Entry<K, V>> lista = table[index];

        // Verifica se a chave já existe na lista
        boolean chaveExiste = false;
        for(int i = 0; i < lista.getTamanho(); i++){
            try {
                if (lista.get(i).key.equals(key)) {
                    lista.remova(i);
                    chaveExiste = true;
                    break;
                }
            } catch (Exception e) {
                //Ignora exceção, pois a lista pode estar vazia
            }
        }


        try {
            lista.guardeNoInicio(new Entry<>(key, value));
            if(!chaveExiste) size++;
        } catch (Exception e) {
            e.printStackTrace(); // Lidar com possíveis exceções
        }
    }

    public V get(K key) {
        int index = hash(key);
        ListaEncadeadaSimplesDesordenada<Entry<K, V>> lista = table[index];

        for (int i = 0; i < lista.getTamanho(); i++) {
            try {
                if (lista.get(i).key.equals(key)) {
                    return lista.get(i).value;
                }
            } catch (Exception e) {
                // Ignora exceção se a lista estiver vazia.
            }
        }
        return null; // Chave não encontrada
    }

    public int size() {
        return size;
    }
    
    public void remove(K key) {
        int index = hash(key);
        ListaEncadeadaSimplesDesordenada<Entry<K, V>> lista = table[index];

        for (int i = 0; i < lista.getTamanho(); i++) {
            try {
                if (lista.get(i).key.equals(key)) {
                    lista.remova(i);
                    size--;
                    return;
                }
            } catch (Exception e) {
                // Ignora exceção se a lista estiver vazia.
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (ListaEncadeadaSimplesDesordenada<Entry<K, V>> lista : table) {
            for (int i = 0; i < lista.getTamanho(); i++) {
                try {
                    sb.append(lista.get(i).key).append("=").append(lista.get(i).value).append(", ");
                } catch (Exception e) {
                    // Ignora exceção se a lista estiver vazia.
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}