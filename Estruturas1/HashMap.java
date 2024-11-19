package Estruturas1;


// package Estruturas;

// public class HashMap<K, V> {
//     private static final int DEFAULT_CAPACITY = 16; // Tamanho inicial
//     private static final double LOAD_FACTOR = 0.75; // Fator de carga para resize
//     private ListaEncadeadaSimplesDesordenada<ListaEncadeadaSimplesDesordenada<Pair<K, V>>> table;
//     private int capacity;
//     private int size;

//     class Pair<K, V> {
//         K key;
//         V value;

//         Pair(K key, V value) {
//             this.key = key;
//             this.value = value;
//         }

//         @Override
//         public boolean equals(Object obj) {
//             if (this == obj) return true;
//             if (obj == null || getClass() != obj.getClass()) return false;
//             Pair<?, ?> pair = (Pair<?, ?>) obj;
//             return key.equals(pair.key);
//         }

//         @Override
//         public int hashCode() {
//             return key.hashCode();
//         }

//         @Override
//         public String toString() {
//             return key + "=" + value;
//         }
//     }

//     public HashMap() {
//         this(DEFAULT_CAPACITY);
//     }

//     public HashMap(int capacity) {
//         this.capacity = capacity;
//         this.size = 0;
//         this.table = new ListaEncadeadaSimplesDesordenada<>();
//         try {
//             for (int i = 0; i < capacity; i++) {
//                 table.guardeNoInicio(new ListaEncadeadaSimplesDesordenada<>());
//             }
//         } catch (Exception e) {
//             System.err.println("Erro ao inicializar tabela: " + e.getMessage());
//         }
//     }

//     private int hash(K key) {
//         return Math.abs(key.hashCode()) % capacity;
//     }

//     private ListaEncadeadaSimplesDesordenada<Pair<K, V>> getBucket(int index) throws Exception {
//         return table.get(index);
//     }

//     public void put(K key, V value) {
//         if (key == null) throw new IllegalArgumentException("Chave não pode ser nula.");
//         if (contemValor(value)) throw new IllegalArgumentException("Valor já existe no HashMap.");

//         if ((double) size / capacity > LOAD_FACTOR) {
//             resize();
//         }

//         int index = hash(key);
//         try {
//             ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(index);

//             // Correctly checks for existing key and updates value or adds new pair
//             boolean keyExists = false;
//             for (int i = 0; i < bucket.getTamanho(); i++) {
//                 Pair<K, V> pair = bucket.get(i);
//                 if (pair.key.equals(key)) {
//                     pair.value = value;
//                     keyExists = true;
//                     break;
//                 }
//             }
//             if (!keyExists) {
//                 bucket.guardeNoInicio(new Pair<>(key, value));
//                 size++;
//             }
//         } catch (Exception e) {
//             System.err.println("Erro ao adicionar elemento: " + e.getMessage());
//         }
//     }

//     public V get(K key) {
//         // ... (get method remains unchanged) ...
//     }

//     private void resize() {
//         int newCapacity = capacity * 2;
//         ListaEncadeadaSimplesDesordenada<ListaEncadeadaSimplesDesordenada<Pair<K, V>>> newTable =
//                 new ListaEncadeadaSimplesDesordenada<>();
//         for (int i = 0; i < newCapacity; i++) {
//             newTable.guardeNoInicio(new ListaEncadeadaSimplesDesordenada<>());
//         }

//         try {
//             for (int i = 0; i < capacity; i++) {
//                 ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(i);
//                 for (Pair<K, V> pair : bucket) { //Iterate using enhanced for loop for cleaner code
//                     int newIndex = Math.abs(pair.key.hashCode()) % newCapacity;
//                     newTable.get(newIndex).guardeNoInicio(pair); //Directly add to new bucket - no need to check for duplicates here.  Resize handles it.
//                 }
//             }
//             this.table = newTable;
//             this.capacity = newCapacity;
//         } catch (Exception e) {
//             System.err.println("Erro ao redimensionar tabela: " + e.getMessage());
//         }
//     }

//     public V get(K key) {
//         int index = hash(key);
//         try {
//             ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(index);

//             for (int i = 0; i < bucket.getTamanho(); i++) {
//                 Pair<K, V> pair = bucket.get(i);
//                 if (pair.key.equals(key)) {
//                     return pair.value;
//                 }
//             }
//         } catch (Exception e) {
//             System.err.println("Erro ao buscar chave: " + e.getMessage());
//         }
//         return null;
//     }
    

//     public boolean contemKey(K key) {
//         return get(key) != null;
//     }

//     private boolean contemValor(V value) {
//         try {
//             for (int i = 0; i < table.getTamanho(); i++) {
//                 ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(i);
//                 for (int j = 0; j < bucket.getTamanho(); j++) {
//                     if (bucket.get(j).value.equals(value)) {
//                         return true;
//                     }
//                 }
//             }
//         } catch (Exception e) {
//             System.err.println("Erro ao verificar valor: " + e.getMessage());
//         }
//         return false;
//     }

//     public int size() {
//         return size;
//     }
    
//     @Override
//     public String toString() {
//         StringBuilder sb = new StringBuilder();
//         sb.append("{");
//         try {
//             for (int i = 0; i < table.getTamanho(); i++) {
//                 ListaEncadeadaSimplesDesordenada<Pair<K, V>> bucket = getBucket(i);
//                 for (int j = 0; j < bucket.getTamanho(); j++) {
//                     sb.append(bucket.get(j)).append(", ");
//                 }
//             }
//         } catch (Exception e) {
//             System.err.println("Erro ao construir string: " + e.getMessage());
//         }
//         if (sb.length() > 1) {
//             sb.setLength(sb.length() - 2); // Remove última vírgula e espaço
//         }
//         sb.append("}");
//         return sb.toString();
//     }
// }