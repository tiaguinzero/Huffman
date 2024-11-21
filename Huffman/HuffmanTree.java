package Huffman;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import Estruturas1.*;

public class HuffmanTree {
    private HuffmanNode root;
    private HashMap<Byte, String> huffmanCodes;
    private FilaPrioridade<HuffmanNode> queue;

    public HuffmanTree(HashMap<Byte, Integer> frequencyMap) {
        this.queue = new FilaPrioridade<>();
        this.huffmanCodes = new HashMap<>();

        for (HashMap.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            byte value = entry.getKey();
            int frequency = entry.getValue();
            HuffmanNode node = new HuffmanNode(value, frequency);
            queue.guarde(node); 
        }

        huffmanCompressor();
    }

    public HuffmanNode getRoot() {
        return this.root;
    }

    private void huffmanCompressor() {
        while (queue.getTamanho() > 1) {
            HuffmanNode left = queue.desenfilere();
            HuffmanNode right = queue.desenfilere();
            HuffmanNode parent = new HuffmanNode(left.getFrequency() + right.getFrequency(), left, right);
            queue.guarde(parent);
            System.out.println(queue.toString());
        }
        this.root = queue.desenfilere();
    }

    public void generateCodes() {
        if (root != null) {
            generateCodes(root, ""); //recursivo
        }
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            huffmanCodes.put(node.getValue(), code);
            // System.out.println("Byte: " + node.getValue() + ", Código: " + code + ", Frequência: " + node.getFrequency());
            return;
        }
        if (node.getLeft() != null) {
            generateCodes(node.getLeft(), code + "0");
        }
        if (node.getRight() != null) {
            generateCodes(node.getRight(), code + "1");
        }
    }

    public HashMap<Byte,String> getHuffmanCodes() {
        return huffmanCodes;
    }

    
    public ListaEncadeadaSimplesDesordenada<Byte> makeCompressFile(ListaEncadeadaSimplesDesordenada<Byte> bytes, HashMap<Byte,String> huffmanCodes){
        ListaEncadeadaSimplesDesordenada<Byte> newList = new ListaEncadeadaSimplesDesordenada<>(); //cria uma nova lista de bytes
        int byteTemp = 0;
        int bitCount = 0;


        for(int i = 0; i<bytes.getTamanho(); i++){
            System.out.printf("%.2f%%\n", (i * 100.0) / bytes.getTamanho());
            try {
                Byte b = bytes.get(i);
                String code = huffmanCodes.get(b);
                for(int j = 0; j<code.length(); j++){
                    byteTemp = (byteTemp << 1) | code.charAt(j) - '0';
                    bitCount++;
                    
                    if (bitCount == 8) {
                        newList.guardeNoFinal((byte) byteTemp);
                        byteTemp = 0;
                        bitCount = 0;
                    }
                }
        
            } catch (Exception e) {
                System.err.println("Erro ao buscar byte: " + e.getMessage());
            }

        }
        return newList;

    }
}
