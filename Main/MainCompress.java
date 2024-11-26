package Main;

import java.io.*;
import java.util.HashMap;
import Estruturas1.*;
import Huffman.*;

public class MainCompress {
    public static void main(String[] args) {

        ReadBytes readBytes = new ReadBytes("assets/video.mp4");

        readBytes.readBytes();

        Pilha<Byte> listabytes = readBytes.getBytes();
        HashMap<Byte, Integer> frequencyMap = readBytes.getFrequencyMap();




        System.out.println(frequencyMap);


        

        HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);


        huffmanTree.generateCodes();
        HashMap<Byte, String> huffmanCodes = huffmanTree.getHuffmanCodes();
        System.out.println(huffmanCodes);

        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        huffmanTree.makeCompressFile(listabytes, huffmanCodes, "saida.txt");

        System.out.println("Compressão realizada com sucesso!");

    }

}
