package RedBlackTree;

public class Queue<T> {
    Node<T> top;
    public Queue(){
        this.top = null;
    }

    public Queue(T topValue){
        this.top = new Node<T>(topValue);
        this.top.setNext(this.top);
    }


    public void push(Node<T> newNode){
        if (this.top!=null){
            Node<T> temp =  this.top;
            while (temp.getNode()!=this.top){
                
                temp = temp.getNode();

            }
            newNode.setNext(this.top);
            temp.setNext(newNode);
        }
        else{
            this.top = newNode;
            this.top.setNext(this.top);
        }
    }

    public void push(T newvalue){
        if (this.top!=null){
            Node<T> temp =  this.top;
            while (temp.getNode()!=this.top){
                temp = temp.getNode();
            }
            Node<T> newNode = new Node<T>(newvalue);
            newNode.setNext(this.top);
            temp.setNext(newNode);
        }
        else{
            this.top = new Node<T>(newvalue);
            this.top.setNext(this.top);
        }
    }

    public Node<T> pop(){
        if (this.top == null){
            Integer a= -1;
            return new Node<T>((T)a);
        }
        Node<T> popped = this.top;
        Node<T> temp = this.top.getNode();
        if (temp == this.top){
            this.top = null;
        }
        else{
            while (temp.getNode() != this.top){
            temp = temp.getNode();
            }
            this.top = this.top.getNode();
            temp.setNext(this.top);
        }
        return popped;
    }

    public int size(){
        int size = 0;
        Node<T> temp =  this.top;
        if (this.top!=null){
            size+=1;
            while (temp.getNode()!=null){
                size+=1;
                temp = temp.getNode();
            }
        }
        return size;
    }

    public Boolean empty(){
        if (this.top!=null){
            return false;
        }
        return true;
    }

    public Node<T> front(){
        if (this.top != null){
            return this.top;
        }
        Integer a = -1;
        return new Node<T>((T)a);
    }

    public T Peek(int elements){
        if (this.top!=null){
            Node<T> temp = this.top;
            for (int i = 0; i < elements; i++) {
                temp = temp.getNode();
            }
            return temp.getValue();
        }
        Integer a = -1;
        return (T)a;
    }
}
