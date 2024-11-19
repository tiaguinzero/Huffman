package Huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import Estruturas1.*;

public class HuffmanDescompressor {
    private String compressedFilePath;
    private String outputFilePath;

    public HuffmanDescompressor(String compressedFilePath, String outputFilePath) {
        this.compressedFilePath = compressedFilePath;
        this.outputFilePath = outputFilePath;
    }

    public void decompress() {
        try (FileInputStream input = new FileInputStream(compressedFilePath);
             FileOutputStream output = new FileOutputStream(outputFilePath)) {

            // Reconstrói a árvore de Huffman a partir do arquivo
            HuffmanNode root = deserializeTree(input);

            // Lê os dados comprimidos e traduz os bits em bytes usando a árvore
            HuffmanNode currentNode = root;
            int currentByte;

            while ((currentByte = input.read()) != -1) {
                for (int bitIndex = 7; bitIndex >= 0; bitIndex--) {
                    int bit = (currentByte >> bitIndex) & 1;
                    currentNode = (bit == 0) ? currentNode.getLeft() : currentNode.getRight();

                    // Quando alcança uma folha, escreve o byte correspondente
                    if (currentNode.isLeaf()) {
                        output.write(currentNode.getValue());
                        currentNode = root; // Retorna para a raiz para continuar a decodificação
                    }
                }
            }

            System.out.println("Descompressão concluída com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HuffmanNode deserializeTree(FileInputStream input) throws IOException {
        int isValidNode = input.read();
        if (isValidNode == -1 || isValidNode == 0) {
            return null; // Nó inválido ou final do arquivo
        }

        int isLeaf = input.read();
        if (isLeaf == 1) {
            return new HuffmanNode((byte) input.read(), 0); // Cria nó folha com valor
        }

        // Caso contrário, reconstrói nós internos recursivamente
        HuffmanNode left = deserializeTree(input);
        HuffmanNode right = deserializeTree(input);
        return new HuffmanNode(0, left, right); // Nó interno não precisa de valor
    }
}
