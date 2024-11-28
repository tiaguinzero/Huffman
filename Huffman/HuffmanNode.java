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

    public byte getValue() { return value; }
    public int getFrequency() { return frequency; }
    public HuffmanNode getLeft() { return left; }
    public HuffmanNode getRight() { return right; }

    public void setLeft(HuffmanNode left) { this.left = left; }
    public void setRight(HuffmanNode right) { this.right = right; }
    public void setByte(byte value) { this.value = value; }
    public void setFrequency(int frequency) { this.frequency = frequency; }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }

    @Override
    public String toString() {
        return "Char: " + this.value + ", Freq: " + this.frequency;
    }
}
