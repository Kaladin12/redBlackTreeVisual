package RedBlackTree.auxl;

public class Stack<T> {
    RedBlackTree.Node<T> top;
    public Stack(){
        this.top = null;
    }

    public Stack(T topValue){
        this.top = new RedBlackTree.Node<T>(topValue);
    }
    public void push(RedBlackTree.Node<T> node){
        if (this.top == null){
            this.top = node;
        }else{
            RedBlackTree.Node<T> temp = this.top;
            this.top = node;
            this.top.setNext(temp);
            System.out.println();
        } 
    }
    public void push(T newValue){
        //RedBlackTree.Node<T> temp = this.top;
        push(new RedBlackTree.Node<T>(newValue));
        //this.top.setNext(temp);
    }
    public T pop(){
        if (this.top == null){
            Integer a= -1;
            return (T)a;
        }
        RedBlackTree.Node<T> temp = this.top;
        this.top = this.top.getNode();
        return temp.getValue();
    }
}
