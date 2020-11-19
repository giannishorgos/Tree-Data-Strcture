package com.java.btree;
import com.java.btree.Node;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class SearchTree {
    private Node root;

    public SearchTree(int data) {
        root = new Node(data);
    }

    public void addNode(int data) {
        Node temp = root;
        boolean added = false;
        while(!added) {
            if(data >= temp.getData()) {
                if(temp.getRight() != null) {
                    temp = temp.getRight();
                }
                else {
                    temp.setRight(new Node(data));
                    added = true;
                }
            }
            else {
                if(temp.getLeft() != null) {
                    temp = temp.getLeft();
                }
                else {
                    temp.setLeft(new Node(data));
                    added = true;
                }
            }
        }
    }

    public String find(int data) {
        Node temp = root;
        int steps = 0;
        LocalDateTime mil1 = LocalDateTime.now();
        while(temp != null) {
            steps++;
            if(data > temp.getData())
                temp = temp.getRight();
            else if(data < temp.getData())
                temp = temp.getLeft();
            else {
                LocalDateTime mil2 = LocalDateTime.now();
//              System.out.println(mil2.getNano());
//              System.out.println(mil1.getNano());
                return steps + " Is found in " + (mil2.getNano() - mil1.getNano()) + " nanosec";
            }
        }
        LocalDateTime mil2 = LocalDateTime.now();
        return steps + " Is not found " + (mil2.getNano() - mil1.getNano()) + " nanosec";
    }
    public void printing() {
        Node temp = root;
        while(temp.getLeft() != null) {
            System.out.println("Lefts " + temp.getData());
            temp = temp.getLeft();
        }
        System.out.println("Lefts " + temp.getData());

        temp = root;
        while(temp.getRight() != null) {
            System.out.println("Rights " + temp.getData());
            temp = temp.getRight();
        }
        System.out.println("Right " + temp.getData());
    }
}
