package Huffman;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


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
            return new HuffmanNode(value, 0); 
        } else {
            HuffmanNode left = deserializeTree(dis);
            HuffmanNode right = deserializeTree(dis);
            return new HuffmanNode(0, left, right); 
        }
    }

    public void descomprimir() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(this.compressedFilePath));
             FileOutputStream fos = new FileOutputStream(this.outputFilePath)) {
    
           HuffmanTree arvore = new HuffmanTree(); 
           arvore.setRoot(deserializeTree(dis)); 

           System.out.println(arvore.toString());

           arvore.generateCodes(); 

           HuffmanNode current = arvore.getRoot();
            while (dis.available() > 0) {
                byte b = dis.readByte();
                for (int i = 7; i >= 0; i--) { 
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
