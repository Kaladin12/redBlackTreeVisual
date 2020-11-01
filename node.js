class Node {
    constructor(value, next=null){
        this.value = value;
        this.next = next;
        this.leftSon = null;
        this.rightSon = null;
        this.parent = null;
        this.red = false;
        //this.next = new Node(null);
    }
    /*    constructor(value, next){
        this(value);
        this.next = next;
    } */
    print() {
        console.log(this.value);
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
}

class Tree {
    constructor(DaRoot=null){
        this.blackHeight = 1;
        this.root = new Node(DaRoot);
    }
    insert(value){
        if (this.root.getValue()===null){
            this.root = new Node(value);
        }
        else{
            let newNode = new Node(value);
            this.root = this.insertRecursive(this.root, newNode);
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
        nodeToInsert.setParent(this.getParent(this.root, nodeToInsert.getValue()));
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
}
let a = new Tree();
for (let i = 0; i < 20; i++) {
     a.insert(Math.floor(Math.random() * 100));
}
console.log(a);
//console.log(a);