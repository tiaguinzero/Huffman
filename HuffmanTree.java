public class HuffmanTree {
    private HuffmanNode root;
    private char[] characters;
    private String[] codes;

    public HuffmanTree(String text) {
        char[] uniqueChars = getUniqueCharacters(text);
        int[] frequencies = calculateFrequencies(text, uniqueChars);
        PriorityQueue pq = new PriorityQueue(uniqueChars.length);

        for (int i = 0; i < uniqueChars.length; i++) {
            pq.enqueue(new HuffmanNode(uniqueChars[i], frequencies[i]));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.dequeue();
            HuffmanNode right = pq.dequeue();
            HuffmanNode combined = new HuffmanNode('\0', left.frequency + right.frequency);
            combined.left = left;
            combined.right = right;
            pq.enqueue(combined);
        }

        root = pq.dequeue();
        characters = uniqueChars;
        codes = new String[uniqueChars.length];
        generateCodes(root, "");
    }

    private char[] getUniqueCharacters(String text) {
        StringBuilder uniqueChars = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (uniqueChars.indexOf(String.valueOf(text.charAt(i))) == -1) {
                uniqueChars.append(text.charAt(i));
            }
        }
        char[] result = new char[uniqueChars.length()];
        for (int i = 0; i < uniqueChars.length(); i++) {
            result[i] = uniqueChars.charAt(i);
        }
        return result;
    }

    private int[] calculateFrequencies(String text, char[] uniqueChars) {
        int[] frequencies = new int[uniqueChars.length];
        for (int i = 0; i < uniqueChars.length; i++) {
            int count = 0;
            for (int j = 0; j < text.length(); j++) {
                if (uniqueChars[i] == text.charAt(j)) {
                    count++;
                }
            }
            frequencies[i] = count;
        }
        return frequencies;
    }

    private void generateCodes(HuffmanNode node, String code) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            for (int i = 0; i < characters.length; i++) {
                if (characters[i] == node.character) {
                    codes[i] = code;
                    break;
                }
            }
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    public void printCodes() {
        for (int i = 0; i < characters.length; i++) {
            System.out.println(characters[i] + ": " + codes[i]);
        }
    }

    public String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < characters.length; j++) {
                if (text.charAt(i) == characters[j]) {
                    encoded.append(codes[j]);
                    break;
                }
            }
        }
        return encoded.toString();
    }

    public String decode(String encodedText) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        for (int i = 0; i < encodedText.length(); i++) {
            current = (encodedText.charAt(i) == '0') ? current.left : current.right;
            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root;
            }
        }
        return decoded.toString();
    }
}
