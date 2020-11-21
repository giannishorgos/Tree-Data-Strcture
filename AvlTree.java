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
        calculateBalancedFactor(finalNode, tempHeight);

    }
    private void calculateBalancedFactor(AvlNode finalNode, int tempHeight) {
        AvlNode temp = root;
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
    }
    private void rotation(AvlNode finalNode) {
        AvlNode temp = root;
        AvlNode path;
        String rot = "";
        while(temp != finalNode) {
            if(temp.getBalancedFactor() > 1) {
                path = temp;
                for(int i = 0; i < 2; i++) {
                    if (finalNode.getData() >= path.getData()) {
                        rot += "R";
                        path = temp.getRight();
                    }
                    else {
                        rot += "L";
                        path = temp.getLeft();
                    }
                }
                switch(rot) {
                    case "RR":
                        rrRotation(temp);
                        break;
                    case "LL":
                        llRotation(temp);
                        break;
                    case "RL":
                        rlRotation(temp);
                        break;
                    case "LR":
                        lrRotation(temp);
                        break;
                }

            }
        }
    }
    private void llRotation(AvlNode node) {
        AvlNode right = null, temp = node;

        temp = temp.getLeft();
        if(temp.getRight() != null) right = temp.getRight();
        temp.setRight(node);
        if(right != null) {
            node.setLeft(right);
        }
    }
    private void rrRotation(AvlNode node) {
        AvlNode left = null, temp = node;

        temp = temp.getRight();
        if(temp.getLeft() != null) left = temp.getRight();
        temp.setLeft(node);
        if(left != null) {
            node.setRight(left);
        }
    }
    private void lrRotation(AvlNode node) {}
    private void rlRotation(AvlNode node) {}
}
