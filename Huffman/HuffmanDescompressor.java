package Huffman;

import java.io.DataInputStream;
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


    public HuffmanNode deserializeTree(DataInputStream dis) throws IOException {
        boolean isLeaf = dis.readBoolean();
        if (isLeaf) {
            byte value = dis.readByte();
            System.out.println(value);
            return new HuffmanNode(value, 0); // Frequência não é necessária na descompressão
        } else {
            HuffmanNode left = deserializeTree(dis);
            HuffmanNode right = deserializeTree(dis);
            return new HuffmanNode(0, left, right); // Frequência não é necessária na descompressão
        }
    }

    public void descomprimir() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(this.compressedFilePath));
             FileOutputStream fos = new FileOutputStream(this.outputFilePath)) {
    
           HuffmanTree arvore = new HuffmanTree(); //Cria uma árvore vazia
           arvore.setRoot(deserializeTree(dis)); // Lê e desserializa a árvore

           System.out.println(arvore.toString());

           arvore.generateCodes(); // Gera os códigos de Huffman

           HashMap<Byte,String> codes = arvore.getHuffmanCodes(); // Obtém os códigos de Huffman
            
           int paddingBits = dis.readUnsignedByte();    // Lê os bits de preenchimento

           HuffmanNode current = arvore.getRoot();
        while (dis.available() > 0) {
            byte b = dis.readByte();
            for (int i = 7; i >= 0; i--) { // Lê cada bit do byte, do mais significativo para o menos
                int bit = (b >> i) & 1;

                if (bit == 0) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }

                if (current.isLeaf()) {
                    fos.write(current.getValue());
                    current = arvore.getRoot(); // Volta para a raiz para o próximo caractere
                }
            }
        }

    
        } catch (IOException e) {
                // ...
        }
    }
}
