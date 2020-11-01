package RedBlackTree;

public class Node<T> implements Comparable<T> {
    private T value ;
    private Node<T> leftSon, rightSon;
    private Boolean red = false; //false es negro, true es rojo
    private Node<T> next;
    private Node<T> parent;

    public Node(T value){
        this.value = value;
        this.leftSon = null;
        this.rightSon = null;
        this.next = null;
        this.parent = null;
    }

    public void setParent(Node<T> node){
        this.parent = node;
    }

    public Node<T> getParent(){
        return this.parent;
    }
    public void setNext(Node<T> next){
        this.next = next;
    }

    public Node<T> getNode(){
        return this.next;
    }

    public void setColor(Boolean color){
        this.red = color;
    }

    public Boolean getColor(){
        return this.red;
    }

    public T getValue(){
        return this.value;
    }

    public void setValue(T value){
        this.value = value;
    }

    public void setRightSon(Node<T> rightSon){
        this.rightSon = rightSon;
    }

    public void setLeftSon(Node<T> leftSon){
        this.leftSon = leftSon;
    }

    public Node<T> getLeftSon(){
        return this.leftSon;
    }

    public Node<T> getRightSon(){
        return this.rightSon;
    }

    @Override
    public int compareTo(T value){
        try {
            Integer temp = Integer.parseInt(this.toString());
            Integer temp2 = Integer.parseInt(value.toString());
            return temp.compareTo(temp2);
        } catch (Exception e) {}
        String temp = this.toString();
        String temp2 = value.toString();
        return temp.compareTo(temp2);
    }

    public String toString(){
        return this.value.toString();
    }
}

