class Node {
    constructor(value, next=null){
        this.value = value;
        this.next = next;
        this.leftSon = null;
        this.rightSon = null;
        this.parent = null;
        this.red = false;
        this.item = null;
        this.x = null;
        this.y = null;
        this.height = 0;
        this.positioInLevel = 0;
    }
    print() {
        console.log(this.value);
    }
    setPositionInLevel(pos){
        this.positioInLevel = pos;
    }
    getPositionInLevel(){
        return this.positioInLevel;
    }
    setHeight(value){
        this.height = value;
    }
    getHeight(){
        return this.height;
    }
    setParent(node){
        this.parent = node;
    }
    getParent(){
        return this.parent;
    }
    setNext(node){
        this.next = node;
    }
    getNext(){
        return this.next;
    }
    setColor(color){
        this.red = color;
    }
    getColor(){
        return this.red;
    }
    setValue(value){
        this.value = value;
    }
    getValue(){
        return this.value;
    }
    setRightSon(node){
        this.rightSon = node;
    }
    setLeftSon(node){
        this.leftSon = node;
    }
    getRightSon(){
        return this.rightSon;
    }
    getLeftSon(){
        return this.leftSon;
    }
    comparable(value){
        return this.value>value ? 1: this.value<value?-1:0;
    }
    setItem(){

    }
}

class Tree {
    constructor(DaRoot=null){
        this.blackHeight = 1;
        this.root = new Node(DaRoot);
    }
    insert(value){
        if (this.root.getValue()===null){
            this.root = new Node(value);
            //drawer.createCircle(this.root);
        }
        else{
            let newNode = new Node(value);
            this.root = this.insertRecursive(this.root, newNode);
            this.redBlackBalance(newNode);
            this.root.setColor(false);
            //console.log(newNode);
            //drawer.createCircle(newNode);
        }
        this.root.setHeight(0); this.root.setPositionInLevel(0);
        this.setHeights(this.root);
        this.setPositions(this.root);
        //this.fixChildren(this.root);
        
    }
    setHeights(node){
        if (node!=null){
            this.setHeights(node.getLeftSon());
            if (node!=this.root){
                let height = node.getParent().getHeight();
                node.setHeight(height + 1);
                //console.log(node.getHeight(), node.getParent().getHeight());
            }
            this.setHeights(node.getRightSon());
        }
    }
    setPositions(node){
        if (node!=null){
            this.setPositions(node.getLeftSon());
            if (node!=this.root){
                let pos = 2 * node.getParent().getPositionInLevel();
                node.setPositionInLevel(node.getParent().getLeftSon()==node ? pos : pos+1);
            }
            this.setPositions(node.getRightSon());
        }
    }
    insertRecursive(node, nodeToInsert){
        if (node==null){
            nodeToInsert.setColor(true);
            return nodeToInsert;
        }
        if (node.comparable(nodeToInsert.getValue())>=0){
            node.setLeftSon(this.insertRecursive(node.getLeftSon(), nodeToInsert));
        }
        else if (node.comparable(nodeToInsert.getValue())<0){
            node.setRightSon(this.insertRecursive(node.getRightSon(), nodeToInsert));
        }
        let parent = this.getParent(this.root, nodeToInsert.getValue());
        nodeToInsert.setParent(parent);
        //console.log("Parent: ",parent);
        return node;
    }
    getParent(node, value){
        if (this.root.comparable(value)==0){
            return this.root;
        }
        while (node!=null && node.getValue()!=value){
            if (node.getLeftSon()!=null && node.getLeftSon().comparable(value)==0){return node;}
            if (node.getRightSon()!=null && node.getRightSon().comparable(value)==0){return node;}
            if (node.comparable(value)>0){
                node = node.getLeftSon();
            }
            else{
                node = node.getRightSon();
            }
        }
        if (node==null){return null;}
        return node;
    }
    rotateLeft(rootNode){
        let temp1 = rootNode.getRightSon();
        rootNode.setRightSon(temp1.getLeftSon());
        if (temp1.getLeftSon()!=null){
            temp1.getLeftSon().setParent(rootNode);
        }
        temp1.setParent(rootNode.getParent());
        if (rootNode.getParent()==null){
            this.root = temp1;
        }
        else if (rootNode == rootNode.getParent().getLeftSon()){
            rootNode.getParent().setLeftSon(temp1);
        }
        else{
            rootNode.getParent().setRightSon(temp1);
        }
        temp1.setLeftSon(rootNode);
        rootNode.setParent(temp1);
        
    }
    rotateRight(rootNode){
        let temp1 = rootNode.getLeftSon();
        rootNode.setLeftSon(temp1.getRightSon());
        if (temp1.getRightSon()!=null){
            temp1.getRightSon().setParent(rootNode);
        }
        temp1.setParent(rootNode.getParent());
        if (rootNode.getParent()==null){
            this.root = temp1;
        }
        else if (rootNode == rootNode.getParent().getLeftSon()){
            rootNode.getParent().setLeftSon(temp1);
        }
        else{
            rootNode.getParent().setRightSon(temp1);
        }
        temp1.setRightSon(rootNode);
        rootNode.setParent(temp1);
    }
    redBlackBalance(node){
        if (node == this.root){return;}
        while (node.getParent().getColor()==true) {
            let uncle = null;
            let left = false;
            if (node.getParent().getParent().getRightSon()==node.getParent()){
                uncle = node.getParent().getParent().getLeftSon();
                left = false;
            }
            else{
                uncle = node.getParent().getParent().getRightSon();
                left = true;
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
                        this.rotateLeft(node);
                    }
                    node.getParent().setColor(false);
                    node.getParent().getParent().setColor(true);
                    this.rotateRight(node.getParent().getParent());
                }
                else{
                    if (node == node.getParent().getLeftSon()){
                        node = node.getParent();
                        this.rotateRight(node);
                    }
                    node.getParent().setColor(false);
                    node.getParent().getParent().setColor(true);
                    this.rotateLeft(node.getParent().getParent());
                }
            }
            
