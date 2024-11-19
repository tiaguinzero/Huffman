package Huffman;

import ListaEncadeadaSimplesOrdenada.HashMap;
import ListaEncadeadaSimplesOrdenada.ListaEncadeadaSimplesOrdenada;

public class HuffmanTree {
    private HuffmanNode root;
    private HashMap<Character, String> huffmanCodes;

    public HuffmanTree(String text) throws Exception {
        ListaEncadeadaSimplesOrdenada<HuffmanNode> queue = new ListaEncadeadaSimplesOrdenada<>();

        // Contar frequências
        int[] frequencies = new int[256];
        for (char c : text.toCharArray()) {
            frequencies[c]++;
        }

        // Criar nós iniciais
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.guarde(new HuffmanNode((char) i, frequencies[i]));
            }
        }

        // Construir árvore
        while (queue.getTamanho() > 1) {
            HuffmanNode left = queue.getPrimeiro();
            queue.remova(left);

            HuffmanNode right = queue.getPrimeiro();
            queue.remova(right);

            HuffmanNode parent = new HuffmanNode(left.getFrequency() + right.getFrequency(), left, right);
            queue.guarde(parent);
        }

        this.root = queue.getPrimeiro();
        this.huffmanCodes = new HashMap<>(256);
        generateCodes(this.root, "");
    }

    private void generateCodes(HuffmanNode node, String code) throws Exception {
        if (node.isLeaf()) {
            huffmanCodes.put(node.getCharacter(), code);
            return;
        }
        if (node.getLeft() != null) generateCodes(node.getLeft(), code + "0");
        if (node.getRight() != null) generateCodes(node.getRight(), code + "1");
    }

    public HashMap<Character, String> getHuffmanCodes() {
        return this.huffmanCodes;
    }
}
