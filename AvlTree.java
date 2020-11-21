package com.java.btree;

import com.java.btree.AvlNode;

public class AvlTree {
    private AvlNode root;

    public AvlTree(int data) {
        root = new AvlNode(data);
    }

    public void addNode(int data) {
        AvlNode temp = root;
        boolean added = false;
        boolean addedRight = false;
        int tempHeight = 0;

        while(!added) {
            if (data >= temp.getData()) {
                if (temp.getRight() != null) {
                    temp = temp.getRight();
                } else {
                    temp.setRight(new AvlNode(data));
                    addedRight = true;
                    added = true;
                }
            } else {
                if (temp.getLeft() != null) {
                    temp = temp.getLeft();
                } else {
                    temp.setLeft(new AvlNode(data));
                    added = true;
                }
            }
            tempHeight++;
        }

        AvlNode finalNode = addedRight ? temp.getRight() : temp.getLeft();
        temp = root;
        while(temp != finalNode) {
            if(finalNode.getData() >= temp.getData()) {
                if(temp.getRightHeight() < tempHeight) {
                    temp.setRightHeight(tempHeight);
                }
                temp = temp.getRight();
            }
            else {
                if(temp.getLeftHeight() < tempHeight) {
                    temp.setLeftHeight(tempHeight);
                }
                temp = temp.getLeft();
            }
            tempHeight--;
        }
        System.out.println(root.getRightHeight());
    }
}
