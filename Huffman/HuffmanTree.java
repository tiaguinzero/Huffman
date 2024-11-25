package Huffman;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

import Estruturas1.*;

public class HuffmanTree {
    private HuffmanNode root;
    private HashMap<Byte, String> huffmanCodes;
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

    public HuffmanTree(){
        this.queue = new FilaPrioridade<>();
        this.huffmanCodes = new HashMap<>();
        this.root = null;
    }


    public HuffmanNode getRoot() {
        return this.root;
    }

    public void setRoot(HuffmanNode root) {
        this.root = root;
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
            huffmanCodes.put(node.getValue(), code);
            // System.out.println("Byte: " + node.getValue() + ", Código: " + code + ", Frequência: " + node.getFrequency());
            return;
        }
        if (node.getLeft() != null) {
            generateCodes(node.getLeft(), code + "0");
        }
        if (node.getRight() != null) {
            generateCodes(node.getRight(), code + "1");
        }
    }

    public HashMap<Byte,String> getHuffmanCodes() {
        return huffmanCodes;
    }

    public void makeCompressFile(ListaEncadeadaSimplesDesordenada<Byte> bytes, HashMap<Byte, String> huffmanCodes, String namefile) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(namefile))) {
            Fila<Integer> fila = new Fila<>();
            serializeTree(dos);

            for (int i = 0; i < bytes.getTamanho(); i++) {
                try{
                Byte b = bytes.get(i);
                String code = huffmanCodes.get(b);
                    for (int j = 0; j < code.length(); j++) {
                        if (code.charAt(j) == '1') {
                            fila.guardeUmItem(1);
                        } else {
                            fila.guardeUmItem(0);
                        }
                    }
                while(fila.getTamanho()>=8){
                        byte newByte = 0;
                        StringBuilder sb = new StringBuilder();
                        for(int j = 0; j < 8; j++){
                            sb.append(fila.desenfilere());
                        }
                        newByte = (byte) Integer.parseInt(sb.toString(), 2);
                        dos.write(newByte);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            int paddingBits = 8 - fila.getTamanho();
            if (paddingBits < 8) {
                for (int j = 0; j < paddingBits; j++) {
                    try {
                        fila.guardeUmItem(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                byte newByte = 0;
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < 8; j++){
                        sb.append(fila.desenfilere());
                    }
                    newByte = (byte) Integer.parseInt(sb.toString(), 2);
                    dos.write(newByte);
                dos.writeByte(paddingBits); // Grava o padding
            } else {
               dos.writeByte(0);
            }



        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void serializeTree(DataOutputStream dos) throws IOException {
        serializeTreeRecursive(this.root, dos);
    }
    
    private void serializeTreeRecursive(HuffmanNode node, DataOutputStream dos) throws IOException {
        if (node.isLeaf()) {
            dos.writeBoolean(true); // Marcador para nó folha
            dos.writeByte(node.getValue());
        } else {
            dos.writeBoolean(false); // Marcador para nó interno
            serializeTreeRecursive(node.getLeft(), dos);
            serializeTreeRecursive(node.getRight(), dos);
        }
    }


    @Override
    public String toString() {
        // Verifica se a raiz está nula
        if (root == null) {
            return "";
        }
        // Usa um StringBuilder para construir a string de forma eficiente
        StringBuilder sb = new StringBuilder();
        preOrderToString(root, sb);
        return sb.toString();
    }

// Método auxiliar para realizar a pré-ordem recursiva
private void preOrderToString(HuffmanNode node, StringBuilder sb) {
    if (node == null) {
        return;
    }
    // Adiciona o valor do nó atual ao StringBuilder
    sb.append(node.getValue()).append(" ");
    // Chama recursivamente para o lado esquerdo e direito
    preOrderToString(node.getLeft(), sb);
    preOrderToString(node.getRight(), sb);
}
    

}
