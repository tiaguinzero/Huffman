package Main;

import java.io.RandomAccessFile;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("entrada.txt");
            if (!file.exists()) {
                System.out.println("Arquivo não encontrado. Criando um novo arquivo chamado 'entrada.txt'.");
                file.createNewFile(); // Cria um arquivo vazio
            }

            RandomAccessFile inputFile = new RandomAccessFile(file, "r");
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = inputFile.readLine()) != null) {
                text.append(line).append("\n");
            }
            inputFile.close();

            System.out.println("Conteúdo do arquivo:");
            System.out.println(text);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}