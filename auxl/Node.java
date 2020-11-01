package RedBlackTree.auxl;

public class Node<T> {
    private T value ;
    private Node<T> next;

    public Node(T value){
        this.value = value;
        this.next = null;
    }
    public Node(T value, Node<T> next){
        this(value);
        this.next = next;
    }

    public T getValue(){
        return this.value;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }

    public Node<T> getNode(){
        return this.next;
    }
}
