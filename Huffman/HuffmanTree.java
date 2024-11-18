package Huffman;

import ListaEncadeadaSimplesOrdenada.ListaEncadeadaSimplesOrdenada;

public class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree(String text) throws Exception {
        ListaEncadeadaSimplesOrdenada<HuffmanNode> queue = new ListaEncadeadaSimplesOrdenada<>();

        // 1. Contar frequências.
        int[] frequencies = new int[256];
        for (char c : text.toCharArray()) {
            frequencies[c]++;
        }

        // 2. Criar nós iniciais.
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.guarde(new HuffmanNode((char) i, frequencies[i]));
            }
        }

        // 3. Construir a árvore.
        while (queue.getSize() > 1) {
            HuffmanNode left = queue.getPrimeiro();
            queue.remova(left);
            HuffmanNode right = queue.getPrimeiro();
            queue.remova(right);

            HuffmanNode parent = new HuffmanNode(left.getFrequency() + right.getFrequency(), left, right);
            queue.guarde(parent);
        }

        this.root = queue.getPrimeiro();
    }

    public HuffmanNode getRoot() {
        return this.root;
    }

    // Método para gerar códigos (não implementado ainda).
}
