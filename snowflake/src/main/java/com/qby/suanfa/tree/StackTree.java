package com.qby.suanfa.tree;

import com.qby.suanfa.basic.TreeNode;

import java.util.Stack;

public class StackTree {
    public static void main(String[] args) {

    }

    /**
     *   a
     *  b c
     * d e
      * @param root
     */
    public static void postOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode> output = new Stack<TreeNode>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                output.push(node);
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }

        while (output.size() > 0) {
            TreeNode n = output.pop();
            System.out.print(n.val + "\t");
        }
    }
}
