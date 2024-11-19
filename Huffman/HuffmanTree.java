package Huffman;

import java.util.BitSet;

import Estruturas.FilaPrioridade;
import java.util.HashMap;
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

    
    
}
