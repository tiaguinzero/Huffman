package Main;

import Huffman.HuffmanDescompressor;

public class MainDecompress {
    public static void main(String[] args) {
        HuffmanDescompressor descompressor = new HuffmanDescompressor("saida.txt", "decomprimido.mp4");
        descompressor.descomprimir();
    }
    
}
