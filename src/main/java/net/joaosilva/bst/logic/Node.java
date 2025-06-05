package net.joaosilva.bst.logic;

import net.joaosilva.bst.logic.lambdaFunctions.NodeComparator;

/***
 * Node of the binary tree
 *
 * @param <T> the type of object the node saves and compares
 */
public class Node<T> implements INode<T>{

    /***
     * Value that the Node saves
     */
    private T value;

    // left and right below are needed for tree logic

    private Node<T> left;

    private Node<T> right;

    /***
     * Function used to compare Nodes
     */
    private NodeComparator<T> comparator;


    /**
     * Constructors
     */
    public Node(){}

    /***
     * Getters and Setters
     */
    public Node(T value) {
        this.value = value;
    }

    public NodeComparator<T> getComparator() {
        return comparator;
    }

    public void setComparator(NodeComparator<T> comparator) {
        this.comparator = comparator;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void printTree(Node<T> node, String prefix, boolean isLeft) {
        if (node != null) {
            printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.value);
            printTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    /**
     *  ------------------------------------------------------------
     */

    @Override
    public void add(Node<T> node){
        // the value that we are trying to add, is it lesser than the value of this node
        if(comparator.compare(this.value, node.getValue()) < 0){
            // the node to add is lesser, is the left free?
            if(left==null){
                // if left is free, append Node to left
                left = node;
            }
            else{
                // if left is not free, left will try to add it
                left.add(node);
            }
        }
        else{
            // the node to add is greater or equal, is the right free?
            if(right==null){
                // if right is free, append Node to right
                right = node;
            }
            else{
                // if right is not free, right will try to add it
                right.add(node);
            }
        }
    }

    @Override
    public Node<T> find(T value) {
        // o valor que queremos está neste node?
        if(this.value.equals(value)){

            // se sim retorna o node
            return this;
        }

        // o valor que queremos pesquisar está para a esquerda ou direita?

        // se left ou right forem null, quer dizer que o valor não está na árvore
        // se não forem null, continuamos a procura
        if(comparator.compare(this.value, value) < 0){
            return this.left!=null?this.left.find(value):null;
        }else{
            return this.right!=null?this.right.find(value):null;
        }
    }

    @Override
    public Node<T> findParent(T value) {
        if(comparator.compare(this.value, value) < 0){
            // o valor está no left? se sim somos o parent
            if(this.left!=null && this.left.getValue().equals(value)){
                return this;
            }else{
                // se o left não for null procura, se for null devolvemos null
                return this.left!=null?this.left.findParent(value):null;
            }

        }else{
            // o valor está na right? se sim somos o parent
            if(this.right!=null && this.right.getValue().equals(value)){
                return this;
            }else{
                // se a right não for null procura, se for null devolvemos null
                return this.right!=null?this.right.findParent(value):null;
            }
        }
    }

    @Override
    public Node<T> remove(T value, Node<T> parent) {

        if(this.value.equals(value)){
            // se chegarmos aqui quer dizer que temos de nos remover a nós mesmos
            if(parent!=null){
                Boolean amILeft = null;
                if(parent.left !=null && parent.left.getValue().equals(value)){
                    amILeft = true;
                }
                else{
                    amILeft = false;
                }


                if(left == null && right==null){
                    // não temos herdeiros

                    if(amILeft!=null){
                        if(amILeft){
                            parent.setLeft(null);

                        }else{
                            parent.setRight(null);
                        }
                        return this;
                    }
                }else if((left!=null && right==null) || (left==null && right!=null)){
                    // se chegamos aqui temos de nos remover e temos herdeiros
                    // buscar o child
                    Node<T> childNode = left!=null?left:right;

                    // dependendo de ser left ou right, substituo-me a mim pelo childNode
                    if (amILeft) {
                        parent.setLeft(childNode);
                    } else {
                        parent.setRight(childNode);
                    }

                    setRight(null);
                    setLeft(null);

                    return this;
                }
                else{
                    Node<T> nodeLeft = left;
                    Node<T> nodeRight = right;

                    if(amILeft){
                        parent.left = nodeLeft;
                        parent.add(nodeRight);
                    }else{
                        parent.right = nodeLeft;
                        parent.add(nodeRight);
                    }

                    setRight(null);
                    setLeft(null);

                    return this;
                }

            // end if(parent!=null)
            }

        // end if(this.value.equals(value))
        } else{
            // se chegamos aqui não somos o valor a sair


            if(comparator.compare(this.value, value) < 0){
                // se o valor estiver à esquerda, chama o remove do left
               return left!=null?left.remove(value, parent):null;
            }else{
                // se estiver à direita chama o right
                return right!=null?right.remove(value, parent):null;
            }
        }

        return null;
    }
}
