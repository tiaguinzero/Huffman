package Huffman;

public class HuffmanNode {
    private char character;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0'; // Nó interno não tem caractere.
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
}
