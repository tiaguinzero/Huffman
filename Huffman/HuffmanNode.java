package Huffman;

public class HuffmanNode implements Comparable<HuffmanNode> {
    private byte value; // byte associado ao nó
    private int frequency; // Frequência de caracteres dentro do nó e seus filhos
    private HuffmanNode left; // Filho esquerdo
    private HuffmanNode right; // Filho direito

    public HuffmanNode(byte value, int frequency) {
        this.value = value;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.value = 0;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public byte getValue() { return this.value; }
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
        return "Char: " + (this.value == 0 ? "INTERNAL" : this.value) + ", Freq: " + this.frequency;
    }
}
