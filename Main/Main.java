package Main;
import Estruturas.ListaEncadeadaSimplesDesordenada;
import Estruturas.ReadBytes;
import Huffman.HuffmanTree;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String filePath = "entrada.txt";

        ReadBytes reader = new ReadBytes(filePath);
        reader.readBytes();
        ListaEncadeadaSimplesDesordenada<Byte> bytes = reader.getBytes();

        HashMap<Byte, Integer> frequencyMap = new HashMap<>();
        for(int i = 0; i<bytes.getTamanho(); i++){
            try{
                byte b = bytes.get(i);
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

        HuffmanTree huffmanTree = new HuffmanTree(frequencyMap);

        huffmanTree.generateCodes();

        System.out.println(huffmanTree.getHuffmanCodes());

    }
}
