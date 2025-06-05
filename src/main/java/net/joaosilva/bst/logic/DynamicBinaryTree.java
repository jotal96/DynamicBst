package net.joaosilva.bst.logic;

import net.joaosilva.bst.logic.lambdaFunctions.NodeComparator;

public class DynamicBinaryTree<T> {
    private Node<T> initialNode;
    private NodeComparator<T> comparator;

    public DynamicBinaryTree(NodeComparator<T> comparator) {
        this.comparator = comparator;
    }

    public void addNode(Node<T> node){
        node.setComparator(this.comparator);
        Node<T> aux = find(node.getValue());
        if(aux!=null){
            return;
        }

        if(initialNode==null){
            initialNode = node;
        }else{
            initialNode.add(node);
        }
    }

    public Node<T> find(T value){
        if(this.initialNode==null)
            return null;

        return this.initialNode.find(value);
    }

    public Node<T> findParent(T value){
        if(this.initialNode==null)
            return null;

        // o node que estamos a pesquisar é o root?

        // se sim devolve o root, pois ele não tem parent
        if(this.initialNode.getValue().equals(value)){
            return this.initialNode;
        }

        return this.initialNode.findParent(value);
    }

    public Node<T> remove(T value){
        if(this.initialNode==null)
            return null;

        // se entrar na condição, quer dizer que queremos remover o initialNode
        if(this.initialNode.getValue().equals(value)){
            Node<T> current = this.initialNode;
            Node<T> left = this.initialNode.getLeft();
            Node<T> right = this.initialNode.getRight();

            current.setLeft(null);
            current.setRight(null);

            if(left!=null && right!=null){
                this.initialNode = left;
                this.initialNode.add(right);
            }
            else if(right==null){
                this.initialNode = left;
            }
            else if(left==null){
                this.initialNode = right;
            }
        }

        Node<T> parent = findParent(value);
        return this.initialNode.remove(value, parent);
    }

    public void print(){
        this.initialNode.printTree(this.initialNode, "", true);
    }
}
