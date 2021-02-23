package com.qby.suanfa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class Solution2 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 100. 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：p = [1,2,3], q = [1,2,3]
     * 输出：true
     * 示例 2：
     * <p>
     * <p>
     * 输入：p = [1,2], q = [1, null,2]
     * 输出：false
     * 示例 3：
     * <p>
     * <p>
     * 输入：p = [1,2,1], q = [1,1,2]
     * 输出：false
     * <p>
     * 深度优先搜索
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p ==  null && q ==  null) {
            return true;
        }
        if (p !=  null && q ==  null) {
            return false;
        } else if (p ==  null && q !=  null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 广度优先搜索
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p ==  null && q ==  null) {
            return true;
        } else if (p ==  null || q ==  null) {
            return false;
        }
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue1.offer(p);
        queue2.offer(q);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();

            if (node1.val != node2.val) {
                return false;
            }
            TreeNode left1 = node1.left, right1 = node1.right, left2 = node2.left, right2 = node2.right;
            if (left1 ==  null ^ left2 ==  null) {
                return false;
            }
            if (right1 ==  null ^ right2 ==  null) {
                return false;
            }
            if (left1 !=  null) {
                queue1.offer(left1);
            }
            if (right1 !=  null) {
                queue1.offer(right1);
            }
            if (left2 !=  null) {
                queue2.offer(left2);
            }
            if (right2 !=  null) {
                queue2.offer(right2);
            }
        }

        return queue1.isEmpty() && queue2.isEmpty();
    }

    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     * <p>
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * <p>
     * <p>
     * 但是下面这个 [1,2,2, null,3, null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     *
     * 递归法
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root ==  null) return true;
        return compareTreeNode(root.left, root.right);
    }

    /**
     * 递归法
     *
     * @param left
     * @param right
     * @return
     */
    public boolean compareTreeNode(TreeNode left, TreeNode right) {
        // 确定终止条件
        // 节点为空的情况
        if (left ==  null && right !=  null) return false;
        else if (left !=  null && right ==  null) return false;
        else if (left ==  null && right ==  null) return true;
        else if (left.val != right.val) return false;

        // 此时就是：左右节点都不为空，且数值相同的情况
        // 此时才做递归，做下一层的判断
        boolean outside = compareTreeNode(left.left, right.right);   // 左子树：左、 右子树：右
        boolean inside= compareTreeNode(left.right, right.left);    // 左子树：右、 右子树：左
        boolean isSame = outside && inside;                    // 左子树：中、 右子树：中 （逻辑处理）
        return isSame;
    }

    /**
     * 迭代法
     * 使用队列存储数据
     * 判断条件和递归时是一样的
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new ArrayBlockingQueue<TreeNode>(1000);
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) {
                // 左节点为空、右节点为空，此时说明是对称的
                continue;
            }
            // 左右一个节点不为空，或者都不为空但数值不相同，返回false
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            // 加入左节点左孩子
            queue.offer(left.left);
            // 加入右节点右孩子
            queue.offer(right.right);
            // 加入左节点右孩子
            queue.offer(left.right);
            // 加入右节点左孩子
            queue.offer(right.left);
        }
        return true;
    }

    /**
     * 迭代法
     * 使用堆栈
     *
     *
     * @param root
     * @return
     */
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();

            if (left == null && right == null) {
                // 左节点为空、右节点为空，此时说明是对称的
                continue;
            }
            // 左右一个节点不为空，或者都不为空但数值不相同，返回false
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            // 加入左节点左孩子
            stack.push(left.left);
            // 加入右节点右孩子
            stack.push(right.right);
            // 加入左节点右孩子
            stack.push(left.right);
            // 加入右节点左孩子
            stack.push(right.left);
        }
        return true;
    }
}
