package com.qby.suanfa.tree;

import com.qby.suanfa.basic.TreeNode;

import java.util.*;

public class StackTree {
    public static void main(String[] args) {

    }

    /**
     * 栈 二叉树后序遍历
     * a
     * b c
     * d e
     *
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

    /**
     * 二叉树前序遍历
     * a.根节点入栈；
     * b.弹出根节点，访问根节点；判断右孩子是否为空，如果不为空，push入栈；判断左孩子是否为空，如果不为空，push入栈。
     * 注：右孩子先入栈，左孩子后入栈，这样可以保证下一次出栈的时候，左孩子在栈顶。
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.empty()) {
            TreeNode top = stack.pop();
            list.add(top.val);
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
        }
        return list;
    }

    /**
     * 二叉树前序遍历2
     * <p>
     * a.对于任意节点，将当前节点及其不为null的左孩子全部入栈并访问；
     * b.若当前栈顶元素无左孩子，弹出栈顶元素，然后将栈顶元素的右孩子置为当前节点；
     * c.继续执行a，b操作，直到当前节点为null并且栈为空则结束。
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                TreeNode top = stack.pop();
                root = top.right;
            }
        }
        return list;
    }

    /**
     * 二叉树中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            //加入条件cur != nul 为了遍历到根节点以及之后
            while (cur != null) {
                //“”左“”的全部压入栈
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pollLast();
            //遍历开始
            output.add(cur.val);
            //开始“右”
            cur = cur.right;
        }
        return output;
    }

    /**
     * 前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> qxTree(TreeNode root) {
        List<Integer> rList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return rList;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            // 中
            TreeNode node = stack.pop();
            if (node != null) {
                rList.add(node.val);
                // 右
                stack.push(node.right);
                // 左
                stack.push(node.left);
            }
        }

        return rList;
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> zxTree(TreeNode root) {
        List<Integer> rList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 需要用到辅助指针
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 指针来访问节点，访问到最底层
                // 讲访问的节点放进栈
                stack.push(cur);
                // 左
                cur = cur.left;
            } else {
                // 从栈里弹出的数据，就是要处理的数据（放进result数组里的数据）
                cur = stack.pop();
                rList.add(cur.val);
                cur = cur.right;
            }
        }
        return rList;
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> hxTree(TreeNode root) {
        List<Integer> rList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return rList;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            // 中
            TreeNode node = stack.pop();
            if (node != null) {
                rList.add(node.val);
                // 左
                stack.push(node.left);
                // 右
                stack.push(node.right);
            }
        }
        Collections.reverse(rList);
        return rList;
    }

}
