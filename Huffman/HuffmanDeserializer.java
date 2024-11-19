package Huffman;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class HuffmanDeserializer {

    public static HuffmanNode deserializeTree(String filePath) {
        try (DataInputStream in = new DataInputStream(new FileInputStream(filePath))) {
            return deserializeTree(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static HuffmanNode deserializeTree(DataInputStream in) throws IOException {
        int nodeType = in.readUnsignedByte();
        if (nodeType == 0) {
            System.out.println("Nó nulo encontrado.");
            return null;
        }
    
        HuffmanNode node = new HuffmanNode((byte) 0, 0);
        int isLeaf = in.readUnsignedByte();
    
        if (isLeaf == 1) {
            byte value = in.readByte();
            System.out.println("Nó folha encontrado com valor: " + value);
            node.setByte(value);
        } else {
            System.out.println("Nó interno encontrado.");
            node.setLeft(deserializeTree(in));
            node.setRight(deserializeTree(in));
        }
        return node;
    }
}   
