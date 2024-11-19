package Huffman;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HuffmanDecoder {

    public static void decompress(String compressedFilePath, String decompressedFilePath, HuffmanNode root) {
    try (FileInputStream input = new FileInputStream(compressedFilePath);
         FileOutputStream output = new FileOutputStream(decompressedFilePath)) {

        // Ignorar a árvore serializada no início do arquivo
        DataInputStream in = new DataInputStream(input);
        HuffmanNode treeRoot = HuffmanDeserializer.deserializeTree(in);
        HuffmanNode current = treeRoot;

        int currentByte;
        int bitsInByte = 8;

        while ((currentByte = in.read()) != -1) {
            for (int i = bitsInByte - 1; i >= 0; i--) {
                int bit = (currentByte >> i) & 1;
                current = (bit == 0) ? current.getLeft() : current.getRight();

                if (current.isLeaf()) {
                    output.write(current.getValue());
                    System.out.println("Decodificando byte: " + current.getValue());
                    current = treeRoot;
                }
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
