package Main;

import Huffman.*;

public class TesteHuffmanDecoder {
    public static void main(String[] args) {
        String compressedFilePath = "saida.bin";
        String decompressedFilePath = "teste.txt";

        // Deserializa a árvore de Huffman
        HuffmanNode root = HuffmanDeserializer.deserializeTree("saida.bin");

        if (root != null) {
            System.out.println("Árvore de Huffman deserializada com sucesso.");
            HuffmanDecoder.decompress(compressedFilePath, decompressedFilePath, root);
        } else {
            System.out.println("Falha ao deserializar a árvore de Huffman.");
        }
    }
}
