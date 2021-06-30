package com.qby.suanfa.basic;

public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(Integer val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 根据数组生成一颗完全二叉树
     *
     * @param sz
     * @return
     */
    public static TreeNode makeTreeNode(Integer[] sz) {
        int n = sz.length;
        TreeNode[] szTree = new TreeNode[sz.length];
        for (int i = 0; i < sz.length; i++) {
            szTree[i] = new TreeNode(sz[i]);
            szTree[i].left = null;
            szTree[i].right = null;
        }

        for (int i = 0; i < n; i++) {
            if (2 * i + 1 < n) {
                szTree[i].left = szTree[2 * i + 1];
            }
            if (2 * i + 2 < n) {
                szTree[i].right = szTree[2 * i + 2];
            }
        }
        return szTree[0];
    }
}
