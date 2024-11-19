package Main;

import Huffman.*;
import java.io.RandomAccessFile;

public class Descompressor{
    public static void main(String[] args) {
        String compressedFile = "saida.bin";
        String decompressedFile = "output.txt";

        HuffmanDescompressor decompressor = new HuffmanDescompressor(compressedFile, decompressedFile);
        decompressor.decompress();
    }
}
