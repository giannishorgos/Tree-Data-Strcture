package com.java.btree;

import com.java.btree.Node;

import static java.lang.StrictMath.abs;

public class AvlNode {
    private int leftHeight;
    private int rightHeight;
    private int balancedFactor;
    private AvlNode right;
    private AvlNode left;
    private int data;

    public AvlNode(int data) {
        this.data = data;
        leftHeight = 0;
        rightHeight = 0;
    }

    public int getLeftHeight() {
        return leftHeight;
    }

    public int getRightHeight() {
        return rightHeight;
    }

    public void setLeftHeight(int leftHeight) {
        this.leftHeight = leftHeight;
    }

    public void setRightHeight(int rightHeight) {
        this.rightHeight = rightHeight;
    }

    public int getBalancedFactor() {
        balancedFactor = abs(leftHeight - rightHeight);
        return balancedFactor;
    }

    public AvlNode getRight() {
        return right;
    }

    public void setRight(AvlNode right) { this.right = right; }

    public AvlNode getLeft() {
        return left;
    }

    public void setLeft(AvlNode left) {
        this.left = left;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