            if (node ==this.root){break;}
        }
    }
    preOrder(){
        this.preOrdered(this.root);
    }
    preOrdered(node){
        if (node!=null){
            console.log(node.getValue(), node.getColor(), node.getHeight(),node.getPositionInLevel());
            this.preOrdered(node.getLeftSon());
            this.preOrdered(node.getRightSon());
        }
    }
    BFS(){
        if (this.root!=null){
            let myQueue = new Queue();
            myQueue.push_(this.root);
            this.BFSrecursive(myQueue);
        }
    }
    BFSrecursive(myQueue){
        if (myQueue.empty()==true){
            return;
        }
        let temp = myQueue.front();
        //console.log(temp.getValue(), temp.getColor(), temp.getHeight(),temp.getPositionInLevel());
        myQueue.pop_();
        if (temp.getLeftSon()!=null){
            myQueue.push_(temp.getLeftSon());
        }
        if (temp.getRightSon()!=null){
            myQueue.push_(temp.getRightSon());
        }
        this.BFSrecursive(myQueue);
    }
    fixChildren(node){
        if (node!=null){
            if (node!=this.root && Math.abs(node.getParent().getHeight()-node.getHeight())>1){
                console.log(node.getValue(), "Fixing");
                node.setHeight(node.getParent().getHeight()+1);
                this.setPositions(node);
            }
            this.fixChildren(node.getLeftSon());
            this.fixChildren(node.getRightSon());
        }
        //this.setHeights(this.root);
        this.setPositions(this.root);
    }

}

class Queue{
    constructor(topValue=null){
        this.top = new Node(topValue);
        this.top.setNext(this.top);    
    }
    push_(node){
        if (this.top!=null){
            let temp = this.top;
            while (temp.getNext()!=this.top) {
                temp = temp.getNext();
            }
            node.setNext(this.top);
            temp.setNext(node);
        }
        else{
            this.top = node;
            this.top.setNext(this.top);
        }
    }
    pop_(){
        if (this.top==null){
            return new Node(-1);
        }
        let popped = this.top;
        let temp = this.top.getNext();
        if (temp == this.top){
            this.top = null;
        }
        else{
            while (temp.getNext()!=this.top) {
                temp = temp.getNext();
            }
            this.top = this.top.getNext();
            temp.setNext(this.top);
        }
        return popped;
    }
    empty(){
        if (this.top!=null){
            return false;
        }
        return true;
    }
    front(){
        if (this.top!=null){
            return this.top;
        }
        return new Node(-1);
    }
}
let tree = new Tree();
tree.insert(95);
tree.insert(896);
//tree.insert(968);
console.log(tree);

