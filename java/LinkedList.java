public class LinkedList<E> {

    private Node<E> head;
    private int length;

    public LinkedList() {
        // default
    }

    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.setNext(head);
        head = newNode;
        length++;
    }

    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            length++;
            return;
        }
        Node<E> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
        length++;
    }

    public void add(int index, E value) {
        if (index < 0 || index > length) throw new IllegalArgumentException("Index out of bounds");

        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == length) {
            addLast(value);
            return;
        }
        Node<E> newNode = new Node<>(value);
        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        length++;
    }

    public int search(E value) {
        Node<E> current = head;
        for (int i = 0; i < length; i++) {
            if (current.getValue().equals(value)) {
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }

    public E removeFirst() {
        if (head == null) return null;
        E value = head.getValue();
        head = head.getNext();
        length--;
        return value;
    }

    public E removeLast() {

        if (head == null) return null;

        E valOfRemoved;

        if (length == 1) {
            valOfRemoved = head.getValue();
            head = null;
            length--;
            return valOfRemoved;
        }

        Node<E> current = head;
        for (int i = 0; i < length - 2; i++) {
            current = current.getNext();
        }
        valOfRemoved = current.getNext().getValue();
        current.setNext(null);
        length--;
        return valOfRemoved;
    }

    public E replace(int index, E value) {
        if (index < 0 || index > length - 1) throw new IllegalArgumentException("Index out of bounds");
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        E valOfReplace = current.getValue();
        current.setValue(value);
        return valOfReplace;
    }

    public E remove(int index) {
        if (index < 0 || index > length - 1) throw new IllegalArgumentException("Index out of bounds");
        if (index == 0) {
            return removeFirst();
        }
        if (index == length - 1) {
            return removeLast();
        }

        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        E valOfRemoved = current.getNext().getValue();
        current.setNext(current.getNext().getNext());
        length--;
        return valOfRemoved;
    }

    public int removeVal(E value) {
        if (head == null) return -1;
        
        if (head.getValue().equals(value)) {
            removeFirst();
            return 0;
        }

        Node<E> current = head;
        int index = 1;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(value)) { 
                current.setNext(current.getNext().getNext());
                length--;
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }
}