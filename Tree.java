package RedBlackTree;

import java.time.temporal.Temporal;

import RedBlackTree.auxl.Stack;

public class Tree<T> {
    Node<T> root;
    int blackHeight = 0;
    public Tree(){
        this.blackHeight+=1;
        this.root = new Node<T>(null);
    }

    public Tree(T root){
        super();
        this.root= new Node<T>(root);    
    }

    public void insertRecursive(T value){
        if (this.root.getValue()==null){
            this.root = new Node<T>(value);
        }
        else{
            //Stack<T> myStack = new Stack<T>();
            Node<T> newNode = new Node<T>(value);
            this.root = insertRecursive(this.root, newNode);
            redBlackBalance(newNode);
            this.root.setColor(false);
        }
    }

    public void getBlackHeight(){

    }

    private Node<T> insertRecursive(Node<T> node, Node<T> nodeToInsert){
        if (node==null){
            nodeToInsert.setColor(true);
            return nodeToInsert;
        }
        if (node.compareTo(nodeToInsert.getValue())>=0){
            node.setLeftSon(insertRecursive(node.getLeftSon(), nodeToInsert));
        }
        else if (node.compareTo(nodeToInsert.getValue())<0){
            node.setRightSon(insertRecursive(node.getRightSon(), nodeToInsert)); 
        }  
        nodeToInsert.setParent(getParent(root, nodeToInsert.getValue()));
        return node;
    }

