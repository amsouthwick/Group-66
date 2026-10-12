/**
 * A linked chain collection that stores objects using linked nodes.
 * This collection is unordered, permits duplicates
 *
 * @param <T> the type of elements stored in the chain
 */
public class LinkedChain<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Creates an empty linked chain.
     */
    public LinkedChain() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Appends a non-null item to the end of the chain.
     * @param item the element to add; if null, the chain remains unchanged
     */
    public void add(T item) {
        if (item == null) {
            return; 
        }
        
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    /**
     * Inserts an item at a specific, valid chain position.
     * @param index the position at which to insert the item
     * @param item  the element to add; if null, the chain remains unchanged
     * @throws IndexOutOfBoundsException if the index is negative or 
     * greater
     */
    public void add(int index, T item) {
        if (item == null) {
            return;
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        Node<T> newNode = new Node<>(item);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            
            if (newNode.getNext() == null) {
                tail = newNode;
            }
        }
        size++;
    }

    /**
     * Retrieves the element at the specified position.
     * @param index the position of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index 
     * is negative or greater than or equal to size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    /**
     * Removes and returns the element at the specified position.
     * @param index the position of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index 
     * is negative or greater than or equal to size
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        T removedData;
        if (index == 0) {
            removedData = head.getData();
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            removedData = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            
            if (current.getNext() == null) {
                tail = current;
            }
        }
        size--;
        return removedData;
    }

    /**
     * Removes the first occurrence of the specified item from the chain.
     * @param item the element to remove
     * @return true if the item was found and removed, false otherwise
     */
    public boolean remove(T item) {
        if (item == null || isEmpty()) {
            return false;
        }
        
        if (head.getData().equals(item)) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }
        
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(item)) {
                current.setNext(current.getNext().getNext());
                if (current.getNext() == null) {
                    tail = current;
                }
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Checks if the chain contains the specified item.
     * @param item the element to search for
     * @return true if an equal item is present, false otherwise
     */
    public boolean contains(T item) {
        if (item == null) {
            return false;
        }
        
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(item)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Counts the total number of occurrences of the specified item in the chain.
     * @param item the element to count
     * @return the number of times the item appears in the chain
     */
    public int count(T item) {
        if (item == null) {
            return 0;
        }
        
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(item)) {
                count++;
            }
            current = current.getNext();
        }
        return count;
    }

    /**
     * Returns the total number of elements currently stored in the chain.
     * @return the size of the chain
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the chain contains no elements.
     * @return true if the chain is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from the chain and resets its state.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}