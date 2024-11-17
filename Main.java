import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite o texto para compactação:");
        String text = reader.readLine();

        HuffmanTree huffmanTree = new HuffmanTree(text);
        System.out.println("Códigos de Huffman:");
        huffmanTree.printCodes();

        String encoded = huffmanTree.encode(text);
        System.out.println("Texto codificado: " + encoded);

        String decoded = huffmanTree.decode(encoded);
        System.out.println("Texto decodificado: " + decoded);
    }
}
