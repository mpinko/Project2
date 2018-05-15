package edu.gcccd.csis;

final class Node<E> {
    private static long counter = 0;   // for instrumentation only
    private E element;
    private Node<E> next;

    /**
     * There is no setter method implemented, to set an element.
     * Instead, the node constructor accepts an element.
     *
     * @param element the data content that is stored in this node.
     */
    Node(final E element) {
        this.element = element;
    }

    public static void resetCounter() {
        counter = 0;
    }

    public static long getCounter() {
        return counter;
    }

    E getElement() {
        return element;
    }

    Node<E> getNext() {
        Node.counter++;
        return next;
    }

    void setNext(final Node<E> next) {
        this.next = next;
    }
}