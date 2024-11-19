package Estruturas1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadBytes {
    private String filePath;
    private ListaEncadeadaSimplesDesordenada<Byte> bytes;

    public ReadBytes() {
        this.bytes = new ListaEncadeadaSimplesDesordenada<>();
    }

    public ReadBytes(String filePath) {
        this();
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ListaEncadeadaSimplesDesordenada<Byte> getBytes() {
        return this.bytes;
    }

    public void readBytes() {
        try (RandomAccessFile file = new RandomAccessFile(this.filePath, "r")) {
            long fileSize = file.length();
            for (int i = 0; i < fileSize; i++) {
                byte b = file.readByte();
                
                System.out.println(b);

                try {
                    this.bytes.guardeNoFinal(b); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
