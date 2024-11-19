package Main;
import Estruturas.ListaEncadeadaSimplesDesordenada;
import Estruturas.ReadBytes;
import Estruturas.HashMap;

public class Main {
    public static void main(String[] args) {
        String filePath = "projeto3_topicosIA.pdf";

        ReadBytes reader = new ReadBytes(filePath);
        reader.readBytes();
        ListaEncadeadaSimplesDesordenada<Byte> bytes = reader.getBytes();

        // Construir a tabela de frequência
        HashMap<Byte, Integer> frequencyMap = new HashMap<>();

        for(int i = 0; i < bytes.getTamanho(); i++){
            try {
                Byte b = bytes.get(i);
                if(frequencyMap.contemKey(b)){
                    frequencyMap.put(b, frequencyMap.get(b) + 1);
                } else {
                    frequencyMap.put(b, 1);
                }
            } catch (Exception e) {
                
            }
        }
    }
}
