package Huffman;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;

import Estruturas.FilaPrioridade;
import java.util.HashMap;
import java.util.Map;

import Estruturas.ListaEncadeadaSimplesDesordenada;

public class HuffmanTree {
    private HuffmanNode root;
    private HashMap<Byte, Integer> huffmanCodes;
    private FilaPrioridade<HuffmanNode> queue;
    

    public HuffmanTree(HashMap<Byte, Integer> frequencyMap) {
        this.queue = new FilaPrioridade<>();
        this.huffmanCodes = new HashMap<>();
    
        // Criação dos nós e inserção na fila de prioridade
        for (HashMap.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            byte value = entry.getKey();
            int frequency = entry.getValue();
            HuffmanNode node = new HuffmanNode(value, frequency);
            queue.guarde(node); // Adiciona nó à fila de prioridade
        }
        
        // Construa a árvore de Huf fman
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
        }
        this.root = queue.desenfilere();
    }

    public void generateCodes() {
        if (root != null) {
            generateCodes(root, 0, 0); // Inicia o processo com código 0 e profundidade 0
        }
    }
    
    private void generateCodes(HuffmanNode node, int code, int depth) {
        // Caso base: nó folha
        if (node.isLeaf()) {
            huffmanCodes.put(node.getValue(), code); // Associa o código inteiro ao byte
            System.out.println("Byte: " + node.getValue() + ", Código: " + Integer.toBinaryString(code) + ", Profundidade: " + depth);
            return;
        }
    
        // Percorre à esquerda (adiciona bit 0)
        if (node.getLeft() != null) {
            generateCodes(node.getLeft(), code << 1, depth + 1); // Desloca o código à esquerda
        }
    
        // Percorre à direita (adiciona bit 1)
        if (node.getRight() != null) {
            generateCodes(node.getRight(), (code << 1) | 1, depth + 1); // Desloca à esquerda e adiciona 1 no último bit
        }
    }
    

    public HashMap<Byte, Integer> getHuffmanCodes() {
        return huffmanCodes;
    }

    public void makeNewFile(String filePath, Map<Byte, Integer> huffmanCodes, ListaEncadeadaSimplesDesordenada<Byte> bytes, HuffmanNode root) {
        try (FileOutputStream file = new FileOutputStream(filePath)) {
            // Serializa a árvore
            serializeTree(file, root);
            System.out.println("Árvore serializada com sucesso.");
    
            byte currentByte = 0;
            int bitsInCurrentByte = 0;
    
            for (int i = 0; i < bytes.getTamanho(); i++) {
                try {
                    byte b = bytes.get(i);
                    int code = huffmanCodes.get(b);
                    int codeLength = Integer.SIZE - Integer.numberOfLeadingZeros(code);
        
                    System.out.println("Codificando byte: " + b + " com código: " + Integer.toBinaryString(code));
        
                    for (int j = codeLength - 1; j >= 0; j--) {
                        int bit = (code >> j) & 1;
                        currentByte = (byte) ((currentByte << 1) | bit);
                        bitsInCurrentByte++;
        
                        if (bitsInCurrentByte == 8) {
                            file.write(currentByte);
                            System.out.println("Escrevendo byte completo: " + Integer.toBinaryString(currentByte & 0xFF));
                            currentByte = 0;
                            bitsInCurrentByte = 0;
                        }
                    }
                    
                } catch (Exception e) {
                    // TODO: handle exception
                }
                
            }
    
            if (bitsInCurrentByte > 0) {
                currentByte <<= (8 - bitsInCurrentByte);
                file.write(currentByte);
                System.out.println("Escrevendo byte final incompleto: " + Integer.toBinaryString(currentByte & 0xFF));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void serializeTree(FileOutputStream file, HuffmanNode node) throws IOException {
        if (node == null) {
            file.write(0); // Nó nulo
            return;
        }
    
        file.write(1); // Indica que é um nó válido
    
        if (node.isLeaf()) {
            file.write(1); // Indica que é uma folha
            file.write(node.getValue()); // Escreve o valor do byte
        } else {
            file.write(0); // Indica que é um nó interno
            serializeTree(file, node.getLeft()); // Serializa subárvore esquerda
            serializeTree(file, node.getRight()); // Serializa subárvore direita
        }
    }

}
