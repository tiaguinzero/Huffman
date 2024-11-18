package Main;

import Huffman.HuffmanTree;

import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) {
        try {
            RandomAccessFile inputFile = new RandomAccessFile("entrada.txt", "r");
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = inputFile.readLine()) != null) {
                text.append(line).append("\n");
            }
            inputFile.close();

            HuffmanTree tree = new HuffmanTree(text.toString());
            System.out.println("Árvore de Huffman construída com sucesso!");
            // Testes adicionais aqui.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
