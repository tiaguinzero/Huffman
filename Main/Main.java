package Main;

import java.io.*;
import java.util.HashMap;
import Estruturas1.*;
import Huffman.*;

public class Main {
    public static void main(String[] args) {

        ReadBytes readBytes = new ReadBytes("entrada.txt");

        readBytes.readBytes();

        ListaEncadeadaSimplesDesordenada<Byte> listabytes = readBytes.getBytes();
        HashMap<Byte, Integer> frequencyMap = readBytes.getFrequencyMap();




        System.out.println(frequencyMap);
        System.out.println(listabytes.getTamanho());


        

        HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);


        huffmanTree.generateCodes();
        HashMap<Byte, String> huffmanCodes = huffmanTree.getHuffmanCodes();
        System.out.println(huffmanCodes);

        try {
            Byte b = listabytes.get(0);
            System.out.println(huffmanCodes.get(b));
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        ListaEncadeadaSimplesDesordenada<Byte> compressedList = huffmanTree.makeCompressFile(listabytes, huffmanCodes, "saida.txt");

        System.out.println(compressedList.getTamanho());

    }

}
