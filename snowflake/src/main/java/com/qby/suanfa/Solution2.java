package com.qby.suanfa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class Solution2 {
    Random rand = new Random();

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
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q == null) {
            return false;
        } else if (p == null && q != null) {
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
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
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
            if (left1 == null ^ left2 == null) {
                return false;
            }
            if (right1 == null ^ right2 == null) {
                return false;
            }
            if (left1 != null) {
                queue1.offer(left1);
            }
            if (right1 != null) {
                queue1.offer(right1);
            }
            if (left2 != null) {
                queue2.offer(left2);
            }
            if (right2 != null) {
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
     * <p>
     * 递归法
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
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
        if (left == null && right != null) return false;
        else if (left != null && right == null) return false;
        else if (left == null && right == null) return true;
        else if (left.val != right.val) return false;

        // 此时就是：左右节点都不为空，且数值相同的情况
        // 此时才做递归，做下一层的判断
        boolean outside = compareTreeNode(left.left, right.right);   // 左子树：左、 右子树：右
        boolean inside = compareTreeNode(left.right, right.left);    // 左子树：右、 右子树：左
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

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3 。
     * 深度优先算法
     * 如果我们知道了左子树和右子树的最大深度 ll 和 rr，那么该二叉树的最大深度即为
     * <p>
     * max(l,r) + 1
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 我们也可以用「广度优先搜索」的方法来解决这道题目，但我们需要对其进行一些修改，此时我们广度优先搜索的队列里存放的是「当前层的所有节点」。
     * 每次拓展下一层的时候，不同于广度优先搜索的每次只从队列里拿出一个节点，我们需要将队列里的所有节点都拿出来进行拓展，
     * 这样能保证每次拓展完的时候队列里存放的是当前层的所有节点，即我们是一层一层地进行拓展，
     * 最后我们用一个变量 \textit{ans}ans 来维护拓展的次数，该二叉树的最大深度即为 \textit{ans}ans。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param p
     * @return
     */
    public int maxDepth2(TreeNode p) {
        if (p == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(p);

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }

        return ans;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     * <p>
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：nums = [-10,-3,0,5,9]
     * 输出：[0,-3,9,-10,null,5]
     * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
     * <p>
     * 示例 2：
     * <p>
     * <p>
     * 输入：nums = [1,3]
     * 输出：[3,1]
     * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
     *
     * 方法一：中序遍历，总是选择中间位置左边的数字作为根节点
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    /**
     * 方法一：中序遍历，总是选择中间位置左边的数字作为根节点
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * 方法二：中序遍历，总是选择中间位置右边的数字作为根节点
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public TreeNode helper2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * 方法三：中序遍历，选择任意一个中间位置数字作为根节点
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public TreeNode helper3(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 选择任意一个中间位置数字作为根节点
        int mid = (left + right + rand.nextInt(2)) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}
