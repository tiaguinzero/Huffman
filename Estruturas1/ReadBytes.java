package Estruturas1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class ReadBytes {
    private String filePath;
    private Pilha<Byte> bytes;
    private HashMap<Byte, Integer> frequencyMap;

    public ReadBytes() {
        this.bytes = new Pilha<>();
        this.frequencyMap = new HashMap<>();
    }

    public ReadBytes(String filePath) {
        this();
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Pilha<Byte> getBytes() {
        return this.bytes;
    }

    public void readBytes() {
        try (RandomAccessFile file = new RandomAccessFile(this.filePath, "r")) {
            long fileSize = file.length();
            for (int i = 0; i < fileSize; i++) {
                byte b = file.readByte();
                frequencyMap.put(b, frequencyMap.getOrDefault(b, 0) + 1);
                System.out.printf("Primeiro porcentagem");
                System.out.printf("%.2f%%\n", (i * 100.0) / fileSize);
                try {
                    this.bytes.guardeUmItem(b); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            try {
                this.bytes.inverta();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Byte, Integer> getFrequencyMap() {
        return this.frequencyMap;
    }
}
