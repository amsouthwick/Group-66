/**
 * Represents a single node in a generic linked chain.
 *
 * @param <T> the type of data stored in this node
 */
public class Node<T> {
    private T data;
    private Node<T> nextNode;

    /**
     * Creates a new node with the specified data and no next node.
     * @param data the data to store in the node
     */
    public Node(T data) {
        this.data = data;
        this.nextNode = null;
    }

    /**
     * Creates a new node with the specified data and next node reference.
     *
     * @param data the data to store in the node
     * @param nextNode the next node in the chain
     */
    public Node(T data, Node<T> nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    /**
     * Gets the data stored in this node.
     * @return the data stored in the node
     */
    public T getData() {
        return this.data;
    }

    /**
     * Sets the data stored in this node
     * @param data the new data to store
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the next node in the chain.
     * @return the reference to the next node, or null if this is the last node
     */
    public Node<T> getNext() {
        return this.nextNode;
    }

    /**
     * Sets the next node in the chain.
     * @param nextNode the reference to the next node
     */
    public void setNext(Node<T> nextNode) {
        this.nextNode = nextNode;
    }
}