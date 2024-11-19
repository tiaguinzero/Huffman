package Huffman;

public class HuffmanNode implements Comparable<HuffmanNode> {
    private char character; // Caractere associado a este nó (ou '\0' se for nó interno)
    private int frequency; // Frequência do caractere
    private HuffmanNode left; // Filho esquerdo
    private HuffmanNode right; // Filho direito

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0'; // Nó interno
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public char getCharacter() { return this.character; }
    public int getFrequency() { return this.frequency; }
    public HuffmanNode getLeft() { return this.left; }
    public HuffmanNode getRight() { return this.right; }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }

    @Override
    public String toString() {
        return "Char: " + (this.character == '\0' ? "INTERNAL" : this.character) + ", Freq: " + this.frequency;
    }
}
