package net.joaosilva.bst.logic;

public interface INode<T> {
    void add(Node<T> nodeToAdd);

    Node<T> find(T value);
    Node<T> findParent(T value);
    Node<T> remove(T value, Node<T> parent);
}