    public void redBlackBalance(Node<T> node){
        if (node == root) return;
        while (node.getParent().getColor()){
            Node<T> uncle; Boolean left = false;
            if (node.getParent().getParent().getRightSon()==node.getParent()){
                uncle = node.getParent().getParent().getLeftSon();
                left = false;
            } 
            else{
                uncle = node.getParent().getParent().getRightSon();
                left=true;
            }
            if (uncle!=null && uncle.getColor()){
                node.getParent().setColor(false);
                uncle.setColor(false);
                node.getParent().getParent().setColor(true);
                node = node.getParent().getParent();
            }
            else{
                if (left){
                    if (node == node.getParent().getRightSon()){
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    node.getParent().setColor(false);
                    node.getParent().getParent().setColor(true);
                    rotateRight(node.getParent().getParent());
                }
                else{
                    if (node == node.getParent().getLeftSon()){
                        node = node.getParent();
                        rotateRight(node);
                    }
                    node.getParent().setColor(false);
                    node.getParent().getParent().setColor(true);
                    rotateLeft(node.getParent().getParent());
                }
            }
            if (node==root){
                break;
            }
        }
    }

    public Node<T> getParent(Node<T> node, T value){
        if (this.root.compareTo(value)==0) return this.root; 
        while (node!=null && node.getValue()!=value){
            if (node.getLeftSon()!=null && node.getLeftSon().compareTo(value)==0) return node;
            if (node.getRightSon()!=null && node.getRightSon().compareTo(value)==0) return node;
            if (((Comparable<T>) node.getValue()).compareTo(value)>0) {
                node = node.getLeftSon();
            }
            else{
                node = node.getRightSon();
            }
        }
        if (node==null){
            return null;
        }
        return node;
    }

    public void shiftColors(Node<T> node){
        node.setColor(true);
        if (node.getLeftSon()!=null) node.getLeftSon().setColor(false);
        if (node.getRightSon()!=null) node.getRightSon().setColor(false);
    }

    public void rotateLeft(Node<T> rootNode){
        Node<T> temp1 = rootNode.getRightSon();
        rootNode.setRightSon(temp1.getLeftSon());
        if (temp1.getLeftSon()!=null){
            temp1.getLeftSon().setParent(rootNode);
        }
        temp1.setParent(rootNode.getParent());
        if (rootNode.getParent()==null){
            root = temp1;
        }
        else if (rootNode==rootNode.getParent().getLeftSon()){
            rootNode.getParent().setLeftSon(temp1);
        }
        else{
            rootNode.getParent().setRightSon(temp1);
        }
        temp1.setLeftSon(rootNode);
        rootNode.setParent(temp1);
    }

    public void rotateRight(Node<T> rootNode){
        Node<T> temp1 = rootNode.getLeftSon();
        rootNode.setLeftSon(temp1.getRightSon());
        if (temp1.getRightSon()!=null){
            temp1.getRightSon().setParent(rootNode);
        }
        temp1.setParent(rootNode.getParent());
        if (rootNode.getParent()==null){
            root = temp1;
        }
        else if (rootNode==rootNode.getParent().getLeftSon()){
            rootNode.getParent().setLeftSon(temp1);
        }
        else{
            rootNode.getParent().setRightSon(temp1);
        }
        temp1.setRightSon(rootNode);
        rootNode.setParent(temp1);
    }

    public void BFS(){
        if (this.root.getValue()!=null){
            Queue<T> myQueue = new Queue<T>();
            myQueue.push(this.root);
            this.BFSRecursive(myQueue);
        }
    }

    private void BFSRecursive(Queue<T> myQueue){
            if (myQueue.empty()==true){
                return;
            }
            Node<T> temp = myQueue.front();
            System.out.print(temp.getValue()+" ");
            myQueue.pop();
            if (temp.getLeftSon().getValue()!=null){
                myQueue.push(temp.getLeftSon());
            }
            if (temp.getRightSon().getValue()!=null){
                myQueue.push(temp.getRightSon());
            }
            this.BFSRecursive(myQueue);
    }

    public void inOrder(){
        this.ordered(this.root);
    }

    public void ordered(Node<T> node){
        if (node.getValue() != null){
            ordered(node.getLeftSon());
            System.out.println(node.getValue());
            ordered(node.getRightSon());
        }
    }

    public void preOrder(){
        this.preOrdered(this.root);
    }

    public void preOrdered(Node<T> node){
        if (node != null){
            System.out.println(node.getValue() + "  " + node.getColor());
            this.preOrdered(node.getLeftSon());
            this.preOrdered(node.getRightSon());
        }
    }

    public void postOrder(){
        this.postOrdered(this.root);
    }

    public void postOrdered(Node<T> node){
        if (node != null){
            this.postOrdered(node.getLeftSon());
            this.postOrdered(node.getRightSon());
            System.out.println(node.getValue());
        }
    }

    public void callSearch(T value){
        this.search(this.root, value);
    }

    public Node<T> search(Node<T> node, T value){
        while (node.getValue()!=null && node.getValue()!=value){
            if (((Comparable<T>) node.getValue()).compareTo(value)>0) {
                node = node.getLeftSon();
            }
            else{
                node = node.getRightSon();
            }
        }
        if (node.getValue()==null){
            return null;
        }
        return node;
    }

    public Node<T> findSuccessor(Node<T> node){
        if (node.getLeftSon().getValue()==null){
            return node;
        }
        node = findSuccessor(node.getLeftSon());
        return node;
    }

    public Node<T> deleteSuccessor(Node<T> node){
        if (node.getLeftSon().getValue() == null){
            return node.getRightSon(); 
        }
        node.setLeftSon(deleteSuccessor(node.getLeftSon()));
        return node;
    }

    public void deleteNode(T value){
        if (this.root.getValue() == null){
            this.root = new Node<T>(value);
        }
        else{
            this.root = deleteNode(this.root, value);
           
        }
    }

    public Node<T> deleteNode(Node<T> node, T value){
        if (node == null){
            return null;
        }
        if (node.compareTo(value)>0){
            node.setLeftSon(deleteNode(node.getLeftSon(), value));
            System.out.println("");
        }
        if (node.compareTo(value)<0){
            node.setRightSon(deleteNode(node.getRightSon(), value));
            System.out.println("");
        }
        if (node.compareTo(value)==0){
            if (node.getRightSon()==null){
                return node.getLeftSon();
            }
            if (node.getLeftSon()==null){
                return node.getRightSon();
            }
            Node<T> temp = node;
            if (node == this.root){
                this.root = findSuccessor(temp.getRightSon());
                this.root.setRightSon(deleteSuccessor(temp.getRightSon()));
                this.root.setLeftSon(temp.getLeftSon());
            }
            else{
                node = findSuccessor(temp.getRightSon());
                node.setRightSon(deleteSuccessor(temp.getRightSon()));//no puede haber mas a la izquierda, porque ese sería el menor que estaría en node
                node.setLeftSon(temp.getLeftSon());
            }
        }
        return node;
    }

    public void redBlackBalanceDelete(){

    }
}
