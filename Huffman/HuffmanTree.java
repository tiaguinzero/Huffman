package Huffman;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import Estruturas1.*;

public class HuffmanTree {
    private HuffmanNode root;
    private HashMap<Byte, Integer> huffmanCodes;
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
            huffmanCodes.put(node.getValue(), Integer.parseInt(code, 2));
            System.out.println("Byte: " + node.getValue() + ", Código: " + code + ", Profundidade: ");
            return;
        }
        if (node.getLeft() != null) {
            generateCodes(node.getLeft(), code + "0");
        }
        if (node.getRight() != null) {
            generateCodes(node.getRight(), code + "1");
        }
    }

    public HashMap<Byte, Integer> getHuffmanCodes() {
        return huffmanCodes;
    }

    public void makeNewFile(String filePath, Map<Byte, Integer> huffmanCodes, ListaEncadeadaSimplesDesordenada<Byte> bytes, HuffmanNode root) {
        try (FileOutputStream file = new FileOutputStream(filePath)) {
            serializeTree(file, root);
            System.out.println("Árvore serializada com sucesso.");

            BitSet bitSet = new BitSet();
            int bitIndex = 0;

            for (int i = 0; i < bytes.getTamanho(); i++) {
                try {
                    byte b = bytes.get(i);
                    int code = huffmanCodes.get(b);
                    int codeLength = Integer.SIZE - Integer.numberOfLeadingZeros(code);

                    for (int j = codeLength - 1; j >= 0; j--) {
                        boolean bit = ((code >> j) & 1) == 1;
                        bitSet.set(bitIndex++, bit);
                    }
                    
                } catch (Exception e) {
                }
                
            }

            byte[] compressedBytes = bitSet.toByteArray();
            file.write(compressedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void comprimeArvore(){

        

    }
}
