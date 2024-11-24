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

    public ListaEncadeadaSimplesDesordenada<Byte> makeCompressFile(
    ListaEncadeadaSimplesDesordenada<Byte> bytes,
    HashMap<Byte, String> huffmanCodes,
    String namefile) 
    {
    
        ListaEncadeadaSimplesDesordenada<Byte> newList = new ListaEncadeadaSimplesDesordenada<>(); // Cria uma nova lista de bytes
        int bitCounter = 0;
        Fila<Integer> fila = new Fila<>();
    
        try (FileOutputStream fos = new FileOutputStream(namefile)) { // Abre o arquivo uma única vez
            for (int i = 0; i < bytes.getTamanho(); i++) {
                System.out.printf("%.2f%%\n", (i * 100.0) / bytes.getTamanho());
            
                try{
                    Byte b = bytes.get(i);
                    String code = huffmanCodes.get(b);
                    if(fila.getTamanho() < 8){
                        for (int j = 0; j < code.length(); j++) {
                            if (code.charAt(j) == '1') {
                                fila.guardeUmItem(1);
                            } else {
                                fila.guardeUmItem(0);
                            }
                        }
                    }

                    if(fila.getTamanho()>=8){
                        byte newByte = 0;
                        StringBuilder sb = new StringBuilder();
                        for(int j = 0; j < 8; j++){
                            sb.append(fila.desenfilere());
                        }
                        newByte = (byte) Integer.parseInt(sb.toString(), 2);
                        fos.write(newByte);
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever bytes no arquivo: " + e.getMessage());
        }
    
        return newList; // Retorna a nova lista
    }



}
