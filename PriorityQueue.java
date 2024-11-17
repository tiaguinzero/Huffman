public class PriorityQueue {
    private HuffmanNode[] queue;
    private int size;

    public PriorityQueue(int capacity) {
        queue = new HuffmanNode[capacity];
        size = 0;
    }

    public void enqueue(HuffmanNode node) {
        queue[size] = node;
        size++;
        for (int i = size - 1; i > 0; i--) {
            if (queue[i].frequency < queue[i - 1].frequency) {
                HuffmanNode temp = queue[i];
                queue[i] = queue[i - 1];
                queue[i - 1] = temp;
            }
        }
    }

    public HuffmanNode dequeue() {
        if (size == 0) {
            return null;
        }
        HuffmanNode node = queue[0];
        for (int i = 1; i < size; i++) {
            queue[i - 1] = queue[i];
        }
        size--;
        return node;
    }

    public int size() {
        return size;
    }
}
