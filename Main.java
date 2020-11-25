package com.java.btree;

import java.time.LocalDateTime;
import java.util.Random;
import com.java.btree.AvlTree;

public class Main {
    public static void main(String[] args) {
        AvlTree tree = new AvlTree(10);
        tree.addNode(15);
        tree.addNode(14);
        tree.addNode(13);
        tree.addNode(16);
        tree.addNode(20);
        tree.addNode(25);
        tree.addNode(30);
       /* int size = 100000;
        SearchTree btree = new SearchTree(30000);
        Random rand = new Random();

        for(int i = 0; i < size; i++)
            btree.addNode(rand.nextInt(size));
        LocalDateTime mil1;
        LocalDateTime mil2;
        int ff = rand.nextInt(size);

        int[] arr = new int[size];
        for(int i = 0; i < size; i++)
            arr[i] = rand.nextInt(size);

        int data = rand.nextInt(size);

        mil1 = LocalDateTime.now();
        int steps = find(data, arr, size);
        mil2 = LocalDateTime.now();
//        System.out.println(mil2.getNano());
//        System.out.println(mil1.getNano());
          System.out.println(steps + " in " + (mil2.getNano() - mil1.getNano()) + " millisec");
//        System.out.println(btree.find(ff));*/
    }
    public static int find(int data, int[] arr, int size) {
        int steps = 0;
        for(int i = 0; i < size; i++) {
            steps++;
            if(data == arr[i]) break;
        }
        return steps;
    }
}
