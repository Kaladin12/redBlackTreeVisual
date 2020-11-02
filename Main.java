package RedBlackTree;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> ab = new Tree<Integer>();
        Random randy = new Random();
        Integer[] array = new Integer[]{14,6,68,3,12,51,74,2,9,44,63,70,78,54,74,83,74,82,97};//{56,30,70,22,40,60,95,11,3,16,65,63,67,2,25,20,36,10,23,5};
        //{12,5,15,3,7,13,17,1,9,14,20,8,11,18,21};
        //{7,3,11,1,5,9,13,4,6,8,12,14,73};
        //{10,6,20,4,8,15,21,11,16,22,14,17};
        //{30,60,45,15,7,22,75,17,27};
        //{50,48,30,70,65,90,20,32,31,35,15,25,67,98,66,94,99,69};
/*        for (Integer e : array) {
            ab.insertRecursive(e);
            System.out.println();
        }  */
        
        
        int n = 0; 
        for (int i = 0; i < 30; i++) {
            n = randy.nextInt(10500);
            ab.insertRecursive(n);
            System.out.println(n);
        }  
        ab.preOrder();
        System.out.println(); 
    }
}
