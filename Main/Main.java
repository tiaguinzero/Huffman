package Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo para testes
        String filePath = "IMG_4657.PNG";

        try {
            // Usando FileInputStream para ler todo o conteúdo do arquivo em bytes
            File file = new File(filePath);
            byte[] fileBytes = new byte[(int) file.length()];

            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileBytes);
            fileInputStream.close();

            System.out.println(fileBytes.length);
        } catch (IOException e) {
            System.out.println("Erro ao abrir ou ler o arquivo");
        } finally {
            // Fechar a classe ReadFile (caso você a utilize)
            System.out.println("Fechando o arquivo");
        }
    }
}
