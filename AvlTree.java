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
        temp = root;
        while(temp != finalNode) {
            if(temp.getBalancedFactor() > 1) {
                rotation(temp, finalNode);
            }
        }

        System.out.println(root.getBalancedFactor());

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
    private void rotation(AvlNode root, AvlNode finalNode) {
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
            node.setLeftHeight(node.getLeft().getLeftHeight() + 1);
        }
        //recalculate the balance factor
        temp.setLeftHeight(temp.getLeft().getLeftHeight() + 1);
        temp.setRightHeight(temp.getRight().getLeftHeight() + 1);
    }
    private void rrRotation(AvlNode node) {
        AvlNode left = null, temp = node;

        temp = temp.getRight();
        if(temp.getLeft() != null) left = temp.getRight();
        temp.setLeft(node);
        if(left != null) {
            node.setRight(left);
            node.setRightHeight(node.getRight().getRightHeight() + 1);
        }
        //recalculate the balance factor
        temp.setLeftHeight(temp.getLeft().getLeftHeight() + 1);
        temp.setRightHeight(temp.getRight().getLeftHeight() + 1);
    }
    private void lrRotation(AvlNode node) {
        AvlNode left = null, temp;

        temp = node.getLeft().getRight();
        if(temp.getLeft() != null) {
            left = temp.getLeft();
        }
        temp.setLeft(node.getLeft());
        if(left != null) {
            node.getLeft().setRight(left);
            if(left.getRightHeight() > left.getLeftHeight()) {
                node.getLeft().setRightHeight(left.getRightHeight() + 1);
            }
            else {
                node.getLeft().setRightHeight(left.getLeftHeight() + 1);
            }
        }

        node.setLeft(temp);
        temp = node.getLeft();
        AvlNode neo = node.getLeft().getLeft();
        if(neo.getRightHeight() > neo.getLeftHeight()) {
            temp.setLeftHeight(neo.getRightHeight() + 1);
        }
        else {
            temp.setLeftHeight(neo.getLeftHeight() + 1);
        }
        temp.setRightHeight(0);

        if(temp.getRightHeight() > temp.getLeftHeight()) {
            node.setLeftHeight(temp.getRightHeight() + 1);
        }
        else {
            node.setLeftHeight(temp.getLeftHeight() + 1);
        }
        llRotation(node);
    }
    private void rlRotation(AvlNode node) {
        AvlNode right = null, temp;

        temp = node.getRight().getLeft();
        if(temp.getRight() != null) {
            right = temp.getLeft();
        }
        temp.setRight(node.getRight());
        if(right != null) {
            node.getRight().setLeft(right);
            if(right.getRightHeight() > right.getLeftHeight()) {
                node.getRight().setLeftHeight(right.getRightHeight() + 1);
            }
            else {
                node.getRight().setLeftHeight(right.getLeftHeight() + 1);
            }
        }
        node.setRight(temp);
        temp = node.getRight();

        AvlNode neo = node.getRight().getRight();
        if(neo.getRightHeight() > neo.getLeftHeight()) {
            temp.setRightHeight(neo.getRightHeight() + 1);
        }
        else {
            temp.setRightHeight(neo.getLeftHeight() + 1);
        }
        temp.setLeftHeight(0);

        if(temp.getRightHeight() > temp.getLeftHeight()) {
            node.setRightHeight(temp.getRightHeight() + 1);
        }
        else {
            node.setRightHeight(temp.getLeftHeight() + 1);
        }
        rrRotation(node);
    }
}
