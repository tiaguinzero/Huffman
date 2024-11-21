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

        HashMap<Byte, Integer> frequencyMap = new HashMap<>();


        //faz o hashmap de frequencia
        for(int i = 0; i<listabytes.getTamanho(); i++){
            try{
                byte b = listabytes.get(i);
                if(frequencyMap.containsKey(b)){
                    frequencyMap.put(b, frequencyMap.get(b) + 1);
                } else {
                    frequencyMap.put(b, 1);
                }
            }
            catch(Exception e){
                System.err.println("Erro ao buscar byte: " + e.getMessage());
            }
            
        }

        System.out.println(frequencyMap);
        System.out.println(frequencyMap.size());


        

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
        
        ListaEncadeadaSimplesDesordenada<Byte> compressedList = huffmanTree.makeCompressFile(listabytes, huffmanCodes);

        System.out.println(compressedList.getTamanho());

        System.out.println(compressedList);


        WriteBytesWListaEncadeada writeBytes = new WriteBytesWListaEncadeada("saida.bin", compressedList);


    }

}
