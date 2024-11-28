package Estruturas1;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBytesWListaEncadeada {

    public WriteBytesWListaEncadeada(String namefile, ListaEncadeadaSimplesDesordenada<Byte> listabytes) {
        try (FileOutputStream fos = new FileOutputStream(namefile)) { // Usa try-with-resources
            for (int i = 0; i < listabytes.getTamanho(); i++) {
                try {
                    fos.write(listabytes.get(i)); // Escreve cada byte na saída       
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever bytes no arquivo: " + e.getMessage());
        }
    }
}
